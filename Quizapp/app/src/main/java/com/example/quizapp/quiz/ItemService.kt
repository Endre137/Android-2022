package com.example.quizapp.quiz

class ItemService (){
    val itemRepository = ItemRepository()

    fun selectRandomItems(number : Int) : List<Item>{
        val list = mutableListOf<Item>()
        var i = 0
        while(i < number){
            var randomItem = itemRepository.randomItem()
            if(list.contains(randomItem)){
                continue
            }
            else{
            list.add(itemRepository.randomItem())
            i++}
        }
        return list
    }

}