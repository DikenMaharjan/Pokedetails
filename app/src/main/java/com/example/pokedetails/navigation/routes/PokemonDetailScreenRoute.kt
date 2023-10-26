package com.example.pokedetails.navigation.routes

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.pokedetails.navigation.ScreenRoute

object PokemonDetailScreenRoute : ScreenRoute() {

    private const val ID_KEY = "id"

    override val routePrefix: String
        get() = "pokemonDetail"

    override val route: String
        get() = "$routePrefix/{$ID_KEY}"

    override fun getArguments(): List<NamedNavArgument> {
        return listOf(
            navArgument(ID_KEY) {
                this.type = NavType.StringType
                this.nullable = false
            }
        )
    }

    context(NavController)
    fun doNavigation(id: String) {
        navigate("$routePrefix/$id")
    }
}