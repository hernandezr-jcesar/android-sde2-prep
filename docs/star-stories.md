# STAR story bank

Eight stories, each about two minutes spoken. Drawn from the [activities page](https://mcd-tools.atlassian.net/wiki/spaces/~712020599cf2d9081f49a08e8560901a90faac/pages/2326856032/SD+Activities+since+start+working+in+MCD).

**Two rules.** Never read these aloud — learn the shape and the numbers, then improvise the words. And always finish on the result, with a number if you have one; trailing off after "Action" is the most common way a good story lands flat.

## Coverage map

| Question you're asked | Story to reach for |
| --- | --- |
| Most challenging project / describe an architecture you designed | 1. Interop navigation |
| Difficult bug / production pressure / ownership | 2. Critical crash |
| Initiative / something nobody asked you to do | 3. CI rescue |
| Mentoring / growing others | 4. Onboarding Victor |
| Communication / influencing / staying current | 5. Brownbag talk |
| Non-technical audience / stakeholder management | 6. Sprint review |
| Process improvement / thinking beyond your ticket | 7. Error-mock harness |
| Conflict / disagreement / receiving criticism | 8. The feedback story |

---

## 1. Interop navigation — the architecture story

**S.** We were migrating login and registration to a new Compose module while the rest of the app stayed legacy. The two halves didn't connect: after a user logged in through the new flow, the app had no reliable way to send them where they'd originally been heading. If you tapped a deep link to an offer and got prompted to log in, you'd land on the home screen afterwards instead of the offer.

**T.** I picked up designing the bridge between the new module and the legacy application.

**A.** I wrote the design up front rather than starting from code, because the contract was the hard part. I introduced a shared `core/navigator` module with a post-login navigator contract, a persistent store for the pending destination so it survives process death, and a standardised activity-result handshake that the legacy deep-link router consumes. The key decision was direction of dependency: legacy code depends on the new module's public contract, never the reverse, so the legacy side can be deleted later without unpicking the new one.

I decomposed it into seven independently shippable stories and delivered all seven with unit and integration tests, plus JVM fakes so the navigator tests need no Android framework at all.

**R.** It merged as about 2,300 lines across 33 files and it's the mechanism the app now uses for post-login navigation. Because it went in story by story rather than as one drop, nothing was blocked behind it for weeks.

**If they push on tradeoffs** — and they will, this is where you show SDE II judgement: the alternative was letting the legacy router read the new module's state directly. Faster to write, and it would have coupled the legacy code to the new module's internals, so the migration would have got harder rather than easier. I took the slower option because the whole point was to make the legacy side removable. The cost is a layer of indirection that's harder to follow first time, which is why I wrote the docs.

---

## 2. The critical crash — production ownership under pressure

**S.** A critical defect came in: the app crashed on "Continue with Email" on Android 14 and below, across every market. Login is the entry point to the product, so this was blocking real customers.

**T.** I picked it up and owned it to resolution.

**A.** It reproduced only on older API levels, which narrowed it to something version-dependent in the view layer. I worked back from the stack trace to a content-description that was being set in a way older platform versions didn't tolerate. One line. I fixed it, verified across the affected API levels and markets, and wrote the root-cause analysis.

**R.** The fix shipped and the RCA was accepted. I've had no escaped high or critical defects of my own across 63 merged pull requests — the two critical bugs I've closed were both pre-existing.

**The honest reflection**, which is worth volunteering: this reached me as a defect report, not as an alert. A crash on the login entry point across every market should have paged someone before a human noticed. That's a big part of why observability is the gap I'm actively working on.

---

## 3. The CI rescue — initiative

*This is your Initiative story. That was scored 2/5 in May, so this one matters.*

**S.** CI went red on unit tests. Not my tickets, not my module — a set of Robolectric suites failing and blocking the pipeline for everyone.

**T.** Nobody had picked it up. I raised the ticket myself and took it.

**A.** The suites were Robolectric, which we're migrating away from because it's slow and flaky. Rather than patch them, I migrated them to Kotest so they run on the JVM — about 588 lines added and 651 removed. Then I cherry-picked the fix into the release candidate, because leaving the RC red would have blocked the release.

**R.** Raised, fixed, merged and cherry-picked inside 24 hours. CI green, release unblocked, and a chunk of Robolectric permanently gone.

**The pattern behind it:** twelve of my tickets are ones I raised myself rather than were assigned — a startup crash blocking every internal build, two build-breaking test failures, a naming defect spanning 30 files that I spotted during someone else's code review.

---

## 4. Onboarding Victor — mentoring

**S.** A new engineer joined the squad and needed to get productive in Login and Registration, which is a hard module to enter: clean architecture, an unfamiliar state-holder pattern, and a legacy half.

**T.** I owned his onboarding end to end.

**A.** I started with environment setup, which is where people lose their first week, and wrote it down as a guide rather than walking him through it verbally — so the next person gets it too. Then I paired on his first tickets, gave code review feedback aimed at the reasoning rather than the diff, and deliberately handed him ownership of real work rather than filler.

**R.** He now closes bugs in the module independently and holds tickets I handed over. Eight months after I onboarded him he presented his own talk at the chapter brownbag. That's the outcome I'd point at — not that he shipped, but that he's now teaching other people.

---

## 5. The brownbag talk — communication

**S.** I'd upgraded our module to a new Design System Library version, across 32 files. Other squads were about to do the same upgrade and would hit the same problems.

**T.** I volunteered to present it at the Android brownbag, the chapter-wide forum across all GMA squads in the US and Mexico. This was about four months after I joined.

**A.** I built the talk around the decisions and the traps rather than a walkthrough of the diff, since nobody needs to watch someone scroll through a migration.

**R.** Delivered in July 2025; the recording and deck are on the chapter schedule.

**Be ready for the follow-up** *"what have you presented since?"* — the honest answer is nothing, and that's a fair hit. I've produced two talks' worth of written material since, the StateHolder architecture standard and a write-up of agentic development workflows, and I haven't converted either into a session. It's on my plan for this quarter.

---

## 6. The sprint review — non-technical audience

**S.** Sprint review for the whole squad, covering both Android and iOS, in front of a mixed audience including non-technical stakeholders.

**T.** I wrote and hosted it.

**A.** I scripted it around the sprint goal and what changed for the customer, not a ticket-by-ticket readout. Twenty items across both platforms, each framed in terms of user impact, with hand-offs so every engineer presented their own work. I called out carry-overs honestly rather than hiding them, because a review that only reports wins stops being believed.

**R.** It ran to time and stakeholders got a picture of the sprint rather than a list of Jira keys.

---

## 7. The error-mock harness — process improvement

**S.** Registration and Login has a large surface of backend error cases, and reproducing a specific one locally meant asking a backend engineer to force it. So error paths got tested late, shallowly, or not at all.

**T.** It started as a spike to catalogue every error the API can return. I turned it into tooling.

**A.** I inventoried every error and its use case, then built twenty-five reusable JSON mocks — account not found, device already active, expired magic link, nonce mismatch, social provider mismatch, and so on — and wrote a testing guide for using them.

**R.** Any engineer or QA can now reproduce a specific backend failure locally with no backend involvement. It moved error handling from tribal knowledge into a repeatable process, and I've become the person the squad asks about error handling.

---

## 8. The feedback story — conflict and criticism

*Use this for "tell me about receiving difficult feedback", "a disagreement", or "your biggest weakness". Also your pre-emptive answer to the Initiative 2/5 problem.*

**S.** In my mid-year assessment I got feedback I didn't enjoy. My technical scores were strong, but ownership, initiative and follow-through were rated lower — the substance being that I'd hand work off after implementation rather than carrying it through validation and post-release, and that I wasn't proposing work beyond what was assigned.

**T.** Decide whether to argue with it or act on it.

**A.** I sat with it, because my first reaction was defensive and I could point at counter-examples. But the pattern was real: I was strongest at the implementation phase and weakest either side of it. So I changed how I pick up work. I started raising tickets myself when I found problems instead of mentioning them in review. I took the interop navigation epic, which meant owning a design from spec through seven stories to a merged epic rather than taking someone else's decomposition. And I started following changes past the merge — the crash fix and the deep-link defect both include the RCA, not just the patch.

**R.** In the three months since, the work I'm proudest of is exactly the work that feedback was asking for: the interop epic designed and delivered end to end, the CI rescue I raised and closed in a day, twelve self-raised tickets. I don't think the feedback was wrong. I think it was the most useful thing anyone told me last year.

**Why to volunteer this rather than wait for it:** the assessment is on record and rated me Junior/Mid-Sr. If the panel raises it and you look surprised, it becomes a doubt about self-awareness on top of the original concern. If you raise it first and show the arc, the same facts become evidence that you respond to feedback — which is the one thing that assessment scored you 5/5 on.
