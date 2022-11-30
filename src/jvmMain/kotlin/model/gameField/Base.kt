package model.gameField

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.Offset

data class Base(
    val health: MutableState<Float> = mutableStateOf(100f),
    val center: Offset,
    val radius: Float
)