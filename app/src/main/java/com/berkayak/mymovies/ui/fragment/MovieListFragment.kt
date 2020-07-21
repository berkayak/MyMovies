package com.berkayak.mymovies.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.berkayak.mymovies.R
import com.berkayak.mymovies.core.MovieApplication
import com.berkayak.mymovies.core.di.component.MovieListComponent
import com.berkayak.mymovies.core.di.module.MovieListModule
import com.berkayak.mymovies.data.model.GenericResponse
import com.berkayak.mymovies.data.model.Movie
import com.berkayak.mymovies.ui.MainActivity
import com.berkayak.mymovies.ui.adapter.MovieGridAdapter
import com.berkayak.mymovies.utilities.Const
import com.berkayak.mymovies.utilities.setHeader
import com.berkayak.mymovies.utilities.startFragment
import com.berkayak.mymovies.viewmodel.MovieListViewModel
import com.berkayak.mymovies.viewmodel.factory.MovieListViewModelFactory
import kotlinx.android.synthetic.main.fragment_movie_list.*
import javax.inject.Inject


class MovieListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: MovieListViewModelFactory
    val viewModel: MovieListViewModel by viewModels { viewModelFactory }
    var component: MovieListComponent? = null

    private var currentPage = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component = (context?.applicationContext as MovieApplication)
            .appComponent
            .movieListComponent()
            .module(
                MovieListModule()
            )
            .create()
        component?.inject(this)
        movieListObserver()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMovieList(currentPage)
        setLoadMoreButton()
        setSearchButton()
        (activity as? MainActivity)?.setHeader(getString(R.string.popular_movies))
    }

    private fun movieListObserver() {
        viewModel.movieList.observe(this, Observer { response ->
            when (response) {
                is GenericResponse.Success -> {
                    response.result?.results?.let { setMovieAdapter(it) }
                }
                is GenericResponse.Failure -> {
                    Log.d(Const.LOG_TAG, "Failure: ${response.message}")
                }
                is GenericResponse.Error -> {
                    Log.d(Const.LOG_TAG, "Error: ${response.exception.localizedMessage}")
                }
            }
            btnLoadMore.isEnabled = true
        })
    }

    override fun onDestroy() {
        component = null
        super.onDestroy()
    }

    private fun setMovieAdapter(list: List<Movie>) {
        (rvMovies.adapter as? MovieGridAdapter)?.let { movieGridAdapter ->
            movieGridAdapter.appendData(list)
        } ?: run {
            rvMovies.layoutManager = GridLayoutManager(requireContext(), 2)
            rvMovies.adapter = MovieGridAdapter(requireContext(), list, ::onMovieItemClick)
        }
    }

    private fun setLoadMoreButton() {
        btnLoadMore.setOnClickListener {
            currentPage++
            viewModel.getMovieList(currentPage)
            it.isEnabled = false
        }
    }

    private fun setSearchButton() {
        etSearch.addTextChangedListener {editableText ->
            (rvMovies.adapter as? MovieGridAdapter)?.searchTerm = editableText?.toString()
        }
    }

    private fun onMovieItemClick(movie: Movie) {
        activity?.startFragment(MovieDetailFragment.newInstance(movie), MovieDetailFragment.TAG)
    }

    companion object {
        @JvmStatic
        fun newInstance() = MovieListFragment()

        const val TAG = "MovieListFragment"
    }
}