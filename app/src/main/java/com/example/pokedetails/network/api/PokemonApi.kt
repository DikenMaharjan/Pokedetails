package com.example.pokedetails.network.api

import com.example.pokedetails.data.pagination.pokemon.paginatedresponse.PaginatedResponse
import com.example.pokedetails.data.pokemon.models.dto.pokemondetaildto.PokemonDetailDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface PokemonApi {

    @GET("pokemon")
    suspend fun getPokemons(): Response<PaginatedResponse>

    @GET
    suspend fun getPokemons(@Url url: String): Response<PaginatedResponse>

    @GET
    suspend fun getPokemonDetail(@Url detailUrl: String): Response<PokemonDetailDto>

}