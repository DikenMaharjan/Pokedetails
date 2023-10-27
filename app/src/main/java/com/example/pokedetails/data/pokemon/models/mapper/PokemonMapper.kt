package com.example.pokedetails.data.pokemon.models.mapper

import com.example.pokedetails.data.pagination.pokemon.paginatedresponse.Result
import com.example.pokedetails.data.pokemon.models.domain.Pokemon
import com.example.pokedetails.data.pokemon.models.domain.PokemonImage
import com.example.pokedetails.data.pokemon.models.domain.PokemonShortDetail
import com.example.pokedetails.data.pokemon.models.dto.pokemondetaildto.PokemonDetailDto
import com.example.pokedetails.utils.extensions.capitalizeFirst

fun Result.toPokemonModel() = PokemonShortDetail(
    pokemonName = this.name,
    detailUrl = this.url
)

fun PokemonDetailDto.toPokemon() = Pokemon(
    name = this.name.capitalizeFirst(),
    image = PokemonImage(
        front = this.sprites.front_default,
        frontFemale = this.sprites.front_female,
        back = this.sprites.back_default,
        backFemale = this.sprites.back_female,
        frontShiny = this.sprites.front_shiny,
        frontShinyFemale = this.sprites.front_shiny_female,
        backShiny = this.sprites.back_shiny,
        backShinyFemale = this.sprites.back_shiny_female
    ),
    pokemonType = this.types.sortedBy { it.slot }.map { it.type.name.capitalizeFirst() },
    weightInGrams = this.weight * 100,
    heightInMetre = this.height / 10f,
    moves = this.moves.map { it.move.name.capitalizeFirst() }
)