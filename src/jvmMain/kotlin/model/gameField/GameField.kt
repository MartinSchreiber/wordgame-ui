package model.gameField

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.geometry.Offset
import util.EnemyUtil

class GameField(
    start: Offset = Offset(20f, 20f),
    end: Offset = Offset(400f, 350f),
    numberOfTurns: Int = 4
) {
    val path = Path(startX = start.x, startY = start.y, endX = end.x, endY = end.y, numberOfTurns = numberOfTurns)
    val enemiesIncoming = EnemyUtil(path, start).getDefaultEnemies()
    val enemiesOnField: SnapshotStateList<Enemy> = mutableStateListOf()

    fun isOver() = (enemiesIncoming.isEmpty() && enemiesOnField.isEmpty())
            || path.base.health.value <= 0
}