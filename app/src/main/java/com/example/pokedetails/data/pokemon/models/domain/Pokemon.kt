package com.example.pokedetails.data.pokemon.models.domain

data class Pokemon(
    val name: String,
    val imageUrl: List<String>,
    val pokemonType: List<String>,
    val weight: Int,
    val height: Int
)