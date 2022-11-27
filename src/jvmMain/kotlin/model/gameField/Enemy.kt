package model.gameField

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import util.Logger

//TODO: positive interaction/befriending of "enemy"?
//TODO: add "loot"
data class Enemy(
    private val path: Path,
    private val speed: Double = 0.001,
    var health: MutableState<Double> = mutableStateOf(10.0),
    var position: MutableState<Point> = mutableStateOf(Point(20, 20)),
    val delay: Long = 1000L
) {
    //    val maxHealth = health
    var distance = 1.0

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