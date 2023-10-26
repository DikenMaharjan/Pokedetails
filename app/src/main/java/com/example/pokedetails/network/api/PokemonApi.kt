package com.example.pokedetails.network.api

import com.example.pokedetails.data.pagination.models.paginatedresponse.PaginatedResponse
import retrofit2.Response
import retrofit2.http.GET

interface PokemonApi {

    @GET("pokemon")
    suspend fun getPokemons(): Response<PaginatedResponse>

}