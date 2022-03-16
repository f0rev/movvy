package com.example.movvy.presentation.ui.mainlist

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.core_utill.viewmodel.Factory
import com.example.movvy.R
import com.example.movvy.di.module.ApplicationComponent.Companion.get
import com.example.movvy.presentation.ui.mainlist.adapters.CarouselAdapterWithTitle
import com.example.movvy.presentation.ui.mainlist.adapters.DefaultPagerAdapter
import com.example.movvy.presentation.ui.mainlist.adapters.MovieItemAdapter
import com.example.movvy.presentation.ui.mainlist.adapters.MoviePagerAdapter
import kotlinx.coroutines.flow.collect


class MainListFragment : Fragment(R.layout.layout_movies_list), MovieClickCallback {

    private val doggyViewModel: MainListViewModel by navGraphViewModels(R.id.nav_graph) {
        Factory{
            get().mainScreenComponent().getMainListViewModel()
        }
    }
    lateinit var mainRecyclerView: RecyclerView
    lateinit var concatAdapter: ConcatAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainRecyclerView = view.findViewById(R.id.recyclerView)
        concatAdapter = ConcatAdapter()

        val popularMovieItemAdapter = MovieItemAdapter(requireContext(), this)
        val popularMoviesCorouselAdapter = CarouselAdapterWithTitle("Популярные", "фильмы").also {
            it.setNestedAdapter(popularMovieItemAdapter)
        }

        val topRatedItemAdapter = MovieItemAdapter(requireContext(), this)
        val topRatedMoviesCorouselAdapter = CarouselAdapterWithTitle("Рейтинговые", "фильмы").also {
            it.setNestedAdapter(topRatedItemAdapter)
        }

        val moviePagerAdapter = MoviePagerAdapter(requireContext(), this)
        val defaultPagerAdapter =
            DefaultPagerAdapter(true).also { it.setNestedAdapter(moviePagerAdapter) }


        mainRecyclerView.adapter = concatAdapter.also {
            it.addAdapter(defaultPagerAdapter)
            it.addAdapter(popularMoviesCorouselAdapter)
            it.addAdapter(topRatedMoviesCorouselAdapter)
        }


        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            doggyViewModel
                .popularMovies
                .collect {
                    popularMovieItemAdapter.submitData(it)
                }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            doggyViewModel
                .topRatedMovies
                .collect {
                    topRatedItemAdapter.submitData(it)
                }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            doggyViewModel
                .upcomingMovies
                .collect {
                    moviePagerAdapter.submitData(it)
                }
        }

    }

    override fun onItemClick(id: Int) {
        Navigation.findNavController(requireView()).navigate(
            R.id.action_home_to_detailFragment,
            bundleOf("movie_id" to id)
        )
    }

    override fun onItemFavoriteClick(id: Int) {
    }


}