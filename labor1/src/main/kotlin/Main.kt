import java.util.*

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")

    val x1=2
    val x2=3
    println("$x1 + $x2 = ${x1 + x2}")

    val daysOfWeek = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
    for (day in daysOfWeek){
        println(day)
    }
    println();



    for (day in daysOfWeek){
        if(day[0] == 'T') {
            println(day)
        }
    }

    for (day in daysOfWeek){
        if(day.contains('e')){
            println(day)
        }
    }
    println()

    daysOfWeek.filter{it.contains('e')}


    println()
    for (day in daysOfWeek){
        if(day.length == 6){
            println(day)
        }
    }



    (2..100).filter { primeTest(it) }.forEach{print("$it ") }

    println()


    val message = messageCoding("Kotlin", ::encode)
    println(messageCoding(message, ::decode))
}


fun primeTest(number :Int): Boolean{
    if(number == 1 || number == 0){
        return false
    }

    if(number == 2){
        return true
    }

    var counter = 0
    for(i in 1..number){
        if(number % i == 0){
            counter++
        }
    }

    if(counter == 2){
        return true
    }
    return false
}

fun messageCoding(msg: String, func: (String) -> String): String{
    return func(msg)
}

fun encode(msg: String) : String{
    return Base64.getEncoder().encodeToString(msg.toByteArray())
}

fun decode(msg: String) :String{

    val decoder = Base64.getDecoder()
    val decoded = String(decoder.decode(msg))
    return (decoded)
}