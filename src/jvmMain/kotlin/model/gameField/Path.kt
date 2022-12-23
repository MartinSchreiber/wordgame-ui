package model.gameField

import androidx.compose.ui.geometry.Offset

class Path(
    private val startX: Float,
    private val startY: Float,
    private val endX: Float,
    private val endY: Float,
    private val numberOfTurns: Int
) {
    private val lengthStepY = (endY - startY) / numberOfTurns
    private val numberOfStepsY = (0..numberOfTurns).toList().map { startY + (lengthStepY * it) }
    private val totalLength = (endX - startX) * (1 + numberOfTurns) + lengthStepY * numberOfTurns

    val base = Base(
        radius = lengthStepY / 2, center = Offset(
            endX + (lengthStepY / 2), endY
        )
    )

    fun getLines(): List<Line> {
        val lines = mutableListOf<Line>()

        lines.add(
            Line(
                Offset(startX, startY), Offset(endX, startY)
            )
        )

        numberOfStepsY.subList(1, numberOfTurns + 1).forEach { currentY ->
            lines.add(
                Line(
                    lines.last().end, Offset(lines.last().end.x, currentY)
                )
            )
            lines.add(
                Line(
                    Offset(lines.last().end.x, currentY),
                    Offset(if (lines.last().end.x == endX) startX else endX, currentY)
                )
            )
        }

        return lines
    }

    fun moveTo(distanceFromBase: Float): Offset {
        val absoluteDistance = (totalLength * (1.0 - distanceFromBase)).toInt()
        return if (absoluteDistance < totalLength) {
            moveFrom(
                startX, startY, totalLength * (1 - distanceFromBase)
            )
        } else {
            Offset(endX, endY)
        }
    }

    private fun moveFrom(x: Float, y: Float, distance: Float): Offset {
        var currentX = x
        var currentY = y
        var remainingDistance = distance

        while (remainingDistance > 0) {
            val afterHorizontalMove = moveHorizontally(
                currentX, currentY, effectiveDistanceX(currentY, remainingDistance)
            )

            currentX = afterHorizontalMove.coordinate
            remainingDistance = afterHorizontalMove.remainingDistance

            if (remainingDistance > 0) {
                val afterVerticalMove = moveVertically(currentY, remainingDistance)
                currentY = afterVerticalMove.coordinate
                remainingDistance = afterVerticalMove.remainingDistance
            }
        }

        return Offset(currentX, currentY)
    }

    private fun moveHorizontally(currentX: Float, currentY: Float, distance: Float): MoveResult {
        var remainingDistance = distance

        if (!numberOfStepsY.contains(currentY)) {
            return MoveResult(currentX, remainingDistance)
        }

        val newX = if (currentX + distance in startX..endX) {
            remainingDistance = 0f
            currentX + distance
        } else if (currentX + distance < startX) {
            remainingDistance += currentX - startX
            remainingDistance *= -1
            startX
        } else {
            remainingDistance -= endX - currentX
            endX
        }

        return MoveResult(newX, remainingDistance)
    }

    private fun moveVertically(currentY: Float, distance: Float): MoveResult {
        var remainingDistance = distance

        if (currentY == numberOfStepsY.last()) {
            return MoveResult(currentY, 0f)
        }

        val nextTurnAt = numberOfStepsY.filter { it > currentY }.min()
        val newY = if (currentY + distance <= nextTurnAt) {
            remainingDistance = 0f
            currentY + distance
        } else {
            remainingDistance -= nextTurnAt - currentY
            nextTurnAt
        }

        return MoveResult(newY, remainingDistance)
    }

    private fun effectiveDistanceX(y: Float, distance: Float) = if (numberOfStepsY.indexOf(y) % 2 == 1) {
        distance * -1
    } else {
        distance
    }

    data class Line(val start: Offset, val end: Offset)

    inner class MoveResult(val coordinate: Float, val remainingDistance: Float)
}