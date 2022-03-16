package com.example.movvy.presentation.ui.mainlist.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.movvy.R
import com.example.movvy.api.MoviesApi
import com.example.movvy.data.model.Movie
import com.example.movvy.databinding.MoviePagerItemBinding
import com.example.movvy.presentation.ui.mainlist.MovieClickCallback


class MoviePagerAdapter(
    val context: Context,
    private val movieClickCallback: MovieClickCallback
) :
    PagingDataAdapter<Movie, MoviePagerAdapter.MoviePagerViewHolder>(MOVIE_DIFF_UTIL) {

    companion object {
        val MOVIE_DIFF_UTIL = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviePagerViewHolder {
        return MoviePagerViewHolder(
            MoviePagerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MoviePagerViewHolder, position: Int) {
        return holder.bind(getItem(position))
    }


    override fun getItemViewType(position: Int): Int {
        return R.layout.movie_pager_item
    }


    inner class MoviePagerViewHolder(private val view: MoviePagerItemBinding) :
        RecyclerView.ViewHolder(view.root) {

        fun bind(movie: Movie?) {
            view.title.text = movie?.title
            Glide.with(context)
                .load(MoviesApi.IMAGE_BASE_URL.plus(movie?.poster_path))
                .apply(RequestOptions().also { it.transform(RoundedCorners(16)) })
                .into(view.moviePoster)

            Glide.with(context)
                .load(MoviesApi.IMAGE_BASE_URL.plus(movie?.poster_path))
                .apply(RequestOptions().also { it.transform(RoundedCorners(16)) })
                .into(view.backgroundPoster)
            view.root.setOnClickListener {
                movieClickCallback.onItemClick(movie?.id!!)
            }

        }


    }


}