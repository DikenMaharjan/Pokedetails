package com.example.pokedetails.data.pokemon.models.dto.pokemondetaildto

data class Move(
    val move: NameAndUrl,
    val version_group_details: List<VersionGroupDetail>
)