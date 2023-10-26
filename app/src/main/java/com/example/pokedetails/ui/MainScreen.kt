package com.example.pokedetails.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.pokedetails.navigation.routes.PokemonDetailScreenRoute
import com.example.pokedetails.navigation.routes.PokemonListScreenRoute
import com.example.pokedetails.navigation.screen
import com.example.pokedetails.ui.pokemon_detail.PokemonDetailScreen
import com.example.pokedetails.ui.pokemon_list.PokemonListScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = PokemonListScreenRoute.route
    ) {
        screen(PokemonListScreenRoute) {
            PokemonListScreen(
                navigateToPokemonDetail = { id ->
                    with(navController) { PokemonDetailScreenRoute.doNavigation(id) }
                }
            )
        }

        screen(PokemonDetailScreenRoute) {
            PokemonDetailScreen()
        }

    }

}