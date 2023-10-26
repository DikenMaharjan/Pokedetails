package com.example.pokedetails.data.pokemon.repository

import com.example.pokedetails.data.pokemon.models.domain.Pokemon
import com.example.pokedetails.data.pokemon.models.domain.PokemonShortDetail

interface PokemonRepository {

    suspend fun getPokemons(): Result<List<PokemonShortDetail>>

    suspend fun getPokemonDetail(
        detailUrl: String
    ): Result<Pokemon>

}