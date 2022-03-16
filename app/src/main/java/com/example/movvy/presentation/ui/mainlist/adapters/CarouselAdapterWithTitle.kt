package com.example.movvy.presentation.ui.mainlist.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movvy.R
import com.example.movvy.databinding.LayoutCorouselWithTitleItemBinding


const val KEY_SCROLL_X = "key_scroll_x"

class CarouselAdapterWithTitle(
    private val title: String,
    private val subtitle: String
) : RecyclerView.Adapter<CarouselAdapterWithTitle.CorouselWithTitleViewHolder>() {
    private var lastScrollX = 0
    private var nestedAdapter: MovieItemAdapter? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CorouselWithTitleViewHolder {
        return CorouselWithTitleViewHolder(
            LayoutCorouselWithTitleItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CorouselWithTitleViewHolder, position: Int) {
        holder.bind(title, subtitle, adapter = nestedAdapter, lastScrollX) {
            lastScrollX = it
        }
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.layout_corousel_with_title_item
    }

    fun onSaveState(outState: Bundle) {
        outState.putInt(KEY_SCROLL_X, lastScrollX)
    }

    fun onRestoreState(savedState: Bundle) {
        lastScrollX = savedState.getInt(KEY_SCROLL_X)
    }

    fun setNestedAdapter(adapter: MovieItemAdapter) {
        this.nestedAdapter = adapter
        this.notifyDataSetChanged()
    }


    inner class CorouselWithTitleViewHolder(
        private val binding: LayoutCorouselWithTitleItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            title: String,
            subtitle: String,
            adapter: MovieItemAdapter?,
            lastScrollX: Int,
            onScrolled: (Int) -> Unit
        ) {
            binding.corouselTitle.text = title
            binding.corouselSubTitle.text = subtitle
            val context = binding.root.context
            binding.horizontalItemRv.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            binding.horizontalItemRv.adapter = adapter
            binding.horizontalItemRv.doOnPreDraw {
                binding.horizontalItemRv.scrollBy(lastScrollX, 0)
            }
        }
    }


}