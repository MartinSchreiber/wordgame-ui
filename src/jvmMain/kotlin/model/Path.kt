package model

//TODO: Generalise and document
class Path(
    private val startX: Int = 20,
    private val startY: Int = 20,
    private val maxX: Int = 160,
    private val stepY: Int = 30,
    private val numberOfTurns: Int = 2
) {
    private val stepsY = (0..numberOfTurns)
        .toList()
        .map { startY + (stepY * it) }
    val moveBackwards = { y: Int -> stepsY.indexOf(y) % 2 == 1 }

    fun moveFrom(x: Int, y: Int, distance: Int): Pair<Pair<Int, Int>, Boolean> {
        var currentX = x
        var currentY = y
        var distanceRemaining = distance

        while (distanceRemaining > 0) {
            val resultX = moveX(x, y, if (moveBackwards(y)) distanceRemaining * -1 else distanceRemaining)
            currentX = resultX.first
            distanceRemaining = resultX.second
            if (distanceRemaining > 0) {
                val resultY = moveY(y, distanceRemaining)
                distanceRemaining = resultY.second
                currentY = resultY.first
            }
        }

        val reachedEnd = currentY == stepsY.last() && currentX == maxX

        return Pair(Pair(currentX, currentY), reachedEnd)
    }

    //TODO optimise
    fun getLines(): List<Pair<Pair<Int, Int>, Pair<Int, Int>>> {
        val lines = mutableListOf<Pair<Pair<Int, Int>, Pair<Int, Int>>>()

        lines.add(
            Pair(
                Pair(startX, startY),
                Pair(maxX, startY)
            )
        )

        stepsY.subList(1, numberOfTurns + 1).forEach {
            lines.add(
                Pair(
                    Pair(lines.last().second.first, lines.last().second.second),
                    Pair(lines.last().second.first, it)
                )
            )
            lines.add(
                Pair(
                    Pair(lines.last().second.first, it),
                    Pair(startX, it)
                )
            )
        }

        lines.add(
            Pair(
                Pair(startX, lines.last().second.second),
                Pair(maxX, lines.last().second.second)
            )
        )

        return lines
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

        println("moveX: $xNew \t $distanceRemaining")
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

        println("moveY: $yNew \t $distanceRemaining")
        return Pair(yNew, distanceRemaining)
    }
}