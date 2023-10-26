package com.example.pokedetails.di

import com.example.pokedetails.network.API_END_POINT
import com.example.pokedetails.network.PokemonApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun providesRetrofit(
        converterFactory: Converter.Factory,
        httpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .client(httpClient)
            .addConverterFactory(converterFactory)
            .baseUrl(API_END_POINT)
            .build()
    }

    @Singleton
    @Provides
    fun providesPokemonApi(
        retrofit: Retrofit
    ): PokemonApi {
        return retrofit.create(PokemonApi::class.java)
    }

    @Provides
    fun providesConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun providesHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        ).build()
    }


}