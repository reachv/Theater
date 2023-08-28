package com.example.cs411final.fragment

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.cs411final.R
import com.example.cs411final.dataModel.movie
import com.example.cs411final.trailerActivity2
import com.parse.ParseUser


class detailsFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @RequiresApi(33)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view : View = inflater.inflate(R.layout.fragment_details, container, false)

        var dbut : Button = view.findViewById(R.id.dbut)
        var tvtitle  : TextView = view.findViewById(R.id.dTitle)
        var tvOverview : TextView = view.findViewById(R.id.dOverview)
        var rating : RatingBar = view.findViewById(R.id.dratingBar)
        var poster : ImageView = view.findViewById(R.id.dposter)
        var button : Button = view.findViewById(R.id.fAdd)

        var bundle : Bundle = this.requireArguments()
        var mov : movie = bundle.getSerializable("mov", movie::class.java)!!

        dbut.setOnClickListener {
            var intent = Intent(context, trailerActivity2::class.java)
            intent.putExtra("mov", mov)
            startActivity(intent)
        }
        tvtitle.setText(mov.title)
        tvOverview.setText(mov.overview)
        rating.rating = (mov.vote_average/2).toFloat()

        var imageUrl : String
        if(requireContext().resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            imageUrl = String.format("https://image.tmdb.org/t/p/w342/%s", mov.backdrop_path)
        }else{
            imageUrl = String.format("https://image.tmdb.org/t/p/w342/%s", mov.poster_path)
        }

        Glide.with(requireContext()).load(imageUrl).into(poster)

        button.setOnClickListener {
            var temp = ArrayList<Int>()
            if(ParseUser.getCurrentUser().getList<Int>("favorites") != null){
                temp.addAll(ParseUser.getCurrentUser().getList<Int>("favorites")!!)
            }
            for(x in temp){
                if(x == mov.id){
                    Toast.makeText(context, "This movie already exists in favorites", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }
            temp.add(mov.id)
            var user = ParseUser.getCurrentUser()
            user.put("favorites", temp)
            user.saveInBackground {
                if(it != null){
                    Log.e("detailsFragment", "SaveException: " + it)
                    return@saveInBackground
                }
                Toast.makeText(context, "Successfully saved", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}