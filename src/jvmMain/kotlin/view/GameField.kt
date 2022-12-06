package view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import model.gameField.GameField
import org.jetbrains.skia.Font

//TODO: fix overlay with other elements
@Composable
fun GameField(gameField: GameField) {
    val textPaint = Paint().asFrameworkPaint().apply {
        isAntiAlias = true
        color = Color.Black.toArgb()
    }

    Canvas(modifier = Modifier.size(500f.dp, 400f.dp)) {

        gameField.path.getLines().forEach {
            drawLine(
                start = it.first,
                end = it.second,
                color = Color.DarkGray,
                strokeWidth = 2f
            )
        }

        gameField.path.base.let { base ->
            drawCircle(radius = base.radius, center = base.center, color = Color.Gray)
            drawIntoCanvas {
                it.nativeCanvas.drawString(
                    base.health.value.toString(),
                    base.center.x,
                    base.center.y,
                    Font(),
                    textPaint
                )
            }
        }

        gameField.enemiesOnField.forEach { enemy ->
            drawIntoCanvas {
                it.nativeCanvas.drawString(
                    enemy.toString(),
                    enemy.position.value.x,
                    enemy.position.value.y,
                    Font(),
                    textPaint
                )
            }
        }
    }
}