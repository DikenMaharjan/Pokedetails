package com.example.pokedetails.data.pagination.pokemon.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pokedetails.data.pokemon.models.domain.PokemonShortDetail
import com.example.pokedetails.data.pokemon.models.mapper.toPokemonModel
import com.example.pokedetails.network.api.PokemonApi
import com.example.pokedetails.network.utils.SafeApiCall

class PokemonPagingSource(
    private val pokemonApi: PokemonApi,
    private val safeApiCall: SafeApiCall,
) : PagingSource<String, PokemonShortDetail>() {
    override fun getRefreshKey(state: PagingState<String, PokemonShortDetail>): String? {
        return null
    }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, PokemonShortDetail> {
        val key = params.key
        val response = safeApiCall.execute {
            if (key == null) {
                pokemonApi.getPokemons()
            } else {
                pokemonApi.getPokemons(key)
            }
        }
        return response.fold(
            onSuccess = { paginatedResponse ->
                LoadResult.Page(
                    data = paginatedResponse.results.map { it.toPokemonModel() },
                    prevKey = paginatedResponse.previous,
                    nextKey = paginatedResponse.next
                )
            },
            onFailure = {
                LoadResult.Error(it)
            }
        )
    }
}