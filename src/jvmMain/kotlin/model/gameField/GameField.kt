package model.gameField

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import util.EnemyUtil

class GameField {
    val path = Path()
    val enemiesIncoming = EnemyUtil.getDefaultEnemies(path)
    val enemiesOnField: SnapshotStateList<Enemy> = mutableStateListOf()

    fun isOver() = enemiesIncoming.isEmpty() && enemiesOnField.isEmpty()
}