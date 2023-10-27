package com.example.pokedetails.ui.pokemon_list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pokedetails.data.pokemon.models.domain.PokemonShortDetail
import com.example.pokedetails.ui.shared.defaultError
import com.example.pokedetails.ui.shared.defaultLoadingIndicator
import com.example.pokedetails.ui.theme.spacing
import com.example.pokedetails.utils.loadingdata.LoadingData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonListScreen(
    modifier: Modifier = Modifier,
    viewModel: PokemonListScreenViewModel = hiltViewModel(),
    navigateToPokemonDetail: (url: String) -> Unit
) {
    Column(
        modifier = modifier.background(MaterialTheme.colorScheme.background)
    ) {
        val appBarBehavior = TopAppBarDefaults.pinnedScrollBehavior()
        PokemonListTopBar(scrollBehavior = appBarBehavior)
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(appBarBehavior.nestedScrollConnection)
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
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonListTopBar(
    modifier: Modifier = Modifier, scrollBehavior: TopAppBarScrollBehavior
) {
    Surface(
        modifier = modifier,
        shadowElevation = MaterialTheme.spacing.dimen4
    ) {
        TopAppBar(
            title = { Text(text = "Pokemons") },
            scrollBehavior = scrollBehavior
        )
    }


}

fun LazyListScope.pokemons(
    pokemons: List<PokemonShortDetail>,
    onPokemonClick: (url: String) -> Unit
) {
    itemsIndexed(pokemons) { _, pokemon ->
        PokemonItem(pokemon = pokemon, onClick = onPokemonClick)
    }
}

@Composable
fun PokemonItem(
    modifier: Modifier = Modifier,
    pokemon: PokemonShortDetail,
    onClick: (url: String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.spacing.dimen4)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onClick(pokemon.detailUrl)
                }
                .padding(
                    all = MaterialTheme.spacing.dimen16
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = pokemon.pokemonName)
        }
        Divider(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.surfaceVariant
        )
    }

}