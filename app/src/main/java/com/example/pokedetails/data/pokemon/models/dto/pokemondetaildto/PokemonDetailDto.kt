package com.example.pokedetails.data.pokemon.models.dto.pokemondetaildto

data class PokemonDetailDto(
    val abilities: List<Ability>,
    val base_experience: Int,
    val forms: List<NameAndUrl>,
    val game_indices: List<GameIndice>,
    val height: Int,
    val held_items: List<HeldItem>,
    val id: Int,
    val is_default: Boolean,
    val location_area_encounters: String,
    val moves: List<Move>,
    val name: String,
    val order: Int,
    val species: NameAndUrl,
    val stats: List<Stat>,
    val types: List<Type>,
    val weight: Int,
    val sprites: Sprite
)