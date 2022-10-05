class ItemController() {
    val itemService = ItemService()
    var correctAnswers = 0

    fun quiz(number: Int):Unit{
        println("Welcome to the quiz, only one answer is correct!")

        var list = listOf<Item>()
        list = itemService.selectRandomItems(number)

        for(item in list){

        }
    }
}