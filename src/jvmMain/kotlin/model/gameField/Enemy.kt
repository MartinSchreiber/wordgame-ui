package model.gameField

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.Offset
import util.Logger

//TODO: positive interaction/befriending of "enemy"?
//TODO: add "loot"
data class Enemy(
    private val path: Path,
    private val speed: Float = 0.002f,
    val position: MutableState<Offset>,
    val maxHealth: Float,
    val delay: Long = 1000L
) {
    var distance = 1f
    val health = mutableStateOf(maxHealth)

    fun move() {
        distance -= speed
        position.value = path.moveTo(distance)
        Logger.LOGGER.logEnemyMove(this)
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
            .map { this.copy(position = mutableStateOf(this.position.value)) }
    }

    override fun toString(): String {
        return ")${health.value}("
    }
}