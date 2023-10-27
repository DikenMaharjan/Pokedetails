package com.example.pokedetails.ui.pokemon_list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
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
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.pokedetails.data.pokemon.models.domain.PokemonShortDetail
import com.example.pokedetails.ui.shared.defaultError
import com.example.pokedetails.ui.shared.defaultLoadingIndicator
import com.example.pokedetails.ui.theme.spacing
import com.example.pokedetails.utils.extensions.errorMsg
import com.example.pokedetails.utils.extensions.isLoading

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
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
        val pokemons = viewModel.pokemonsPagingData.collectAsLazyPagingItems()
        val isRefreshing =
            pokemons.loadState.refresh is LoadState.Loading && pokemons.itemCount != 0
        val pullRefreshState = rememberPullRefreshState(
            refreshing = isRefreshing, onRefresh = pokemons::refresh
        )
        Box(modifier = Modifier.pullRefresh(pullRefreshState)) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .nestedScroll(appBarBehavior.nestedScrollConnection)
            ) {
                val refreshState = pokemons.loadState.refresh
                if (refreshState is LoadState.Error) {
                    defaultError(errorMsg = refreshState.error.errorMsg)
                }

                if (pokemons.itemCount == 0 && pokemons.isLoading()) {
                    defaultLoadingIndicator()
                } else {
                    items(pokemons.itemCount) { index ->
                        val pokemon = pokemons[index]
                        if (pokemon != null) {
                            PokemonItem(pokemon = pokemon, onClick = navigateToPokemonDetail)
                        }
                    }
                }
                footer(pokemons)
            }
            PullRefreshIndicator(
                refreshing = isRefreshing,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
    }
}

fun LazyListScope.footer(pokemon: LazyPagingItems<PokemonShortDetail>) {
    when (val appendState = pokemon.loadState.append) {
        is LoadState.Error -> {
            defaultError(errorMsg = appendState.error.errorMsg)
        }

        LoadState.Loading -> {
            defaultLoadingIndicator()
        }

        is LoadState.NotLoading -> {}
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