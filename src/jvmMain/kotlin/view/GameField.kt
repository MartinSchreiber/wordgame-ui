package view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import model.gameField.GameField
import org.jetbrains.skia.Font

//TODO: draw base
@Composable
fun GameField(gameField: GameField) {
    val textPaint = Paint().asFrameworkPaint().apply {
        isAntiAlias = true
        color = Color.Black.toArgb()
    }

    Canvas(modifier = Modifier.fillMaxSize(0.5f)) {

        gameField.path.getLines().forEach {

            drawLine(
                start = it.first,
                end = it.second,
                color = Color.DarkGray,
                strokeWidth = 2f
            )
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