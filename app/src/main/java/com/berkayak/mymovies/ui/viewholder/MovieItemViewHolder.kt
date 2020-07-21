package com.berkayak.mymovies.ui.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.berkayak.mymovies.data.model.Movie
import com.berkayak.mymovies.utilities.Const
import com.berkayak.mymovies.utilities.loadDynamic
import kotlinx.android.synthetic.main.item_grid_movie.view.*

class MovieItemViewHolder(private val view: View): RecyclerView.ViewHolder(view) {

    fun bind(bean: Movie) {
        view.ivMovieCover.loadDynamic(Const.POSTER_BASE_PATH + bean.posterPath)
        view.tvMovieTitle.text = bean.title
    }
}