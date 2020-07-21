package com.berkayak.mymovies.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toolbar
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.berkayak.mymovies.R
import com.berkayak.mymovies.core.MovieApplication
import com.berkayak.mymovies.core.di.component.MovieListComponent
import com.berkayak.mymovies.core.di.module.MovieListModule
import com.berkayak.mymovies.core.di.scope.MovieListScope
import com.berkayak.mymovies.data.model.GenericResponse
import com.berkayak.mymovies.data.model.MovieListResponse
import com.berkayak.mymovies.ui.fragment.MovieListFragment
import com.berkayak.mymovies.utilities.Const
import com.berkayak.mymovies.utilities.startFragment
import com.berkayak.mymovies.viewmodel.MovieListViewModel
import com.berkayak.mymovies.viewmodel.factory.MovieListViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    startFragment(MovieListFragment.newInstance(), MovieListFragment.TAG)

    setSupportActionBar(findViewById(R.id.toolbar))
    toolbar.findViewById<ImageView>(R.id.ivBackBtn)?.setOnClickListener {
      onBackPressed()
    }
  }

  override fun onBackPressed() {
    if(supportFragmentManager.backStackEntryCount > 1) {
      super.onBackPressed()
    } else {
      finish()
    }
  }
}