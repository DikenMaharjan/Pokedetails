package com.example.pokedetails.data.pokemon.repository

import com.example.pokedetails.data.pagination.models.paginatedresponse.PaginatedResponse
import retrofit2.Response
import retrofit2.http.GET

interface PokemonRepository {

    @GET("pokemon")
    suspend fun getPokemons(): Response<PaginatedResponse>
}