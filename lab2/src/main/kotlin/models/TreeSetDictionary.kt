package models

import java.io.File
import java.util.TreeSet

object TreeSetDictionary : IDictionary{

    val words = TreeSet<String>()

    init{
        //beolvasas, hozzadas
        File(IDictionary.FILE_NAME).readLines().forEach{ add(it) }

    }
    override fun add(word: String): Boolean {
        if(!find(word)){
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