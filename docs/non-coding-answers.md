# SDE2 non-coding answers

Model answers for the five non-coding questions in the [Technical Interview Question Bank](https://mcd-tools.atlassian.net/wiki/spaces/ENGG/pages/93590587/Technical+Interview+Question+Bank), built from real evidence in the [activities page](https://mcd-tools.atlassian.net/wiki/spaces/~712020599cf2d9081f49a08e8560901a90faac/pages/2326856032/SD+Activities+since+start+working+in+MCD).

**Read these, then close the file and say them out loud.** Reciting memorised prose is obvious and scores badly. What you want is the shape in your head and the specifics on your tongue.

---

## 1. Describe the architecture of a project you've worked on. What would you change?

*Roughly two minutes for the description, then at least one concrete change. The second half is the actual question.*

I own the Registration and Login module in the McDonald's Global Mobile App, which serves five markets. It is a clean-architecture feature module with four layers.

The **data layer** holds repository implementations and the network models, talking to our SDK's endpoint layer. The **domain layer** is pure Kotlin with no Android dependencies — use cases and the domain models, so it is testable on the JVM with no framework at all. The **presentation layer** is Jetpack Compose with an MVI and unidirectional-data-flow pattern, and the **DI layer** wires it together with Hilt. The dependency rule runs inward: presentation depends on domain, data depends on domain, and domain depends on nothing.

The piece I'd single out is the state management. We had a recurring question about where state should live, so I wrote the module's StateHolder standard: flow-level state that spans several screens goes in a flow StateHolder, screen-local state in a screen StateHolder, and anything that has to survive process death goes in a ViewModel with SavedStateHandle. That is now published as an extension of the org's Android architecture guidelines and it's what we point new engineers at.

**What I'd change, first:** the module still reaches into legacy shared preferences in one or two places for session state. It works, but it means the data layer has two sources of truth for whether a user is authenticated, and that has caused at least one bug I fixed. I'd consolidate behind a single session repository. I didn't do it at the time because it crosses into another team's ownership and would have blocked the delivery I was on — which in hindsight I should have raised as a tracked follow-up rather than leaving as a comment.

**Second:** we adopted MVI for consistency, but for the genuinely simple screens — a static legal-text page, for example — the ceremony of intents and reducers costs more than it returns. I'd allow a lighter pattern for leaf screens with no real state machine. Uniformity is worth something, but not unlimited.

> Have a third change ready in case they push: the error-handling layer grew organically across the phased-API work and could be one sealed hierarchy rather than two overlapping ones.

---

## 2. Serverless computing vs containers — when would you use one over the other?

*A known blind spot. Answer structurally, then mark the boundary of your experience honestly. Do not invent production stories.*

I should say up front that I'm a mobile engineer, so I've consumed these services rather than operated them. I can talk about the tradeoffs and where I've seen them matter from the client side.

**Serverless** — a Lambda or Cloud Function — means you ship a function and the platform owns everything underneath: provisioning, scaling to zero, scaling out under load. You pay per invocation. That suits spiky, event-driven, short-lived work: reacting to a queue message, a scheduled job, an image resize on upload.

**Containers** mean you ship the process and its dependencies and something like Kubernetes or ECS runs it. You control the runtime, the startup behaviour and long-lived state, and you pay for the capacity whether or not it is being used.

The tradeoffs I'd weigh:

- **Cold starts.** Serverless scales to zero, which is exactly why the first request after idle is slow. For anything user-facing and latency-sensitive that matters. Our login flow calls services where a cold start would be visible to the customer.
- **Execution time and resource limits.** Serverless platforms cap runtime and memory. Long-running or heavyweight work doesn't fit.
- **Cost shape.** Serverless is cheap when traffic is spiky and idles often, and gets expensive at sustained high volume, where reserved container capacity wins.
- **Operational burden.** Serverless removes patching and capacity planning; containers give you control and portability at the cost of running the platform.
- **Lock-in.** Container images move between clouds far more easily than a function wired into one vendor's event ecosystem.

So: event-driven, bursty, short — serverless. Steady traffic, long-lived, latency-sensitive, or needing runtime control — containers. Most real systems run both.

Where my experience genuinely ends is operating either at scale: I have not owned a deployment topology or a cost model. It's one of the reasons I've asked to sit in on backend design reviews for the Registration and Login services.

---

## 3. What are important considerations when developing reliable services? How do you test it? What metrics do you monitor? How do you know when an issue is occurring?

*The single most important answer on this page. Your May 2026 assessment scored Scalability thinking 2/5, and your IDP names observability as your biggest gap. This question walks straight into both. Rehearse it until it is fluent.*

I'll answer from the client side, which is where I work, and I'll be straight about where my gap is.

**Testing.** I work in layers. Domain use cases are pure Kotlin, so they're unit tested on the JVM with Kotest and MockK — fast and deterministic, no framework. Repositories are tested against fake data sources rather than mocked networks. For the presentation layer I test StateHolders and ViewModels by asserting on the emitted state flow with Turbine, so I'm testing the state machine rather than the pixels. I deliberately wrote in-memory fakes rather than using Robolectric, because Robolectric was slow and flaky enough to be a CI problem — I later migrated a failing suite off it entirely.

The piece I'd highlight is that testing the happy path is the easy half. For Registration and Login the interesting behaviour is all in the failures, so I catalogued every error the backend can return and built a library of twenty-five reusable JSON mocks — account not found, device already active, expired magic link, nonce mismatch, social provider mismatch. Any engineer or QA can now reproduce a specific backend failure locally with no backend support. That turned error handling from something we discovered in production into something we test before merge.

**Metrics.** For a mobile client the ones I actually watch are crash-free session rate, which is the top-line health signal; funnel conversion through the login and registration journey via our clickstream analytics, because a silent drop between two steps is usually a defect rather than user behaviour; error-rate by error code, since a spike in one specific backend code localises a problem fast; and New Relic for the flow's start and completion events, which I instrumented.

**How I know something is wrong.** Honestly, today it is mostly reactive — crash reporting, a spike in a specific error code, or a market reporting it. The critical crash I fixed earlier this year reached me as a defect report, not as an alert, and I think that's the right thing to be dissatisfied with.

That's my real gap, and it's written into my development plan. I have not owned an alert definition or a dashboard. What I want to change is the direction of the signal: instead of waiting for a crash report, define a threshold on login funnel completion and crash-free rate for the authentication flow and get alerted when it moves. I've already touched the New Relic logging for this flow, so it's the natural place for me to start, and it's the thing I'd most like to pick up at the next level.

> Ending on the gap is deliberate. Naming it precisely, with a concrete plan, reads as senior. Claiming you have observability covered when you don't is one follow-up question away from falling apart.

---

## 4. Can you describe a situation where you had to quickly learn a new technology to solve a problem?

*Pick the Zscaler emulator story over a framework story. It's more distinctive and it shows initiative, which is your lowest-scored competency.*

**Situation.** Our corporate network does TLS inspection through Zscaler. On an Android emulator that breaks every HTTPS call, because the emulator doesn't trust the corporate root CA. The whole squad was losing time to it, and the usual workaround — disabling certificate validation — was not something I was willing to ship near an authentication flow.

**Task.** Get the emulator to trust the corporate CA properly, without weakening the app's security posture.

**Action.** I had to learn a stack I'd never touched: how Android's system trust store differs from the user store, why a Play Store system image cannot be rooted, how to build a writable API 33 AVD, and how to install a certificate into the system store with the correct SELinux context so it survives. I worked through it, got it stable, and then wrote a self-healing shell function that reinstalls the CA automatically, because otherwise it broke every time Android Studio restarted the emulator.

**Result.** Local development works on the corporate network with the security model intact. I documented the whole thing on Confluence — the reasoning, not just the commands, so people could adapt it rather than copy it. Other engineers on the squad now use it.

**What I took from it.** The thing I'd do differently is share it sooner. I solved it for myself first and wrote it up afterwards; the team lost time in between that they didn't need to lose.

---

## 5. Could you describe your experience with cloud services? Benefits of cloud vs on-premise?

*Same honesty rule as question 2. Answer the concepts well, then mark the boundary.*

My hands-on experience is Firebase — Firestore, Cloud Storage and Cloud Messaging — from an Android application project where I used them as a live-sync backend, plus consuming our own cloud-hosted services from the client. I have not architected or operated cloud infrastructure, so let me answer on the tradeoffs.

**Cloud advantages:** no capital expenditure and no procurement lead time; elastic capacity so you pay for what you use and can absorb a traffic spike; managed services that remove undifferentiated work like database patching and backups; and global regions, which for us matters because the app serves markets on several continents and latency and data residency are both real constraints.

**What to watch for:** cost is elastic in both directions and is easy to lose control of without tagging and budget discipline; lock-in grows the more managed services you adopt; data residency and regulatory constraints can force specific regions or providers; latency to a distant region can be worse than a local machine; and you inherit the provider's outages, so multi-region design becomes your problem rather than theirs.

**On-premise still wins** where you have steady, predictable load that makes reserved hardware cheaper, hard regulatory requirements about physical data location, or existing sunk infrastructure and the operations team to run it.

For a consumer application like ours with five markets and traffic that swings hard around promotions, cloud is clearly right — the elasticity alone justifies it. Where I'd want to grow is being able to reason about the cost and availability tradeoffs of a specific service design, which is why I've asked to join backend design reviews.
