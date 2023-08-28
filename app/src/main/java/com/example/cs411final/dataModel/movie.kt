package com.example.cs411final.dataModel

import android.os.Parcelable
import com.parse.ParseClassName
import com.parse.ParseObject
import com.parse.ParseUser
import java.io.Serializable


data class movie(var title : String, var poster_path : String, var overview : String, var backdrop_path : String, var id : Int, var vote_average : Double) : Serializable{

}
