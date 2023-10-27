package com.example.pokedetails.data.pokemon.models.domain

data class Pokemon(
    val name: String,
    val image: PokemonImage,
    val pokemonType: List<String>,
    val weightInGrams: Int,
    val heightInMetre: Int,
    val moves: List<String>
)

data class PokemonImage(
    val front: String?,
    val frontFemale: String?,

    val back: String?,
    val backFemale: String?,

    val frontShiny: String?,
    val frontShinyFemale: String?,

    val backShiny: String?,
    val backShinyFemale: String?
) {
    fun getImageList() = listOf(
        this.front,
        this.back,
        this.frontShiny,
        this.backShiny,
        this.frontFemale,
        this.backFemale,
        this.frontShinyFemale,
        this.backShinyFemale
    ).mapNotNull { it }
}