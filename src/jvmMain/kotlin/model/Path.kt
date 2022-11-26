package model

//TODO: Generalise and document
class Path(
    private val startX: Int = 20,
    private val startY: Int = 20,
    private val maxX: Int = 320,
    private val stepY: Int = 60,
    private val numberOfTurns: Int = 2
) {
    private val stepsY = (0..numberOfTurns)
        .toList()
        .map { startY + (stepY * it) }
    private val length = (maxX - startX) * (1 + numberOfTurns) + stepY * numberOfTurns

    private val effectiveDistanceX = { y: Int, distance: Int ->
        if (stepsY.indexOf(y) % 2 == 1) {
            distance * -1
        } else {
            distance
        }
    }

    fun getLines(): List<Pair<Point, Point>> {
        val lines = mutableListOf<Pair<Point, Point>>()

        lines.add(
            Pair(
                Point(startX, startY),
                Point(maxX, startY)
            )
        )

        stepsY.subList(1, numberOfTurns + 1).forEach { currentY ->
            lines.add(
                Pair(
                    lines.last().second,
                    Point(lines.last().second.x, currentY)
                )
            )
            lines.add(
                Pair(
                    Point(lines.last().second.x, currentY),
                    Point(
                        if (lines.last().second.x == maxX) startX else maxX,
                        currentY
                    )
                )
            )
        }

        return lines
    }

    fun moveTo(distance: Double): Point {
        val effectiveDistance = (length * (1.0 - distance)).toInt()
        return if (effectiveDistance < length) {
            moveFrom(
                startX,
                startY,
                (length * (1.0 - distance)).toInt()
            )
        } else {
            Point(maxX, stepsY.last())
        }
    }

    private fun moveFrom(x: Int, y: Int, distance: Int): Point {
        var currentX = x
        var currentY = y
        var distanceRemaining = distance

        while (distanceRemaining > 0) {
            val resultX = moveX(
                currentX,
                currentY,
                effectiveDistanceX(currentY, distanceRemaining)
            )
            currentX = resultX.first
            distanceRemaining = resultX.second
            if (distanceRemaining > 0) {
                val resultY = moveY(currentY, distanceRemaining)
                distanceRemaining = resultY.second
                currentY = resultY.first
            }
        }

        return Point(currentX, currentY)
    }

    private fun moveX(x: Int, y: Int, distance: Int): Pair<Int, Int> {
        var distanceRemaining = distance

        if (!stepsY.contains(y)) {
            return Pair(x, distanceRemaining)
        }

        val xNew = if (x + distance in startX..maxX) {
            distanceRemaining = 0
            x + distance
        } else if (x + distance < startX) {
            distanceRemaining += x - startX
            distanceRemaining *= -1
            startX
        } else {
            distanceRemaining -= maxX - x
            maxX
        }

        //println("moveX: $xNew \t $distanceRemaining")
        return Pair(xNew, distanceRemaining)
    }

    private fun moveY(y: Int, distance: Int): Pair<Int, Int> {
        var distanceRemaining = distance

        if (y == stepsY.last()) {
            return Pair(y, 0)
        }

        val nextTurnAt = stepsY.filter { it > y }.min()
        val yNew = if (y + distance <= nextTurnAt) {
            distanceRemaining = 0
            y + distance
        } else {
            distanceRemaining -= nextTurnAt - y
            nextTurnAt
        }

        //println("moveY: $yNew \t $distanceRemaining")
        return Pair(yNew, distanceRemaining)
    }

    data class Point(val x: Int, val y: Int)
}