import android.content.Context
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.io.IOException
import java.security.AccessController.getContext


data class QuizState(
//     var MAX_QUESTIONS : Int = 5,

     val counter :Int = 1,
     val userName : String? = null,
     val question : String? = null,
     val answer1 : String? = null,
     val answer2 : String? = null,
     val answer3 : String? = null,
     val answer4 : String? = null,
     val correctAnswers : Int = 0,
     val highestScore : Int = 0
)
val list = mutableListOf<Item>()

data class Item(
     val question : String,
     val answers : List<String>,
     val correct : String,
     val answerNumber : Int

)

fun setQuestions(){
     val TAG= "Radio"
     var answers = mutableListOf<String>()
     var answearsNumber :Int
     var question : String
     var correct : String

     answearsNumber = 4
     question = "What does a data class not offer?"
     correct = "Automatic conversion from/to JSON"
     answers.add("An auto-generated toString() method")
     answers.add("Automatic conversion from/to JSON")
     answers.add("A generated copy(...) method, to create copies of instances")
     answers.add("Auto-generated hashCode() and equals() methods")
     answers.shuffle()

     var item = Item(question,answers, correct,answearsNumber)
     list.add(item)
     Log.i(TAG, "${list[0].answers.size}")

     answers = mutableListOf<String>()
     answearsNumber = 2
     question = "Does Kotlin use semicolons?"
     correct = "No"
     answers.add("Yes")
     answers.add("No")
     answers.shuffle()
     list.add(Item(question,answers, correct,answearsNumber))
     Log.i(TAG, "${list[1].answers.size}")

     answers = mutableListOf<String>()
     answearsNumber = 3
     question = "What is the difference between val and var in Kotlin?"
     correct = "Variables declared with val are final, those with var are not"
     answers.add("Variables declared with val can only access const members")
     answers.add("var is scoped to the nearest function block and val is scoped to the nearest enclosing block")
     answers.add("Variables declared with val are final, those with var are not")
     answers.shuffle()
     list.add(Item(question,answers, correct,answearsNumber))
     Log.i(TAG, "${list[2].answers.size}")

     answers = mutableListOf<String>()
     answearsNumber = 4
     question = "Which of these targets does Kotlin currently not support?"
     correct = ".NET CLR"
     answers.add("LLVM")
     answers.add("JavaScript")
     answers.add(".NET CLR")
     answers.add("JVM")
     answers.shuffle()
     list.add(Item(question,answers, correct,answearsNumber))
     Log.i(TAG, "${list[3].answers.size}")

     list.shuffle()
}

class QuizViewModel : ViewModel()  {

     val set = setQuestions()

     private val _uiState = MutableStateFlow(QuizState())
     val uiState : StateFlow<QuizState> = _uiState.asStateFlow()


     fun isFinalQuestion() : Boolean{
          if (uiState.value.counter == list.size) {
               return true
          }
          return false
     }

     fun incrementCounter() {
          _uiState.update { currentState ->
               currentState.copy(
                    counter = currentState.counter+1
               )

          }
     }

     fun incrementCorrectAnswers() {
          _uiState.update { currentState ->
               currentState.copy(
                    correctAnswers = currentState.correctAnswers + 1
               )

          }
     }


     fun setUserName(name:String){
          _uiState.update { currentState ->
               currentState.copy(
                    userName = name
               )
          }
     }

     fun getUserName(): String? {
          return uiState.value.userName
     }
     fun getScore(): Int{
          return uiState.value.correctAnswers
     }

     fun getAnswerNumber(): Int{
          return list[uiState.value.counter-1].answerNumber
     }

     fun getCorrectAnswer():String{
          return list[uiState.value.counter-1].correct
     }

     fun getAnswer(x: Int): String{
          return list[uiState.value.counter-1].answers[x]
     }


     fun getQuestion():String{
          return list[uiState.value.counter-1].question
     }

     fun resetCounter(){
          _uiState.update { currentState->
               currentState.copy(
                    counter = 1,
                    correctAnswers = 0
               )
          }
     }

     fun getQuestionCounter():Int{
          return uiState.value.counter
     }

     fun getHighestScore():Int{
          return uiState.value.highestScore
     }

     fun updateHighestScore(x : Int){
          _uiState.update { currentState->
               currentState.copy(
                    highestScore = x
               )
          }
     }

//     fun random(){
//          list.shuffle()
//     }
}