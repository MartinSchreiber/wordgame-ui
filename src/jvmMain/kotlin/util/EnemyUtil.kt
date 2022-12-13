package util

import androidx.compose.ui.geometry.Offset
import constants.Level
import model.gameField.Enemy
import model.gameField.Path

class EnemyUtil(val path: Path, val position: Offset) {
    companion object {
        const val BASE_SPEED = 0.001f
        const val DEFAULT_DELAY = 2000L
    }

    inner class EnemyGroup(
        health: Float,
        number: Int = 1,
        speed: Float = 1f,
        delay: Long = (DEFAULT_DELAY / speed).toLong()
    ) {
        val enemies = Enemy(
            path = path,
            speed = BASE_SPEED * speed,
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

    fun getEnemies(level: Level): MutableList<Enemy> {
        val enemyGroups = when (level) {
            Level.ONE -> {
                listOf(EnemyGroup(10f, 20))
            }

            Level.TWO -> {
                EnemyGroup(10f, 10) +
                        EnemyGroup(20f, 10)
            }

            Level.THREE -> {
                EnemyGroup(10f, 10) +
                        EnemyGroup(20f, 5) +
                        EnemyGroup(10f, 10)
            }

            Level.FOUR -> {
                EnemyGroup(10f, 10) +
                        EnemyGroup(20f, 5) +
                        EnemyGroup(50f, 1)
            }

            Level.FIVE -> {
                EnemyGroup(10f, 5, 2f) +
                        EnemyGroup(20f, 5) +
                        EnemyGroup(10f, 5, 2f) +
                        EnemyGroup(50f, 1)
            }

            Level.SIX -> {
                EnemyGroup(20f, 5) +
                        EnemyGroup(50f, 1) +
                        EnemyGroup(20f, 5) +
                        EnemyGroup(50f, 1) +
                        EnemyGroup(10f, 5, 2f)
            }

            Level.SEVEN -> {
                EnemyGroup(20f, 5, 2f) +
                        EnemyGroup(50f, 1) +
                        EnemyGroup(20f, 5, 2f) +
                        EnemyGroup(50f, 1) +
                        EnemyGroup(10f, 5, 2f)
            }

            Level.EIGHT -> {
                EnemyGroup(10f, 10, 2f) +
                        EnemyGroup(50f, 1, 2f) +
                        EnemyGroup(10f, 10, 2f) +
                        EnemyGroup(50f, 1, 2f)
            }

            Level.NINE -> {
                EnemyGroup(10f, 10, 2f) +
                        EnemyGroup(50f, 1, 2f) +
                        EnemyGroup(20f, 5, 2.5f) +
                        EnemyGroup(50f, 1, 2f)
            }

            Level.TEN -> {
                EnemyGroup(10f, 10, 2.5f) +
                        EnemyGroup(50f, 2, 2f) +
                        EnemyGroup(20f, 5, 2.5f) +
                        EnemyGroup(50f, 2, 2f)
            }
        }
        return enemies(enemyGroups)
    }
}