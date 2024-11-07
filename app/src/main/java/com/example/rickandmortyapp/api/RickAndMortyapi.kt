package com.example.rickandmortyapp.api

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface RickAndMortyapi {
    @GET("api/character")
    suspend fun getCharacter(

    ) : Response<RickAndMortyModel>
}