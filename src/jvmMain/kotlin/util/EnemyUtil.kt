package util

import androidx.compose.ui.geometry.Offset
import model.gameField.Enemy
import model.gameField.Path

class EnemyUtil(val path: Path, val position: Offset) {
    companion object {
        const val BASE_SPEED = 0.002f
        const val DEFAULT_DELAY = 1000L
    }

    inner class EnemyGroup(health: Float, number: Int = 1, delay: Long = DEFAULT_DELAY, speed: Float = BASE_SPEED) {
        val enemies = Enemy(
            path = path,
            speed = speed,
            maxHealth = health,
            startPosition = position,
            delay = delay
        ) * number

        operator fun plus(enemyGroup: EnemyGroup): List<EnemyGroup> {
            return listOf(this, enemyGroup)
        }
    }

    private val enemies = { groups: List<EnemyGroup> ->
        groups
            .map { it.enemies }
            .flatten()
            .toMutableList()
    }

    fun getDefaultEnemies(): MutableList<Enemy> {
        return enemies(
            EnemyGroup(10f, 10) +
                    EnemyGroup(25f, 5) +
                    EnemyGroup(50f)
        )
    }
}