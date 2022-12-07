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
        val enemies = mutableListOf<Enemy>()

        enemies.addAll(smallEnemy * 10)
        enemies.addAll(middleEnemy * 5)
        enemies.addAll(bigEnemy * 1)

        return enemies
    }
    //TODO: give Enemy-List for various levels/difficulties
}