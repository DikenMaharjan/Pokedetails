package com.example.pokedetails.ui.pokemon_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.pokedetails.data.pokemon.models.domain.Pokemon
import com.example.pokedetails.data.pokemon.repository.PokemonRepository
import com.example.pokedetails.navigation.routes.PokemonDetailScreenRoute
import com.example.pokedetails.utils.fetcher.getFetcher
import com.example.pokedetails.utils.loadingdata.LoadingData
import com.example.pokedetails.utils.refreshstate.RefreshState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonDetailScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

    private val detailUrl = with(PokemonDetailScreenRoute) {
        savedStateHandle.getDetailUrl()
    }

    private val pokemonFetcher = getFetcher {
        pokemonRepository.getPokemonDetail(detailUrl)
    }

    val pokemon: LoadingData<Pokemon>
        get() = pokemonFetcher.data

    val refreshState: RefreshState
        get() = pokemonFetcher.refreshState


    fun refresh() {
        pokemonFetcher.refresh()
    }

    fun clearRefreshState() {
        pokemonFetcher.clearRefreshState()
    }


}