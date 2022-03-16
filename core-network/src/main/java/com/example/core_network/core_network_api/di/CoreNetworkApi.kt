package com.example.core_network.core_network_api.di

import okhttp3.OkHttpClient
import retrofit2.Retrofit

interface CoreNetworkApi {
    fun getHttpClient(): OkHttpClient
    fun getRetrofit(): Retrofit
}