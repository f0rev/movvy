package com.example.movvy.di.module

import com.example.core.di.general.PerScreen
import com.example.movvy.presentation.ui.detail.DetailFragmentViewModel
import com.example.movvy.presentation.ui.mainlist.MainListViewModel
import dagger.Subcomponent

@Subcomponent(modules = [ViewModelModule::class])
@PerScreen
interface MainScreenComponent {

    fun getMainListViewModel(): MainListViewModel
    fun getDetailViewModel():DetailFragmentViewModel


}