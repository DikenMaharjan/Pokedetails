package com.example.pokedetails.data.pokemon.models.mapper

import com.example.pokedetails.data.pagination.models.paginatedresponse.Result
import com.example.pokedetails.data.pokemon.models.domain.PokemonShortDetail

fun Result.toPokemonModel() = PokemonShortDetail(
    pokemonName = this.name,
)