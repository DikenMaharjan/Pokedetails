package com.example.pokedetails.data.pokemon.repository

import androidx.paging.PagingData
import com.example.pokedetails.data.pokemon.models.domain.Pokemon
import com.example.pokedetails.data.pokemon.models.domain.PokemonShortDetail
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    fun getPokemonsPagingData(): Flow<PagingData<PokemonShortDetail>>

    suspend fun getPokemonDetail(
        detailUrl: String
    ): Result<Pokemon>

}