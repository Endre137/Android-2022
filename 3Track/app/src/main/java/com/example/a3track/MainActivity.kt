package com.example.a3track


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.WindowManager
import androidx.navigation.findNavController
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.setFlags(                                                    //make the splash screen as a full screen activity
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val controller = findNavController(R.id.nav_host_fragment)

        val prefs = this.getPreferences(MODE_PRIVATE)
        val token = prefs.getString("token", "")
        val deadline = prefs.getLong("deadline", 0L)

        Log.i("xxx", "token: " + token)
        // @TODO - check the token's validity
        var isValid : Boolean


        if(Date().time < deadline){
            isValid = true;
        }else{
            isValid = false

            Handler(Looper.getMainLooper()).postDelayed({
                controller.navigate(R.id.loginFragment)
            }, 3000)
        }

        if (!token.equals("") && isValid ) {
            MyApplication.token = token!!
            MyApplication.email = prefs.getString("email","")!!

            Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this,TrackerActivity::class.java))
            }, 3000)
        }
    }
}