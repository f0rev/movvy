package com.example.movvy.presentation.ui.mainlist.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movvy.R
import com.example.movvy.databinding.LayoutCarouselBinding


class DefaultPagerAdapter(val autoScrollable: Boolean) :
    RecyclerView.Adapter<DefaultPagerAdapter.CorouselWithTitleViewHolder>() {
    private var lastScrollX = 0
    private var nestedAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CorouselWithTitleViewHolder {
        return CorouselWithTitleViewHolder(
            LayoutCarouselBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CorouselWithTitleViewHolder, position: Int) {
        holder.bind(adapter = nestedAdapter)
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.layout_carousel
    }

    fun onSaveState(outState: Bundle) {
        outState.putInt(KEY_SCROLL_X, lastScrollX)
    }

    fun onRestoreState(savedState: Bundle) {
        lastScrollX = savedState.getInt(KEY_SCROLL_X)
    }

    fun setNestedAdapter(adapter: RecyclerView.Adapter<out RecyclerView.ViewHolder?>) {
        this.nestedAdapter = adapter as RecyclerView.Adapter<RecyclerView.ViewHolder>
        this.notifyDataSetChanged()
    }


    inner class CorouselWithTitleViewHolder(
        private val binding: LayoutCarouselBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>?,
        ) {
            val context = binding.root.context
            binding.carousel.adapter = adapter
            if (autoScrollable)
                PagerSrollBehavior(binding.carousel).initAutoScrollBehavior()
        }
    }
}