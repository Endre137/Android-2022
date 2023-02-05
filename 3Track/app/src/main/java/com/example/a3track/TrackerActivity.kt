package com.example.a3track

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.a3track.fragment.MyProfileFragment
import com.example.a3track.fragment.MyTasksFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class TrackerActivity : AppCompatActivity() {

    lateinit var bottomNav : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tracker)
        bottomNav = findViewById(R.id.bottomNav) as BottomNavigationView
        bottomNav.setOnItemSelectedListener {
            when(it.itemId){
//                R.id.activities -> {
//                    Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.)
//                }
                R.id.myTasks -> {
                    loadFragment(MyTasksFragment())
                    true
                }
                R.id.profile ->{
                    loadFragment(MyProfileFragment())
                    true
                }
                else ->{
                    true
                }

            }
        }
    }
    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.commit()
    }

}