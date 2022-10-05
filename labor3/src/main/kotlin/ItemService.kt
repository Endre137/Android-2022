class ItemService (){
    val itemRepository = ItemRepository()

    fun selectRandomItems(number : Int) : List<Item>{
        val list = mutableListOf<Item>()
        var i = 0
        while(i < number){
            list.add(itemRepository.randomItem())
            i++
        }
        return list
    }

}