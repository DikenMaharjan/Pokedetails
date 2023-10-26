package com.example.pokedetails.ui.pokemon_list

import androidx.lifecycle.ViewModel
import com.example.pokedetails.data.pokemon.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonListScreenViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

}