package com.example.pokedetails.ui.shared

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.pokedetails.ui.theme.spacing

fun LazyListScope.defaultError(
    modifier: Modifier = Modifier,
    errorMsg: String
) {
    item {
        DefaultError(modifier = modifier.fillParentMaxWidth(), errorMsg = errorMsg)
    }
}

@Composable
fun DefaultError(modifier: Modifier = Modifier, errorMsg: String) {
    Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Text(
            modifier = Modifier.padding(
                MaterialTheme.spacing.dimen32
            ),
            text = errorMsg,
            color = MaterialTheme.colorScheme.error
        )
    }

}