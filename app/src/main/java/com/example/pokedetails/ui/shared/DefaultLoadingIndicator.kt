package com.example.pokedetails.ui.shared

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

fun LazyListScope.defaultLoadingIndicator(
    modifier: Modifier = Modifier
) {
    item {
        DefaultLoadingIndicator(modifier = modifier)
    }
}


@Composable
fun DefaultLoadingIndicator(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxWidth()) {
        CircularProgressIndicator(
            modifier = Modifier.padding(top = 32.dp)
        )
    }
}