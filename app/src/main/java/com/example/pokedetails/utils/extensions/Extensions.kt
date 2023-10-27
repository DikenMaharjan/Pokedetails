package com.example.pokedetails.utils.extensions

import android.content.Context
import android.widget.Toast
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