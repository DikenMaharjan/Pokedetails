package com.example.pokedetails.navigation.routes

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.pokedetails.navigation.ScreenRoute
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

object PokemonDetailScreenRoute : ScreenRoute() {

    private const val URL_KEY = "url_key"


    override val routePrefix: String
        get() = "pokemonDetail"

    override val route: String
        get() = "$routePrefix/{$URL_KEY}"

    override fun getArguments(): List<NamedNavArgument> {
        return listOf(
            navArgument(URL_KEY) {
                this.type = NavType.StringType
                this.nullable = false
            }
        )
    }

    context(NavController)
    fun doNavigation(url: String) {
        val encodedUrl = URLEncoder.encode(url, StandardCharsets.UTF_8.toString())
        navigate("$routePrefix/$encodedUrl")
    }

    fun SavedStateHandle.getDetailUrl() = this.get<String>(URL_KEY)!!.run {
        URLDecoder.decode(this, StandardCharsets.UTF_8.toString())
    }
}