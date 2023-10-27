package com.example.pokedetails.data.pokemon.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.pokedetails.data.pagination.pokemon.pagingsource.PokemonPagingSource
import com.example.pokedetails.data.pokemon.models.domain.Pokemon
import com.example.pokedetails.data.pokemon.models.domain.PokemonShortDetail
import com.example.pokedetails.data.pokemon.models.mapper.toPokemon
import com.example.pokedetails.network.api.PokemonApi
import com.example.pokedetails.network.utils.SafeApiCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val safeApiCall: SafeApiCall,
    private val pokemonApi: PokemonApi
) : PokemonRepository {
    override fun getPokemonsPagingData(): Flow<PagingData<PokemonShortDetail>> {
        return Pager(
            config = PagingConfig(20),
            initialKey = null,
            pagingSourceFactory = {
                PokemonPagingSource(
                    pokemonApi = pokemonApi,
                    safeApiCall = safeApiCall
                )
            }
        ).flow
    }

    override suspend fun getPokemonDetail(detailUrl: String): Result<Pokemon> {
        return safeApiCall.execute {
            pokemonApi.getPokemonDetail(detailUrl)
        }.map {
            it.toPokemon()
        }
    }
}