package util

import androidx.compose.ui.geometry.Offset
import model.gameField.Enemy
import model.gameField.Path

class EnemyUtil(path: Path, position: Offset) {

    private val smallEnemy = Enemy(
        path = path,
        startPosition = position,
        maxHealth = 10f
    )
    private val middleEnemy = Enemy(
        path = path,
        startPosition = position,
        maxHealth = 25f
    )
    private val bigEnemy = Enemy(
        path = path,
        startPosition = position,
        maxHealth = 50f
    )

    fun getDefaultEnemies(): MutableList<Enemy> {
        return ((smallEnemy * 10)
                + (middleEnemy * 5)
                + (bigEnemy * 1))
            .toMutableList()
    }
    //TODO: give Enemy-List for various levels/difficulties
}