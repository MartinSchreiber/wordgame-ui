package model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

class Enemy(
    var health: Double = 100.0,
    var speed: Double = 10.0,
    var position: MutableState<Double> = mutableStateOf(100.0)
) {
    val maxHealth = health
    fun move() {
        position.value -= speed
    }

    fun damage(damage: Double) {
        health -= damage
    }
}