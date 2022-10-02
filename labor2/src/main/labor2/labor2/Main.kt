import labor2.models.IDictionary
import labor2.models.ListDictionary

fun main(){
    val dict: IDictionary = ListDictionary
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
