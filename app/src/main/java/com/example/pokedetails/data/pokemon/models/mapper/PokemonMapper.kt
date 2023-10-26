package com.example.pokedetails.data.pokemon.models.mapper

import com.example.pokedetails.data.pagination.models.paginatedresponse.Result
import com.example.pokedetails.data.pokemon.models.domain.Pokemon
import com.example.pokedetails.data.pokemon.models.domain.PokemonShortDetail
import com.example.pokedetails.data.pokemon.models.dto.pokemondetaildto.PokemonDetailDto

fun Result.toPokemonModel() = PokemonShortDetail(
    pokemonName = this.name,
    detailUrl = this.url
)

fun PokemonDetailDto.toPokemon() = Pokemon(
    name = this.name,
    imageUrl =  listOf(),
    pokemonType = listOf(this.name),
    weight = this.weight,
    height = this.height
)