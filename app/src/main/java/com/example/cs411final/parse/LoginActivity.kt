package com.example.cs411final.parse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.bumptech.glide.Registry
import com.example.cs411final.MainActivity
import com.example.cs411final.R
import com.parse.LogInCallback
import com.parse.ParseUser

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //Persistence Check
        if(ParseUser.getCurrentUser() !== null)goMainActivity()

        //Declarations
        var username : EditText = findViewById(R.id.username)
        var password : EditText = findViewById(R.id.password)
        var log : Button = findViewById(R.id.login)
        var reg : Button = findViewById(R.id.register)

        //Logins User
        log.setOnClickListener {
            ParseUser.logInInBackground(username.text.toString(), password.text.toString(), LogInCallback { _, e ->
                if(e!=null){
                    Log.e("logInInBackground", "Exception = " + e)
                    Toast.makeText(baseContext, "Username or Password incorrect", Toast.LENGTH_SHORT).show()
                    return@LogInCallback
                }
                goMainActivity()
            })
        }

        //Switch from Login to Register
        reg.setOnClickListener {
            goRegistryActivity()
        }

    }

    private fun goRegistryActivity() {
        val intent = Intent(this, RegistryActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun goMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}