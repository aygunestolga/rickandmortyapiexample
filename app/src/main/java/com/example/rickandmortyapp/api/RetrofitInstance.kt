package com.example.rickandmortyapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val baseUrl = "https://rickandmortyapi.com";

object RetrofitInstance {
    private fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
        .build()
    }
    val rickandmortyApi : RickAndMortyapi = getInstance().create(RickAndMortyapi::class.java)
}