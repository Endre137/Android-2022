import models.*
import models.DictionaryProvider.Companion.createDictionary
import java.util.Random

fun main(){
    //dictionaryExcersie()
    //extensionFunctions()
    dateExcersie()
}

fun dictionaryExcersie() {
    val dict: IDictionary = createDictionary(DictionaryType.HASH_SET)
    println("Number of words: ${dict.size()}")
    var word: String?
    while(true){
        print("What to find? ")
        word = readLine()
        if( word.equals("quit")){
            break
        }
        println("Result: ${word?.let { dict.find(it) }}")
    }
}

fun extensionFunctions(){
    var nev = "Balazs Endre"
    println(nev.monogram())

    val list = listOf("apple", "pear", "melon1")
    println(list.joinBySeparator("#"))

    println(list.longest())
}

fun dateExcersie(){

    val list = mutableListOf<Date>()
    var year : Int
    var month :Int
    var day : Int

    println("Invalid dates: ")
    while(list.size != 10) {
        year = kotlin.random.Random.nextInt(0,2022)
        month = kotlin.random.Random.nextInt(-1,40)
        day = kotlin.random.Random.nextInt(0,60)

        val date = Date(year, month, day)

        if(date.validDate()){
            list.add(date)
        }
        else{
            println(date)
        }
    }

    println()
    println()

    println("Valid dates: ")
    list.forEach{println(it)}

}



fun String.monogram() :String = this.split(" ").map{it.first().uppercase()}.joinToString("")

fun List<String>.joinBySeparator(separator : String) : String = this.joinToString (separator){it}

fun List<String>.longest() : String = this.maxBy { it.length }