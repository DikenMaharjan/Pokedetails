package com.example.pokedetails.utils.refreshstate

sealed interface RefreshState {
    object Refreshing : RefreshState
    class Error(msg: String?) : RefreshState {
        val errorMsg = msg ?: "Something went wrong"
    }
    object Idle : RefreshState

}
