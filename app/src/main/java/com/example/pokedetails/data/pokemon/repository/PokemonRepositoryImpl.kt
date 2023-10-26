package com.example.pokedetails.data.pokemon.repository

import com.example.pokedetails.data.pagination.models.paginatedresponse.PaginatedResponse
import com.example.pokedetails.network.PokemonApi
import retrofit2.Response
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonApi: PokemonApi
) : PokemonRepository {
    override suspend fun getPokemons(): Response<PaginatedResponse> {
        return pokemonApi.getPokemons()
    }
}