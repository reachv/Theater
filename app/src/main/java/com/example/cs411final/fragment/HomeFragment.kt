package com.example.cs411final.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cs411final.R
import com.example.cs411final.Retrofithelper
import com.example.cs411final.adapters.homeMovieadapter
import com.example.cs411final.dataModel.movie
import com.example.cs411final.dataModel.movieApi
import com.example.cs411final.dataModel.movieList
import com.example.cs411final.detailsActivity2
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(R.layout.fragment_home, container, false)

        var recylcer: RecyclerView = view.findViewById(R.id.homeRV)
        val moviesApi = Retrofithelper.getInstance().create(movieApi::class.java)
        var movies = ArrayList<movie>()
        var adapter: homeMovieadapter

        var onClickListener = object : homeMovieadapter.OnClickListener{
            override fun onItemClicked(mov: movie) {
                var intent = Intent(context,detailsActivity2::class.java)
                intent.putExtra("mov", mov)
                startActivity(intent)

            }

        }


        adapter = homeMovieadapter(movies, requireContext(), onClickListener)
        recylcer.adapter = adapter
        recylcer.layoutManager = LinearLayoutManager(context)

        val call: Call<movieList>? = moviesApi.getMovies()
        call!!.enqueue(object : Callback<movieList> {
            override fun onResponse(call: Call<movieList>, response: Response<movieList>) {
                if (response.isSuccessful()) {
                    movies.addAll(response.body()!!.results)
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<movieList>, t: Throwable) {
                Log.e("HomeFragment", "Failed Response: " + t.toString())
            }

        })


        return view
    }
}