package com.example.cs411final.fragment

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.cs411final.R
import com.example.cs411final.parse.LoginActivity
import com.example.cs411final.passwordActivity
import com.example.cs411final.perservation.MyPreferencesRepository
import com.example.cs411final.perservation.MyViewModel
import com.parse.ParseUser
import org.w3c.dom.Text


class settingsFragment : Fragment() {
    lateinit var email : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    private val myViewModel: MyViewModel by lazy {
        MyPreferencesRepository.initialize(requireContext())
        ViewModelProvider(this)[MyViewModel::class.java]
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_settings, container, false)

        var user = view.findViewById<EditText>(R.id.settingsUser)
        var pass = view.findViewById<TextView>(R.id.settingsPassword)
        var logout : Button = view.findViewById(R.id.logout)
        email  = view.findViewById(R.id.email)

        email.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                myViewModel.saveInput(email.text.toString(), 1)
            }

        })

        user.setText(ParseUser.getCurrentUser().username)
        user.addTextChangedListener(object : TextWatcher {
            lateinit var before : String
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                before = s.toString()
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                //Queries usernames
                var query = ParseUser.getQuery()
                query.findInBackground { objects, e ->
                    if(e != null){
                        Log.e("SettingsQueryException: ", e.toString())
                        return@findInBackground
                    }
                    for(i in objects){
                        if(user.equals(i.username)){
                            Toast.makeText(context, "Username already exists", Toast.LENGTH_SHORT).show()
                            return@findInBackground
                        }
                    }
                    //Changes username
                    var pUser = ParseUser.getCurrentUser()
                    pUser.username = user.text.toString()
                    pUser.saveInBackground {
                        if(it != null){
                            Log.e("SettingsSaveException: " , it.toString())
                            return@saveInBackground
                        }
                        Toast.makeText(context, "Successfully changed username", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        })
        //Logs user out
        logout.setOnClickListener {
            ParseUser.logOutInBackground {
                if(it != null){
                    Log.e("SettingFragmentsLogoutException: ", "" + it)
                    return@logOutInBackground
                }
                val intent = Intent(context, LoginActivity::class.java)
                startActivity(intent)
            }
        }

        //Change password activity
        pass.setOnClickListener {
            val intent = Intent(context, passwordActivity::class.java)
            startActivity(intent)
        }

        this.myViewModel.loadInputs(this)
        return view
    }

}