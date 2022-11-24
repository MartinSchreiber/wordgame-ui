package model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

//TODO: positive interaction/befriending of "enemy"?
class Enemy(
    var health: MutableState<Double> = mutableStateOf(100.0),
    var speed: Int = 1,
    var position: MutableState<Pair<Int, Int>> = mutableStateOf(Pair(20, 20)),
    val delay: Long = 1000L
) {
    val maxHealth = health
    var reachedEnd = false
    fun moveTo(position: Pair<Int, Int>) {
        this.position.value = position
    }

    fun damage(damage: Double) {
        health.value -= damage
    }

    override fun toString(): String {
        return ")${health.value}("
    }
}