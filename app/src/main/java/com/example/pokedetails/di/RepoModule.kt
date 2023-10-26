package com.example.pokedetails.di

import com.example.pokedetails.data.pokemon.repository.PokemonRepository
import com.example.pokedetails.data.pokemon.repository.PokemonRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {

    @Binds
    abstract fun providesPokemonRepository(
        pokemonRepositoryImpl: PokemonRepositoryImpl
    ): PokemonRepository

}