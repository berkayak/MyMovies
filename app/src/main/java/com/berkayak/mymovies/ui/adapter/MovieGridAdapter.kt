package com.berkayak.mymovies.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.berkayak.mymovies.R
import com.berkayak.mymovies.data.model.Movie
import com.berkayak.mymovies.ui.viewholder.MovieItemViewHolder

class MovieGridAdapter(
    private val context: Context,
    movieList: List<Movie>,
    private val onClickItem: ((movie: Movie) -> Unit)? = null
): RecyclerView.Adapter<MovieItemViewHolder>() {

    private var movieList = movieList.toMutableList()
    private var filteredList = this.movieList

    var searchTerm: String? = null
    set(value) {
        if (value?.length?.compareTo(2) == 1) {
            field = value
            field?.let { safeFilter ->
                filteredList = movieList.filter { filtered ->
                    filtered.title.toLowerCase().contains(safeFilter.toLowerCase())
                }.toMutableList()
            }
            notifyDataSetChanged()
        } else {
            field == null
            filteredList = movieList
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder =
        MovieItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_grid_movie, parent, false))

    override fun getItemCount(): Int = filteredList.size

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        holder.bind(filteredList[position])
        holder.itemView.setOnClickListener {
            onClickItem?.invoke(filteredList[position])
        }
    }

    fun appendData(movieList: List<Movie>) {
        this.movieList.addAll(movieList)
        notifyDataSetChanged()
    }
}