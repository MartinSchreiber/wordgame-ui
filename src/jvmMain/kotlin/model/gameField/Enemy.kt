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
    var health: MutableState<Double> = mutableStateOf(10.0),
    var position: MutableState<Offset> = mutableStateOf(Offset(20f, 20f)),
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

    fun damage(damage: Double) {
        health.value -= damage
    }

    override fun toString(): String {
        return ")${health.value}("
    }
}