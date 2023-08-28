package com.example.cs411final

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.cs411final.fragment.HomeFragment
import com.example.cs411final.fragment.favoritesFragment
import com.example.cs411final.fragment.settingsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Declaration
        var bottomnav : BottomNavigationView = findViewById(R.id.Botnav)
        var fragmentManager = supportFragmentManager
        var fragment = Fragment()
        var homeFragment = HomeFragment()
        var favoritesFragment = favoritesFragment()
        var settingFragment = settingsFragment()

        //Switch case to move Fragments
        bottomnav.setOnItemSelectedListener {
            if(it.itemId == R.id.home){
                fragment = homeFragment
            }else if(it.itemId == R.id.bfavor){
                fragment = favoritesFragment
            }else if(it.itemId == R.id.settings){
                fragment = settingFragment
            }
            fragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
            return@setOnItemSelectedListener true
        }
        //Default Fragment Case
        bottomnav.selectedItemId = R.id.home

    }
}