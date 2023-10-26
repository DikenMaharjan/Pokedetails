package com.example.pokedetails.ui.pokemon_list

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun PokemonListScreen(
    viewModel: PokemonListScreenViewModel = hiltViewModel(),
    navigateToPokemonDetail: (id: String) -> Unit
) {
    Column {
        Text(text = "Pokemon Lists")
        Button(onClick = {
            navigateToPokemonDetail("dsf")
        }) {
            Text(text = "How")
        }
    }

}