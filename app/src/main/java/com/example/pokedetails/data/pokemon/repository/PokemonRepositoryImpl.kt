package com.example.pokedetails.data.pokemon.repository

import com.example.pokedetails.data.pokemon.models.domain.PokemonShortDetail
import com.example.pokedetails.data.pokemon.models.mapper.toPokemonModel
import com.example.pokedetails.network.api.PokemonApi
import com.example.pokedetails.network.utils.SafeApiCall
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val safeApiCall: SafeApiCall,
    private val pokemonApi: PokemonApi
) : PokemonRepository {

    override suspend fun getPokemons(): Result<List<PokemonShortDetail>> {
        return safeApiCall.execute {
            pokemonApi.getPokemons()
        }.map { paginatedResponse ->
            paginatedResponse.results.map {
                it.toPokemonModel()
            }
        }
    }
}