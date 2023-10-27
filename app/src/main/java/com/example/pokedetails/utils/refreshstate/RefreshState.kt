package com.example.pokedetails.utils.refreshstate

sealed interface RefreshState {
    object Refreshing : RefreshState
    class Error(val errorMsg: String) : RefreshState
    object Idle : RefreshState

}
