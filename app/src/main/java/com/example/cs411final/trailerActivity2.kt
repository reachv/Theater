package com.example.cs411final

import android.os.Bundle
import android.util.Log
import android.widget.RatingBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.cs411final.dataModel.movie
import com.example.cs411final.dataModel.movieApi
import com.example.cs411final.dataModel.videoList
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class trailerActivity2 : YouTubeBaseActivity() {

    var YOUTUBE_API_KEY : String = "AIzaSyB_vq4tXUnpQs6u23YQ8hwK4inE5GdNGZs"
    @RequiresApi(33)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trailer2)

        var youtubePlayer : YouTubePlayerView = findViewById(R.id.player)
        var tvtitle  : TextView = findViewById(R.id.tTitle)
        var tvOverview : TextView = findViewById(R.id.tOverview)
        var rating : RatingBar = findViewById(R.id.tratingBar)
        var key : String
        val moviesApi = Retrofithelper.getInstance().create(movieApi::class.java)

        var mov = intent.getParcelableExtra("mov", movie::class.java)
        val call: Call<videoList>? = moviesApi.getVideo(mov!!.id)

        tvtitle.setText(mov.title)
        tvOverview.setText(mov.overview)
        rating.rating = (mov.vote_average/2).toFloat()

        call!!.enqueue(object : Callback<videoList> {
            override fun onResponse(call: Call<videoList>, response: Response<videoList>) {
                key = response.body()!!.results.get(0).key
                initializeYoutube(key, youtubePlayer)
            }

            override fun onFailure(call: Call<videoList>, t: Throwable) {
                Log.e("detailsFragment", "CallFailure: " + t)
            }
        })
    }

    @RequiresApi(33)
    private fun initializeYoutube(key: String, youtubePlayer: YouTubePlayerView) {
        youtubePlayer.initialize(YOUTUBE_API_KEY, object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(
                provider: YouTubePlayer.Provider,
                youTubePlayer: YouTubePlayer,
                b: Boolean
            ) {
                Log.d("DetailActivity", "OnSuccess")
                youTubePlayer.cueVideo(key)
            }

            override fun onInitializationFailure(
                provider: YouTubePlayer.Provider,
                youTubeInitializationResult: YouTubeInitializationResult
            ) {
                Log.d("DetailActivity", "OnFailure")
            }
        })
    }
}