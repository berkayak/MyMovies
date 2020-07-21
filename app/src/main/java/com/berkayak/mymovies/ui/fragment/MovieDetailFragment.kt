package com.berkayak.mymovies.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.berkayak.mymovies.R
import com.berkayak.mymovies.data.model.Movie
import com.berkayak.mymovies.ui.MainActivity
import com.berkayak.mymovies.utilities.Const
import com.berkayak.mymovies.utilities.loadDynamic
import com.berkayak.mymovies.utilities.setHeader
import kotlinx.android.synthetic.main.fragment_movie_detail.*

class MovieDetailFragment: Fragment() {

    private var currentMovie: Movie? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadMovie()
        (activity as? MainActivity)?.setHeader(currentMovie?.title ?: "")
    }

    private fun loadMovie() {
        currentMovie?.let { safeMovie ->
            ivPoster.loadDynamic(Const.POSTER_BASE_PATH + safeMovie.posterPath)
            tvDescription.text = safeMovie.overview
            tvPopularity.text = safeMovie.popularity.toString()
            tvScore.text = safeMovie.voteAverage.toString()
        }
    }

    companion object {
        fun newInstance(movie: Movie) = MovieDetailFragment().apply { currentMovie = movie }

        const val TAG = "MovieDetailFragment"
    }
}