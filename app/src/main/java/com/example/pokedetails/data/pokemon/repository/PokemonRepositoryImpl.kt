package com.example.pokedetails.data.pokemon.repository

import com.example.pokedetails.data.pagination.models.paginatedresponse.PaginatedResponse
import com.example.pokedetails.network.api.PokemonApi
import com.example.pokedetails.network.utils.SafeApiCall
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val safeApiCall: SafeApiCall,
    private val pokemonApi: PokemonApi
) : PokemonRepository {

    override suspend fun getPokemons(): Result<PaginatedResponse> {
        return safeApiCall.execute {
            pokemonApi.getPokemons()
        }
    }
}