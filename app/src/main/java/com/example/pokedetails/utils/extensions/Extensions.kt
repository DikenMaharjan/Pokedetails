package com.example.pokedetails.utils.extensions

import android.content.Context
import android.widget.Toast
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.pokedetails.data.pokemon.models.domain.PokemonShortDetail
import java.util.Locale

fun Context.showShortToast(
    msg: String
) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

fun <T, R> Result<List<T>>.maps(transform: (T) -> R): Result<List<R>> {
    return this.map {
        it.map(transform)
    }
}

fun String.capitalizeFirst() =
    this.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }

fun LazyPagingItems<PokemonShortDetail>.isLoading() =
    this.loadState.append is LoadState.Loading || this.loadState.prepend is LoadState.Loading || this.loadState.refresh is LoadState.Loading

val Throwable.errorMsg: String
    get() = this.message ?: "Something went wrong."