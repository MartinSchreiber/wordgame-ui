package model.gameField

import androidx.compose.ui.geometry.Offset
import util.Logger

//TODO: Generalise and document
class Path(
    private val startX: Float = 20f,
    private val startY: Float = 20f,
    private val maxX: Float = 320f,
    private val stepY: Float = 60f,
    private val numberOfTurns: Int = 2
) {
    private val stepsY = (0..numberOfTurns)
        .toList()
        .map { startY + (stepY * it) }
    private val length = (maxX - startX) * (1 + numberOfTurns) + stepY * numberOfTurns

    private val effectiveDistanceX = { y: Float, distance: Float ->
        if (stepsY.indexOf(y) % 2 == 1) {
            distance * -1
        } else {
            distance
        }
    }

    fun getLines(): List<Pair<Offset, Offset>> {
        val lines = mutableListOf<Pair<Offset, Offset>>()

        lines.add(
            Pair(
                Offset(startX, startY),
                Offset(maxX, startY)
            )
        )

        stepsY.subList(1, numberOfTurns + 1).forEach { currentY ->
            lines.add(
                Pair(
                    lines.last().second,
                    Offset(lines.last().second.x, currentY)
                )
            )
            lines.add(
                Pair(
                    Offset(lines.last().second.x, currentY),
                    Offset(
                        if (lines.last().second.x == maxX) startX else maxX,
                        currentY
                    )
                )
            )
        }

        return lines
    }

    fun moveTo(distance: Float): Offset {
        val absoluteDistance = (length * (1.0 - distance)).toInt()
        return if (absoluteDistance < length) {
            moveFrom(
                startX,
                startY,
                length * (1 - distance)
            )
        } else {
            Offset(maxX, stepsY.last())
        }
    }

    private fun moveFrom(x: Float, y: Float, distance: Float): Offset {
        var currentX = x
        var currentY = y
        var remainingDistance = distance

        while (remainingDistance > 0) {
            val resultX = moveX(
                currentX,
                currentY,
                effectiveDistanceX(currentY, remainingDistance)
            )
            currentX = resultX.first
            remainingDistance = resultX.second
            if (remainingDistance > 0) {
                val resultY = moveY(currentY, remainingDistance)
                remainingDistance = resultY.second
                currentY = resultY.first
            }
        }

        return Offset(currentX, currentY)
    }

    private fun moveX(x: Float, y: Float, distance: Float): Pair<Float, Float> {
        var remainingDistance = distance

        if (!stepsY.contains(y)) {
            return Pair(x, remainingDistance)
        }

        val newX = if (x + distance in startX..maxX) {
            remainingDistance = 0f
            x + distance
        } else if (x + distance < startX) {
            remainingDistance += x - startX
            remainingDistance *= -1
            startX
        } else {
            remainingDistance -= maxX - x
            maxX
        }

        Logger.LOGGER.logPartialMove(newX = newX, remainingDistance = remainingDistance)
        return Pair(newX, remainingDistance)
    }

    private fun moveY(y: Float, distance: Float): Pair<Float, Float> {
        var remainingDistance = distance

        if (y == stepsY.last()) {
            return Pair(y, 0f)
        }

        val nextTurnAt = stepsY.filter { it > y }.min()
        val newY = if (y + distance <= nextTurnAt) {
            remainingDistance = 0f
            y + distance
        } else {
            remainingDistance -= nextTurnAt - y
            nextTurnAt
        }

        Logger.LOGGER.logPartialMove(newY = newY, remainingDistance = remainingDistance)
        return Pair(newY, remainingDistance)
    }
}