package com.example.cs411final.adapters

import android.content.Context
import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cs411final.R
import com.example.cs411final.dataModel.movie

class homeMovieadapter(var movieList : List<movie>, val context : Context, var onClickListener: OnClickListener) : RecyclerView.Adapter<homeMovieadapter.Viewholder>(){


    interface OnClickListener {
        fun onItemClicked(mov: movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.homeadapteritem, parent, false)
        return Viewholder(view)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        var mov : movie = movieList.get(position)
        holder.bind(mov, context, onClickListener)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    class Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title : TextView = itemView.findViewById(R.id.mTitle)
        val descript : TextView = itemView.findViewById(R.id.mDescription)
        val post : ImageView = itemView.findViewById(R.id.mPoster)
        val rating : RatingBar = itemView.findViewById(R.id.mRating)
        val relativeLayout : RelativeLayout = itemView.findViewById(R.id.haiC)
        lateinit var imageUrl : String


        fun bind(
            mov: movie,
            context: Context,
            onClickListener: OnClickListener,
        ) {
            title.setText(mov.title)
            descript.setText(mov.overview)
            rating.rating = (mov.vote_average / 2).toFloat()

            if(context.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
                imageUrl = String.format("https://image.tmdb.org/t/p/w342/%s", mov.backdrop_path)
            }else{
                imageUrl = String.format("https://image.tmdb.org/t/p/w342/%s", mov.poster_path)
            }

            Glide.with(context).load(imageUrl).into(post)

            relativeLayout.setOnClickListener{
                onClickListener.onItemClicked(mov)
            }


        }

    }
}