package model.gameField

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.geometry.Offset
import util.EnemyUtil

class GameField(
    start: Offset = Offset(20f, 20f),
    end: Offset = Offset(400f, 400f),
    numberOfTurns: Int = 2
) {
    val path = Path(startX = start.x, startY = start.y, endX = end.x, endY = end.y, numberOfTurns = numberOfTurns)
    val base = Base()
    val enemiesIncoming = EnemyUtil.getDefaultEnemies(path, start)
    val enemiesOnField: SnapshotStateList<Enemy> = mutableStateListOf()

    fun isOver() = enemiesIncoming.isEmpty() && enemiesOnField.isEmpty()
}