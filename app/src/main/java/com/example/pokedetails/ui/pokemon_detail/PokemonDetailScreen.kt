package com.example.pokedetails.ui.pokemon_detail

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pokedetails.data.pokemon.models.domain.Pokemon
import com.example.pokedetails.ui.pokemon_detail.composables.PokemonImage
import com.example.pokedetails.ui.pokemon_detail.composables.PokemonInfo
import com.example.pokedetails.ui.shared.DefaultError
import com.example.pokedetails.ui.shared.DefaultLoadingIndicator
import com.example.pokedetails.utils.extensions.showShortToast
import com.example.pokedetails.utils.loadingdata.LoadingData
import com.example.pokedetails.utils.refreshstate.RefreshState
import kotlinx.coroutines.flow.collectLatest

private const val TAG = "PokemonDetailScreen"

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun PokemonDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: PokemonDetailScreenViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val isRefreshing = viewModel.refreshState is RefreshState.Refreshing
    val state = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = viewModel::refresh
    )
    LaunchedEffect(key1 = Unit) {
        snapshotFlow { viewModel.refreshState }.collectLatest {
            if (it is RefreshState.Error) {
                context.showShortToast(it.errorMsg)
                viewModel.clearRefreshState()
            }
        }
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.outlineVariant)
    ) {
        val appBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
        TopBar(pokemon = viewModel.pokemon, appBarScrollBehavior)
        Box(
            modifier = modifier
                .weight(1f)
                .pullRefresh(state)
                .nestedScroll(appBarScrollBehavior.nestedScrollConnection)
        ) {
            when (val pokemon = viewModel.pokemon) {
                is LoadingData.Error -> DefaultError(errorMsg = pokemon.errorMsg)
                is LoadingData.LoadedData -> PokemonDetail(
                    pokemon = pokemon.data
                )

                LoadingData.Loading -> DefaultLoadingIndicator()
            }

            PullRefreshIndicator(
                refreshing = isRefreshing,
                state = state,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    pokemon: LoadingData<Pokemon>,
    topAppBarScrollBehavior: TopAppBarScrollBehavior
) {
    val backPressedDispatcherOwner = LocalOnBackPressedDispatcherOwner.current
    TopAppBar(
        title = {
            Text(
                text = run {
                    if (pokemon is LoadingData.LoadedData) {
                        pokemon.data.name
                    } else {
                        ""
                    }
                }, style = MaterialTheme.typography.titleLarge
            )
        },
        navigationIcon = {
            IconButton(
                onClick = { backPressedDispatcherOwner?.onBackPressedDispatcher?.onBackPressed() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back Icon"
                )
            }
        },
        scrollBehavior = topAppBarScrollBehavior
    )

}

@Composable
fun PokemonDetail(
    modifier: Modifier = Modifier, pokemon: Pokemon
) {
    ScrollableSplitLayout(
        modifier = modifier
            .fillMaxSize(),
        header = {
            PokemonImage(pokemon = pokemon)
        },
        footer = {
            PokemonInfo(
                modifier = Modifier.fillMaxSize(),
                pokemon = pokemon
            )
        }
    )
}


// This layout will fill take max height and max width imposed by parent.
// This layout will be scrollable
// If the (sum of height of header and footer) < (max_height), footer will take the remaining area.
@Composable
fun ScrollableSplitLayout(
    modifier: Modifier = Modifier,
    header: @Composable BoxScope.() -> Unit,
    footer: @Composable BoxScope.() -> Unit
) {
    SubcomposeLayout(modifier = modifier) { initialConstraints ->
        val actualPlaceable = subcompose(1) {
            Layout(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                content = {
                    Box(content = header)
                    Box(
                        content = footer,
                        propagateMinConstraints = true
                    )
                }
            ) { measureables, constraints ->
                val looseConstraints = constraints.copy(
                    minHeight = 0
                )
                val headerPlaceable = measureables[0].measure(looseConstraints)
                val footerPlaceable = measureables[1].measure(
                    looseConstraints.copy(
                        minHeight = initialConstraints.maxHeight - headerPlaceable.height
                    )
                )
                val totalHeight = headerPlaceable.height + footerPlaceable.height
                layout(constraints.maxWidth, totalHeight) {
                    headerPlaceable.place(0, 0)
                    footerPlaceable.place(0, headerPlaceable.height)
                }
            }
        }[0].measure(initialConstraints)
        layout(actualPlaceable.width, actualPlaceable.height) {
            actualPlaceable.place(0, 0)
        }
    }
}