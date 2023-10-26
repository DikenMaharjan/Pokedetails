package com.example.pokedetails.utils.loadingdata

// generic container for data that needs to be loaded up
sealed class LoadingData<out T> {

    class LoadedData<T>(
        val data: T
    ) : LoadingData<T>()

    object Loading : LoadingData<Nothing>()

    class Error(val throwable: Throwable) : LoadingData<Nothing>() {
        val errorMsg = throwable.message ?: "Something went wrong"
    }

}


fun <T> T.toLoadedData() = LoadingData.LoadedData(this)

fun Throwable.toLoadingError() = LoadingData.Error(this)

fun <T> Result<T>.toLoadingData() = this.fold(
    onSuccess = { it.toLoadedData() },
    onFailure = { it.toLoadingError() }
)