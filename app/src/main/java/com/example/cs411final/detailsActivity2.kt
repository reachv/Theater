package com.example.cs411final

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.cs411final.dataModel.movie
import com.example.cs411final.fragment.HomeFragment
import com.example.cs411final.fragment.detailsFragment
import com.example.cs411final.fragment.favoritesFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class detailsActivity2 : AppCompatActivity() {
    @RequiresApi(33)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details2)

        //Declaration
        var fragmentManager = supportFragmentManager
        var bottomnav : BottomNavigationView = findViewById(R.id.detailsbot)
        var fragment = Fragment()
        var favoritesFragment = favoritesFragment()
        var detailsFragment = detailsFragment()
        var homeFragment = HomeFragment()

        var res = intent.getSerializableExtra("mov", movie::class.java)

        //Switch case to move Fragments
        bottomnav.setOnItemSelectedListener {
            if(it.itemId == R.id.favorite){
                fragment = favoritesFragment
            }else if(it.itemId == R.id.details){
                var bundle = Bundle()
                bundle.putSerializable("mov", res)
                detailsFragment.arguments = bundle
                fragment = detailsFragment
            }else if(it.itemId == R.id.dhome){
                fragment = homeFragment
            }
            fragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
            return@setOnItemSelectedListener true
        }
        //Default Fragment Case
        bottomnav.selectedItemId = R.id.details
    }
}