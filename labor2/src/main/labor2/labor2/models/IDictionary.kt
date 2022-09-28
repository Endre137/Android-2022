package labor2.models

interface IDictionary {
    companion object {
        val FILE_NAME = "input.txt"
    }
    fun add(word: String) : Boolean
    fun find(word: String) : Boolean
   fun size() : Int
}