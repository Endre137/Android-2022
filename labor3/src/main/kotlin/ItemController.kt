class ItemController() {
    val itemService = ItemService()
    var correctAnswers = 0


    fun quiz(number: Int):Unit{
        println("Welcome to the quiz, only one answer is correct!")
        println()

        var list = listOf<Item>()
        list = itemService.selectRandomItems(number)


        for(item in list){
            println(item.question)

            item.answers.forEachIndexed{index,element -> println("  ${index+1} - $element")}
            println("Your Answear: ")
            try{
                val answear = Integer.valueOf(readLine())
                if(answear < 0 || answear > 4){

                }
                if(item.correct == answear){
                    println("\t -- Correct -- ")
                    correctAnswers++
                }
                else{
                    println("\t -- Wrong -- ")
                    println("The correct answear: ${item.answers[item.correct-1]}")
                }
            }catch(e : NumberFormatException){null}


            println()
        }
        println()
        println("Correct answears - $correctAnswers / Total number of answears - $number")
    }
}