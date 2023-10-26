package com.example.pokedetails.utils.fetcher

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedetails.utils.loadingdata.LoadingData
import com.example.pokedetails.utils.loadingdata.toLoadedData
import com.example.pokedetails.utils.loadingdata.toLoadingData
import com.example.pokedetails.utils.refreshstate.RefreshState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class Fetcher<T>(
    private val scope: CoroutineScope,
    private val getData: suspend () -> Result<T>
) {
    var data: LoadingData<T> by mutableStateOf(LoadingData.Loading)
        private set

    var refreshState: RefreshState by mutableStateOf(RefreshState.Idle)
        private set

    init {
        getData(false)
    }

    private fun getData(isRefreshing: Boolean) {
        scope.launch {
            val shouldChangeRefreshState = isRefreshing && data is LoadingData.LoadedData
            if (shouldChangeRefreshState) {
                getDataAndChangeRefreshState()
            } else {
                data = LoadingData.Loading
                data = getData().toLoadingData()
            }
        }
    }

    private suspend fun getDataAndChangeRefreshState() {
        refreshState = RefreshState.Refreshing
        val response = getData()
        refreshState = response.fold(
            onSuccess = {
                data = it.toLoadedData()
                RefreshState.Idle
            },
            onFailure = {
                RefreshState.Error(it.message)
            }
        )
    }

    fun refresh() {
        getData(true)
    }

    fun clearRefreshState() {
        refreshState = RefreshState.Idle
    }

}

fun <T> ViewModel.getFetcher(getData: suspend () -> Result<T>) = Fetcher(
    scope = viewModelScope,
    getData = getData
)