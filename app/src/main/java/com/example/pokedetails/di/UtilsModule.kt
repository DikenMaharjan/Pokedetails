package com.example.pokedetails.di

import com.example.pokedetails.utils.dispatchers.AppDispatchers
import com.example.pokedetails.utils.dispatchers.AppDispatchersImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UtilsModule {

    @Binds
    abstract fun providesAppDispatchers(
        appDispatchersImpl: AppDispatchersImpl
    ): AppDispatchers

}