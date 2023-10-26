package com.example.pokedetails.ui.shared

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

fun LazyListScope.defaultError(
    modifier: Modifier = Modifier,
    errorMsg: String
) {
    item {
        DefaultError(modifier = modifier, errorMsg = errorMsg)
    }
}

@Composable
fun DefaultError(modifier: Modifier = Modifier, errorMsg: String) {
    Box(modifier = modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier.padding(24.dp),
            text = errorMsg
        )
    }

}