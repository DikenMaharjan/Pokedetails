package com.example.pokedetails.ui.pokemon_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pokedetails.data.pokemon.models.domain.PokemonShortDetail
import com.example.pokedetails.ui.shared.defaultError
import com.example.pokedetails.ui.shared.defaultLoadingIndicator
import com.example.pokedetails.utils.loadingdata.LoadingData

@Composable
fun PokemonListScreen(
    modifier: Modifier = Modifier,
    viewModel: PokemonListScreenViewModel = hiltViewModel(),
    navigateToPokemonDetail: (url: String) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
    ) {
        when (val pokemons = viewModel.pokemons) {
            is LoadingData.Error -> defaultError(
                errorMsg = pokemons.errorMsg
            )

            is LoadingData.LoadedData -> pokemons(
                pokemons = pokemons.data,
                onPokemonClick = navigateToPokemonDetail
            )

            LoadingData.Loading -> defaultLoadingIndicator()
        }
    }
}

fun LazyListScope.pokemons(
    pokemons: List<PokemonShortDetail>,
    onPokemonClick: (url: String) -> Unit
) {
    items(pokemons) { pokemon ->
        PokemonItem(pokemon = pokemon, onClick = onPokemonClick)
    }
}

@Composable
fun PokemonItem(
    modifier: Modifier = Modifier,
    pokemon: PokemonShortDetail,
    onClick: (url: String) -> Unit
) {
    Row(
        modifier = modifier.clickable {
            onClick(pokemon.detailUrl)
        },
    ) {
        Text(text = pokemon.pokemonName)
    }

}