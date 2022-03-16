package com.example.movvy.di.module

import androidx.lifecycle.ViewModel
import com.example.core.di.general.PerScreen
import com.example.movvy.presentation.ui.detail.DetailFragmentViewModel
import com.example.movvy.presentation.ui.mainlist.MainListViewModel
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {

    @Binds
    @PerScreen
    abstract fun getMainViewModel(mainListViewModel: MainListViewModel):ViewModel

    @Binds
    @PerScreen
    abstract fun getDetailViewModel(detailViewModel: DetailFragmentViewModel):ViewModel

}