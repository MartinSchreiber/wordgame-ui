package view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import model.Enemy
import model.Path
import org.jetbrains.skia.Font

//TODO: draw base
@Composable
fun GameField(enemies: SnapshotStateList<Enemy>, path: Path) {
    val textPaint = Paint().asFrameworkPaint().apply {
        isAntiAlias = true
        color = Color.Black.toArgb()
    }

    Canvas(modifier = Modifier.fillMaxSize(0.5f)) {

        path.getLines().forEach {

            drawLine(
                start = Offset(x = it.first.x.toFloat(), y = it.first.y.toFloat()),
                end = Offset(x = it.second.x.toFloat(), y = it.second.y.toFloat()),
                color = Color.DarkGray,
                strokeWidth = 2F
            )
        }

        enemies.forEach { enemy ->
            drawIntoCanvas {
                it.nativeCanvas.drawString(
                    enemy.toString(),
                    enemy.position.value.x.dp.toPx(),
                    enemy.position.value.y.dp.toPx(),
                    Font(),
                    textPaint
                )
            }
        }
    }
}