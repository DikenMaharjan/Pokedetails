package com.example.pokedetails.ui.pokemon_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.pokedetails.data.pokemon.repository.PokemonRepository
import com.example.pokedetails.utils.extensions.capitalizeFirst
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class PokemonListScreenViewModel @Inject constructor(
    pokemonRepository: PokemonRepository
) : ViewModel() {


    val pokemonsPagingData = pokemonRepository
        .getPokemonsPagingData()
        .map { pokemonPagingData ->
            pokemonPagingData.map { it.copy(pokemonName = it.pokemonName.capitalizeFirst()) }
        }.cachedIn(viewModelScope)


}