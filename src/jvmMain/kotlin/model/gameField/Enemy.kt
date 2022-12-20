package model.gameField

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.Offset

data class Enemy(
    private val path: Path,
    private val speed: Float,
    val startPosition: Offset,
    val maxHealth: Float,
    val delay: Long
) {
    var distance = 1f
    val health = mutableStateOf(maxHealth)
    val position = mutableStateOf(startPosition)

    fun move() {
        distance -= speed
        position.value = path.moveTo(distance)
    }

    fun reachedEnd(): Boolean {
        return distance <= 0
    }

    fun damage(damage: Float) {
        health.value -= damage
    }

    operator fun times(factor: Int): List<Enemy> {
        return (1..factor)
            .toList()
            .map { this.copy() }
    }

    override fun toString(): String {
        return health.value.toString()
    }
}