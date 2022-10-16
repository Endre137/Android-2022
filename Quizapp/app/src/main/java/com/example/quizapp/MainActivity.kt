package com.example.quizapp

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"
    lateinit var startButton : Button
    lateinit var messageTextInput : TextInputEditText


//     val getContact =
//        registerForActivityResult(ActivityResultContracts.PickContact()){ result ->
//                if(result. == Activity.RESULT_OK){
//
//                }
//            var name = result.authority.toString()
//        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

       Log.i(TAG,"onStart() called")
        messageTextInput = findViewById(R.id.textInputEditText)
        initViewItems()
        registerListeners()

//        val intent = Intent(context, TargetActivity::class.java)
//        getContact.launch(null)


    }
    private fun registerListeners(){
        startButton.setOnClickListener{
            val name = messageTextInput.text.toString()
            val intent = Intent(this,MainActivity2:: class.java)
            intent.putExtra("message_key", name)
            startActivity(intent)
        }
    }


    private fun initViewItems(){
        startButton = findViewById(R.id.startButton)
    }

}