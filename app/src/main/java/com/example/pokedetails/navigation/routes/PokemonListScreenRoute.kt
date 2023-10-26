package com.example.pokedetails.navigation.routes

import com.example.pokedetails.navigation.ScreenRoute

object PokemonListScreenRoute : ScreenRoute() {

    override val routePrefix: String
        get() = "pokemonList"

    override val route: String
        get() = routePrefix

}
