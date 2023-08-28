package com.example.cs411final.perservation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cs411final.MainActivity
import com.example.cs411final.fragment.settingsFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MyViewModel : ViewModel(){
    private val prefs = MyPreferencesRepository.get()

    fun saveInput(s:String, index:Int){
        viewModelScope.launch {
            prefs.saveInput(s, index)
            Log.v("MyViewModel", "Done Saving input #$index")
        }
    }

    fun loadInputs(act: settingsFragment){
        viewModelScope.launch {
            prefs.emailt.collectLatest {
                act.email.setText(it)
            }
        }
    }

}