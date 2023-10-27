package com.example.pokedetails.ui.pokemon_detail.shape

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection

class TopConcaveCurve(
    val topCurvature: Dp
) : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val topInset = with(density) {
            topCurvature.toPx()
        }
        val path = Path().apply {
            moveTo(0f, 0f)
            quadraticBezierTo(size.width / 2, topInset, size.width, 0f)
            lineTo(size.width, size.height)
            lineTo(0f, size.height)
            close()
        }
        return Outline.Generic(path)
    }

}

@Composable
fun rememberTopConcaveCurve(
    topCurvature: Dp
): TopConcaveCurve {
    return remember(topCurvature) {
        TopConcaveCurve(topCurvature = topCurvature)
    }
}