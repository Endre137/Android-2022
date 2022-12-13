package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)

        val topAppBar = findViewById<MaterialToolbar>(R.id.topAppBar)
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        val navigationView = findViewById<NavigationView>(R.id.navigationView)

        topAppBar.setNavigationOnClickListener {

            drawerLayout.open()
        }

        navigationView.setNavigationItemSelectedListener { menuItem ->
            // Handle menu item selected
            when (menuItem.itemId) {
                R.id.HomeFragment -> {
                    // navigate to home screen
                   Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.HomeFragment)
              }
                R.id.quiz -> {
                    Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.startFragment)
                }
                R.id.profile -> {
                    Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.profileFragment)
                }

                R.id.list -> {
                    Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.listOfQuestions)
                }
//                R.id.add -> {
//                    //Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.questionAddFragment)
//                }
                else -> super.onOptionsItemSelected(menuItem)

            }
            menuItem.isChecked = true
            drawerLayout.close()
            true
        }

    }
    override fun onBackPressed() {
        Toast.makeText(this, "Disabled Back Press", Toast.LENGTH_SHORT).show()
    }
}