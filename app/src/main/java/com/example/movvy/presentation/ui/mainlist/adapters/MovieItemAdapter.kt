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
import com.example.movvy.api.MoviesApi.Companion.IMAGE_BASE_URL
import com.example.movvy.data.model.Movie
import com.example.movvy.databinding.MoviesItemBinding
import com.example.movvy.presentation.ui.mainlist.MovieClickCallback

class MovieItemAdapter(
    private val context: Context,
    private val movieClickCallback: MovieClickCallback
) :
    PagingDataAdapter<Movie, MovieItemAdapter.MovieItemHolder>(MOVIE_DIFF_UTIL) {

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


    override fun onBindViewHolder(holder: MovieItemHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemHolder {
        return MovieItemHolder(
            MoviesItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    inner class MovieItemHolder(private val view: MoviesItemBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bind(movie: Movie?) {
            view.movieName.text = movie?.title
            Glide.with(context)
                .load(IMAGE_BASE_URL.plus(movie?.poster_path))
                .apply(RequestOptions().also { it.transform(RoundedCorners(16)) })
                .into(view.movieImage)
            view.root.setOnClickListener {
                movieClickCallback.onItemClick(movie?.id!!)
            }

        }

    }


}