package control

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshots.SnapshotStateList
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import model.Enemy
import model.LetterChambers
import model.Word
import model.WordGame


@Composable
fun backgroundTasks(wordGame: WordGame) {
    val backgroundScope = rememberCoroutineScope()
    backgroundScope.launch {
        spawnEnemies(
            enemiesIncoming = wordGame.enemiesIncoming,
            enemiesOnField = wordGame.enemiesOnField
        )
    }
    backgroundScope.launch {
        moveEnemies(
            enemies = wordGame.enemiesOnField,
            isOver = wordGame.isOver
        )
    }
    backgroundScope.launch {
        fireLetters(
            queue = wordGame.wordQueue,
            enemiesOnField = wordGame.enemiesOnField,
            chambers = wordGame.letterChambers,
            isOver = wordGame.isOver
        )
    }
}

suspend fun moveEnemies(enemies: SnapshotStateList<Enemy>, isOver: () -> Boolean) = coroutineScope {
    while (!isOver()) {
        enemies.forEach {
            it.move()
        }
        enemies.removeIf { it.reachedEnd() }
        delay(100)
    }
}

suspend fun spawnEnemies(enemiesIncoming: MutableList<Enemy>, enemiesOnField: SnapshotStateList<Enemy>) =
    coroutineScope {
        while (enemiesIncoming.isNotEmpty()) {
            enemiesIncoming.firstOrNull()?.let {
                delay(it.delay)
                enemiesOnField.add(it)
            }
            enemiesIncoming.removeFirst()
        }
    }

suspend fun fireLetters(
    queue: SnapshotStateList<Word>,
    enemiesOnField: SnapshotStateList<Enemy>,
    chambers: LetterChambers,
    isOver: () -> Boolean
) = coroutineScope {
    while (!isOver()) {
        if (queue.isNotEmpty() && enemiesOnField.isNotEmpty()) {
            val word = queue.removeFirst()
            while (word.size() > 0 && !isOver()) {
                if (enemiesOnField.isNotEmpty()) {
                    val letter = word.removeFirstLetter()
                    enemiesOnField.minBy { it.distance }.damage(letter.totalValue)
                    enemiesOnField.removeIf { it.health.value <= 0 }
                    println("########### $letter (${letter.totalValue})")

                    chambers.loadLetters(listOf(letter))
                }
                delay(100)
            }
            delay(1000)
        }
        delay(250)
    }
    println("GAME OVER")
}