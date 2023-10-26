package com.example.pokedetails.ui.pokemon_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedetails.data.pokemon.models.domain.PokemonShortDetail
import com.example.pokedetails.data.pokemon.repository.PokemonRepository
import com.example.pokedetails.utils.loadingdata.LoadingData
import com.example.pokedetails.utils.loadingdata.toLoadingData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListScreenViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

    var pokemons: LoadingData<List<PokemonShortDetail>> by mutableStateOf(LoadingData.Loading)
        private set

    init {
        getPokemons()
    }

    private fun getPokemons() {
        viewModelScope.launch {
            pokemons = LoadingData.Loading
            pokemons = pokemonRepository.getPokemons().toLoadingData()
        }

    }

}