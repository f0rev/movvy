package com.example.movvy.presentation.ui.mainlist

interface MovieClickCallback {

    fun onItemClick(id: Int)

    fun onItemFavoriteClick(id: Int)
}