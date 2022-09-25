import java.util.*

fun main(args: Array<String>) {

    //1
    val x1=2
    val x2=3
    println("$x1 + $x2 = ${x1 + x2}")
    println()

    //2.1
    val daysOfWeek = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
//    for (day in daysOfWeek){
//        println(day)
//    }
    val days = daysOfWeek.filterNotNull()
    println(days)
    println()


    //2.2
    print(daysOfWeek.filter{it.startsWith('T')})
//    for (day in daysOfWeek){
//        if(day[0] == 'T') {
//            println(day)
//        }
//    }
    println()

    //2.3
    print(daysOfWeek.filter{it.contains('e')})
//    for (day in daysOfWeek){
//        if(day.contains('e')){
//            println(day)
//        }
//    }
    println()
    println()

    //2.4
    print(daysOfWeek.filter { it.length == 6 })
//    for (day in daysOfWeek){
//        if(day.length == 6){
//            println(day)
//        }
//    }
    println()
    println()

    //3
    (2..100).filter { primeTest(it) }.forEach{print("$it ") }

    println()
    println()

    //4
    val message = messageCoding("Kotlin", ::encode)
    println(messageCoding(message, ::decode))

    println()
    println()



    //6.1
    val numbers = mutableListOf(1,2,3,4,5,6,7,8)
    println(numbers.map { it * 2 })

    println()
    println()

    //6.2
    println(daysOfWeek.map{it.uppercase()})

    println()
    println()

    //6.3
    println(daysOfWeek.map{it.lowercase().take(1)})

    println()
    println()

    //6.4
    println(daysOfWeek.map{it.length})

    println()
    println()

    //6.5
    println(daysOfWeek.map{it.length}.average())
    println()
    println()



    //7.1
    val mDaysOfWeek = daysOfWeek.toMutableList()
    mDaysOfWeek.removeIf { it.contains('n')}
    println(mDaysOfWeek)

    println()
    println()

    //7.2
    val iterator = mDaysOfWeek.iterator()

    for((index, value) in iterator.withIndex()){
        println("Index at $index is $value")
    }

    println()
    println()


    //7.3

    mDaysOfWeek.sort()
    println(mDaysOfWeek)

    println()
    println()



    //8.1
    var ran = Random()
    val randomArray = IntArray(10) {ran.nextInt(100-0)+0}.asList()

    println(randomArray)

    println()
    println()
    //8.2

    println(randomArray.sorted().reversed())

    println()
    println()
    //8.3

    println(randomArray.map{it % 2 == 0})

    println()
    println()

    //8.4
    if(randomArray.map{it % 2 == 0}.contains(false)){
        println("all the numbers are not even")
    }else{
        println("all the numbers are even")
    }

    println()
    println()
    //8.5

    println(randomArray.map { it }.average())

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

