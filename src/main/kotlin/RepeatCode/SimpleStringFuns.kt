package RepeatCode

val topLevelVariable = 20 // Variables can be declared outside the main().

fun main(){
    println("-------------------")
    helloWorld()
    variables()
    intOperations()
    initializeVariable()
    printDifferentVariables()
}
fun helloWorld() {
    println("Hello World")
    // Difference is the line separator/line break included
    print("Hello World")
}

fun variables(){
    val notMutableVariable = 3
    //notMutableVariable = 5 [val can not be reassigned]

    var mutableVariable = 2
    mutableVariable = 7
    println(mutableVariable)

    println("You can print variables directly with sign $ $mutableVariable")
    println("You can evaluate variables ${mutableVariable + 1} directly on strings")
}

fun intOperations(){
    var apples = 3
    println(apples)
    apples = 10 // you get 7 more apples from the sky
    println(apples)
    //apples =+ 2             // WE CAN NOT DO THIS, THE SYMBOL IS BEFORE =
    apples = apples + 1     // Example of addition: 11
    println(apples)
    apples += 2             // Example of addition: 13
    println(apples)
    apples -= 3             // Example of substraction: 10
    println(apples)
    apples *= 4             // Example of multiplication: 40
    println(apples)
    apples /= 5             // Example of division: 8
    println(apples)
    apples %= 6             // Example of remainder of truncating division of the value: 2
    println(apples)
}

fun initializeVariable(){
    val newVariable: Int
    newVariable = 3
    val e: String = "Variable initialized and with a type "

    // Variables can be read because they were initialized
    println(e)
    println(newVariable)

    val declaredVariable: Int
    //println(declaredVariable) // declaredVariable must be initialized.

}

fun printDifferentVariables(){
    val a: Int = 1000
    val b: String = "log message"
    val c: Double = 3.14
    val d: Long = 100_000_000_000_000
    val e: Boolean = false
    val f: Char = '\n'
    val arr = arrayOf(a, b, c, d, e, f)  // mixed types → Array<Any>

    // Option 1: iterate elements directly (simplest)
    for (item in arr) {
        println(item)
    }

    // Option 2: iterate by index when you need the index
    for (i in arr.indices) {
        println("${i}: ${arr[i]}")
    }
}