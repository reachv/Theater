package com.example.cs411final.parse

import android.app.Application
import com.parse.Parse
import com.parse.ParseObject

class parseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Parse.initialize(
            Parse.Configuration.Builder(this)
                .applicationId("")
                .clientKey("")
                .server("https://parseapi.back4app.com")
                .build()
        )

    }
}