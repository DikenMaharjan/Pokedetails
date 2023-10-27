package com.example.pokedetails.utils.painter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.graphics.painter.Painter


class SizeRetainingPainter(
    private val delegatePainter: Painter,
) : Painter() {
    override val intrinsicSize: Size
        get() = delegatePainter.intrinsicSize

    override fun DrawScope.onDraw() {
        this.inset(
            (this.size.width - intrinsicSize.width) / 2,
            (this.size.height - intrinsicSize.height) / 2
        ) {
            with(delegatePainter) {
                draw(intrinsicSize)
            }
        }
    }
}


@Composable
fun rememberSizeRetainingPainter(
    delegatePainter: Painter
): Painter {
    return remember(delegatePainter) {
        SizeRetainingPainter(delegatePainter)
    }
}




