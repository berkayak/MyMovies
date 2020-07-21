package com.berkayak.mymovies.utilities

import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.berkayak.mymovies.R
import com.berkayak.mymovies.ui.MainActivity
import com.berkayak.mymovies.ui.fragment.MovieListFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

fun ImageView.loadDynamic(url: String) {
    Picasso
        .with(this.context)
        .load(url)
        .placeholder(R.drawable.movie_placeholder)
        .error(R.drawable.movie_placeholder)
        .fit()
        .centerCrop()
        .into(this)
}

fun FragmentActivity.startFragment(fragment: Fragment, tag: String) {
    this.supportFragmentManager.beginTransaction().add(
        R.id.flFragmentContainer,
        fragment,
        tag
    )
        .addToBackStack(tag)
        .commit()
}

fun MainActivity.setHeader(headerText: String){
    toolbar.findViewById<TextView>(R.id.tvToolbarHeader).text = headerText
}