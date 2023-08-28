package com.example.cs411final.perservation

import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStoreFile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

class MyPreferencesRepository constructor(private val dataStore : DataStore<Preferences>){

    val emailt: Flow<String> = this.dataStore.data.map { prefs ->
        prefs[email] ?: ""
    }.distinctUntilChanged()




    private suspend fun saveStringValue(key: Preferences.Key<String>, value: String) {
        this.dataStore.edit { prefs ->
            prefs[key] = value.toString()
        }
    }

    suspend fun saveInput(value: String, index:Int){
        val key: Preferences.Key<String> = when(index){

            1 -> email

            else ->{
                throw  NoSuchFieldException("Invalid input index:  $index")
            }
        }
        this.saveStringValue(key, value)
    }
    companion object{
        private const val PREFERENCE_FILE_NAME = "settings"

        private val email = stringPreferencesKey("email")

        private var instance : MyPreferencesRepository? = null

        fun initialize(context : Context){
            if(instance == null){
                val dataStore = PreferenceDataStoreFactory.create {
                    context.preferencesDataStoreFile(PREFERENCE_FILE_NAME)
                }
                instance = MyPreferencesRepository(dataStore)
            }
        }
        fun get(): MyPreferencesRepository{
            return instance?:throw IllegalStateException("MyPreferencesRepository has not been initialized")
        }
    }
}