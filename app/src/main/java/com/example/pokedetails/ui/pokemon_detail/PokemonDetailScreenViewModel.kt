package com.example.pokedetails.ui.pokemon_detail

import androidx.lifecycle.ViewModel
import com.example.pokedetails.data.pokemon.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonDetailScreenViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {
}