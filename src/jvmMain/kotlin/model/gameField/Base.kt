package model.gameField

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.Offset

data class Base(
    val maxHealth: Float = 100f,
    val center: Offset,
    val radius: Float
) {
    val health = mutableStateOf(maxHealth)
}