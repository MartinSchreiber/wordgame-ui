package model.gameField

import androidx.compose.ui.geometry.Offset

class EnemyGroup(
    private val maxHealth: Float,
    private val number: Int = 1,
    private val speed: Float = 1f,
    private val delay: Long = (DEFAULT_DELAY / speed).toLong()
) {
    companion object {
        const val BASE_SPEED = 0.0005f
        const val DEFAULT_DELAY = 2000L

        fun enemyList(groups: List<EnemyGroup>, path: Path, startPosition: Offset): List<Enemy> =
            groups.map { it.enemyList(path = path, startPosition = startPosition) }.flatten()
    }

    fun enemyList(path: Path, startPosition: Offset): List<Enemy> =
        Enemy(
            speed = BASE_SPEED * speed,
            maxHealth = maxHealth,
            delay = delay,
            path = path,
            startPosition = startPosition
        ) * number

    operator fun plus(enemyGroup: EnemyGroup): List<EnemyGroup> {
        return listOf(this, enemyGroup)
    }
}