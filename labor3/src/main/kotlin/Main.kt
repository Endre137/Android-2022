fun main(args: Array<String>) {
//    val ItemController = ItemController()
//    ItemController.quiz(4)
    val answers = mutableListOf<String>()
    answers.add("Alma")
    answers.add("Banan")
    answers.add("Korte")
    answers.add("Szolo")

    for( i  in 0 .. answers.size-1){
        println(answers[i])
    }
    answers.clear()

    answers.add("Repa")
    answers.add("Krumpli")
    answers.add("Kaposzta")
    answers.add("Uborka")

    for( i  in 0 .. answers.size-1){
        println(answers[i])
    }
}