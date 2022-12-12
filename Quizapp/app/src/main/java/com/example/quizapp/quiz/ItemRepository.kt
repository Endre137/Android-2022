package com.example.quizapp.quiz

import java.io.File

class ItemRepository {

    val items = mutableListOf<Item>()

    init{
        val lines = File("input.txt").readLines()
        var i = 0
        while(i < lines.size){
            val answearsNumber = lines[i++].toInt()
            var question : String = lines[i++]
            var correct : Int = lines[i++].toInt()

            val answers = mutableListOf<String>()

            var j = 0
            while(j < answearsNumber) {
                answers.add(lines[i++])
                j++;
            }

            val item = Item(question,answers, correct)

            save(item)

        }

    }

    fun save(item : Item): Unit{
        items.add(item)
    }

    fun size():Int{
        return items.size
    }

    fun randomItem(): Item {
        return items.random()
    }
}