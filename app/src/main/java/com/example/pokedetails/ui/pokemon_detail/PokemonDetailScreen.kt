package com.example.pokedetails.ui.pokemon_detail

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

@OptIn(ExperimentalMaterialApi::class)
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
    Box(
        modifier = modifier
            .fillMaxSize()
            .pullRefresh(state)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonDetail(
    modifier: Modifier = Modifier, pokemon: Pokemon
) {
    val backPressedDispatcherOwner = LocalOnBackPressedDispatcherOwner.current
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant)
    ) {
        TopAppBar(
            title = {
                Text(text = pokemon.name, style = MaterialTheme.typography.titleLarge)
            },
            navigationIcon = {
                IconButton(
                    modifier = Modifier.align(Alignment.Start),
                    onClick = { backPressedDispatcherOwner?.onBackPressedDispatcher?.onBackPressed() }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back Icon")
                }
            }
        )

        PokemonImage(pokemon = pokemon)
        PokemonInfo(
            pokemon = pokemon
        )
    }
}
