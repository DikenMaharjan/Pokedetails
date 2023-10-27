package com.example.pokedetails.utils.loadingdata

import com.example.pokedetails.utils.extensions.errorMsg

// generic container for data that needs to be loaded up
sealed class LoadingData<out T> {

    class LoadedData<T>(
        val data: T
    ) : LoadingData<T>()

    object Loading : LoadingData<Nothing>()

    class Error(val throwable: Throwable) : LoadingData<Nothing>() {
        val errorMsg = throwable.errorMsg
    }

}


fun <T> T.toLoadedData() = LoadingData.LoadedData(this)

fun Throwable.toLoadingError() = LoadingData.Error(this)

fun <T> Result<T>.toLoadingData() = this.fold(
    onSuccess = { it.toLoadedData() },
    onFailure = { it.toLoadingError() }
)