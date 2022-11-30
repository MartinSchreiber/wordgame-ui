package model.gameField

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.Offset
import util.Logger

//TODO: positive interaction/befriending of "enemy"?
//TODO: add "loot"
data class Enemy(
    private val path: Path,
    private val speed: Float = 0.001f,
    val health: MutableState<Float> = mutableStateOf(10f),
    val position: MutableState<Offset>,
    val delay: Long = 1000L
) {
    //    val maxHealth = health
    var distance = 1f

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

    override fun toString(): String {
        return ")${health.value}("
    }
}