package com.example.cs411final.parse

import android.app.Application
import com.parse.Parse
import com.parse.ParseObject

class parseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Parse.initialize(
            Parse.Configuration.Builder(this)
                .applicationId("Knogl3QNo4lcCAwq1sbT1dNoKuUXSU0iMpxKJw73")
                .clientKey("NoJoB4y1xoO4ZKIyqFjQBfKyTZTtmiXvcv56rIHp")
                .server("https://parseapi.back4app.com")
                .build()
        )

    }
}