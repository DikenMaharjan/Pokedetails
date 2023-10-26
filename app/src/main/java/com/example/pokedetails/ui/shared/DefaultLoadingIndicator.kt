package com.example.pokedetails.ui.shared

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.pokedetails.ui.theme.spacing

fun LazyListScope.defaultLoadingIndicator(
    modifier: Modifier = Modifier
) {
    item {
        DefaultLoadingIndicator(modifier = modifier.fillParentMaxWidth())
    }
}


@Composable
fun DefaultLoadingIndicator(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(
            modifier = Modifier.padding(
                top = MaterialTheme.spacing.dimen32
            )
        )
    }
}