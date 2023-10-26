package com.example.pokedetails.navigation

import androidx.navigation.NamedNavArgument

abstract class ScreenRoute {

    protected abstract val routePrefix: String

    abstract val route: String

    open fun getArguments(): List<NamedNavArgument> = listOf()
}



