package com.example.pokedetails.ui.pokemon_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pokedetails.data.pokemon.models.domain.Pokemon
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
            .verticalScroll(rememberScrollState())
    ) {
        when (val pokemon = viewModel.pokemon) {
            is LoadingData.Error -> DefaultError(errorMsg = pokemon.errorMsg)
            is LoadingData.LoadedData -> PokemonDetail(pokemon.data)
            LoadingData.Loading -> DefaultLoadingIndicator()
        }

        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = state,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}

@Composable
fun PokemonDetail(data: Pokemon) {
    Text(text = data.name)
}
