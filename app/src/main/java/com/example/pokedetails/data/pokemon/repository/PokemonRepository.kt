package com.example.pokedetails.data.pokemon.repository

import com.example.pokedetails.data.pokemon.models.domain.PokemonShortDetail
import retrofit2.http.GET

interface PokemonRepository {

    @GET("pokemon")
    suspend fun getPokemons(): Result<List<PokemonShortDetail>>

}