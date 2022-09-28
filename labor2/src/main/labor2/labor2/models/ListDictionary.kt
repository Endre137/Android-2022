package labor2.models

import java.io.File
import java.util.Dictionary

object ListDictionary : IDictionary {

    val words = mutableListOf<String>()
    init{
        //beolvasas, hozzadas
        File(IDictionary.FILE_NAME).readLines().forEach{ add(it) }

    }
    override fun add(word: String): Boolean {
        if(!find(word)) {
            return words.add(word)
        }
        return false
    }

    override fun find(word: String): Boolean {
        return words.any{it==word}
    }

    override fun size(): Int {
        return words.size
    }


}