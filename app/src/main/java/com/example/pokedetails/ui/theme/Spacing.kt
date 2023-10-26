package com.example.pokedetails.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class Spacing(
    val dimen0: Dp = 0.dp,
    val dimen2: Dp = 2.dp,
    val dimen4: Dp = 4.dp,
    val dimen8: Dp = 8.dp,
    val dimen12: Dp = 12.dp,
    val dimen16: Dp = 16.dp,
    val dimen18: Dp = 18.dp,
    val dimen24: Dp = 24.dp,
    val dimen28: Dp = 28.dp,
    val dimen32: Dp = 32.dp,
    val dimen40: Dp = 40.dp,
    val dimen48: Dp = 48.dp,
    val dimen80: Dp = 80.dp
)

val LocalSpacing = staticCompositionLocalOf { Spacing() }

val MaterialTheme.spacing: Spacing
    @Composable @ReadOnlyComposable get() = LocalSpacing.current