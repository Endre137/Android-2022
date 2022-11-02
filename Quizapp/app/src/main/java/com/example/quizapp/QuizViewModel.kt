import android.content.ClipData.Item
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


data class QuizState(
     val MAX_QUESTIONS : Int = 5,
     val counter :Int = 1,
     val userName : String? = null,
     val question : String? = null,
     val answer1 : String? = null,
     val answer2 : String? = null,
     val answer3 : String? = null,
     val answer4 : String? = null,
     val correctAnswers : Int = 0
)

class QuizViewModel : ViewModel() {

     private val _uiState = MutableStateFlow(QuizState())
     val uiState : StateFlow<QuizState> = _uiState.asStateFlow()

     fun isFinalQuestion() : Boolean{
          if (uiState.value.counter == uiState.value.MAX_QUESTIONS) {
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

     fun getCorrectAnswer():Int{
          when(uiState.value.counter){
               1->return 2
               2-> return 3
               3->return 3
               4-> return 1
               5-> return 4
          }
          return 0
     }

     fun getAnswer1():String{
          when(uiState.value.counter){
               1->return "Variables declared with var are final, those with val are not."
               2->return "An auto-generated toString() method."
               3->return "JVM"
               4->return "They provide asynchronous code without thread blocking."
               5->return "int i = 42"
          }
          return "ERROR"
     }
     fun getAnswer2():String{
          when(uiState.value.counter){
               1->return "Variables declared with val are final, those with var are not."
               2->return "Auto-generated hashcode() and equals() methods."
               3->return "LLVM"
               4->return "It's Kotlin's term for class methods."
               5->return "let i = 42"
          }
          return "ERROR"
     }
     fun getAnswer3():String{
          when(uiState.value.counter){
               1->return "var is scoped to the nearest function block and val is scoped to the nearest enclosing block."
               2->return "Automatic conversion from/to JSON."
               3->return ".NET CLR"
               4->return "These are functions which accept other functions as arguments or return them."
               5->return "var i : int = 42"
          }
          return "ERROR"
     }
     fun getAnswer4():String{
          when(uiState.value.counter){
               1->return "Variables declared with val can only access const members."
               2->return "A generated copy method, to create copies of instances."
               3->return "JavaScript"
               4->return "That's how the automatically generated methods hashCode() and equals() in data classes are called."
               5->return "var i : Int = 42"
          }
          return "ERROR"
     }
     fun getQuestion():String{
          when(uiState.value.counter){
               1->return "What is the difference between val and var in Kotlin?"
               2->return "What does a data class not offer?"
               3->return "Which of these targets does Kotlin currently not support?"
               4->return "What are Kotlin coroutines?"
               5->return "What is the correct way to declare a variable of integer type in Kotlin?"
          }
          return "ERROR"
     }
     fun resetCounter(){
          _uiState.update { currentState->
               currentState.copy(
                    counter = 1,
                    correctAnswers = 0
               )
          }
     }


}