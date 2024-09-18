package com.example.vkclient.ui.theme.icons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.LazyThreadSafetyMode

val WidgetsIcon: ImageVector by lazy(LazyThreadSafetyMode.NONE) {
    ImageVector.Builder(
        name = "Widgets",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 24f,
        viewportHeight = 24f
    ).apply {
        path(
            stroke = SolidColor(Color(0xFF000000)),
            strokeLineWidth = 2f,
            strokeLineCap = StrokeCap.Round
        ) {
            moveTo(4f, 3f)
            lineTo(9f, 3f)
            arcTo(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, 10f, 4f)
            lineTo(10f, 9f)
            arcTo(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, 9f, 10f)
            lineTo(4f, 10f)
            arcTo(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, 3f, 9f)
            lineTo(3f, 4f)
            arcTo(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, 4f, 3f)
            close()
        }
        path(
            stroke = SolidColor(Color(0xFF000000)),
            strokeLineWidth = 2f,
            strokeLineCap = StrokeCap.Round
        ) {
            moveTo(4f, 14f)
            lineTo(9f, 14f)
            arcTo(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, 10f, 15f)
            lineTo(10f, 20f)
            arcTo(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, 9f, 21f)
            lineTo(4f, 21f)
            arcTo(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, 3f, 20f)
            lineTo(3f, 15f)
            arcTo(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, 4f, 14f)
            close()
        }
        path(
            stroke = SolidColor(Color(0xFF000000)),
            strokeLineWidth = 2f,
            strokeLineCap = StrokeCap.Round
        ) {
            moveTo(15f, 3f)
            lineTo(20f, 3f)
            arcTo(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, 21f, 4f)
            lineTo(21f, 9f)
            arcTo(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, 20f, 10f)
            lineTo(15f, 10f)
            arcTo(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, 14f, 9f)
            lineTo(14f, 4f)
            arcTo(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, 15f, 3f)
            close()
        }
        path(
            stroke = SolidColor(Color(0xFF000000)),
            strokeLineWidth = 2f,
            strokeLineCap = StrokeCap.Round
        ) {
            moveTo(17.5f, 14f)
            lineTo(17.5f, 21f)
        }
        path(
            stroke = SolidColor(Color(0xFF000000)),
            strokeLineWidth = 2f,
            strokeLineCap = StrokeCap.Round
        ) {
            moveTo(21f, 17.5f)
            lineTo(14f, 17.5f)
        }
    }.build()
}

@Preview(showBackground = true)
@Composable
private fun WidgetsPreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = WidgetsIcon, contentDescription = null)
    }
}
