package control

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshots.SnapshotStateList
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import model.*


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
            path = wordGame.enemyPath
        )
    }
    backgroundScope.launch {
        fireLetters(
            queue = wordGame.wordQueue.toMutableStateList(),
            enemiesOnField = wordGame.enemiesOnField,
            chambers = wordGame.letterChambers
        )
    }
}

suspend fun moveEnemies(enemies: SnapshotStateList<Enemy>, path: Path) = coroutineScope {
    while (true) {
        enemies.forEach {
            val newPosition = path.moveFrom(it.position.value.first, it.position.value.second, it.speed)
            it.moveTo(newPosition.first)
            it.reachedEnd = newPosition.second
            println("enemy $it moved to (${newPosition.first.first}|${newPosition.first.second})")
        }
        enemies.filter { it.reachedEnd }.forEach {
            println("enemy $it reached the end!")
        }
        enemies.removeIf { it.reachedEnd }
        delay(100L)
    }
}

suspend fun spawnEnemies(enemiesIncoming: MutableList<Enemy>, enemiesOnField: SnapshotStateList<Enemy>) =
    coroutineScope {
        while (enemiesIncoming.isNotEmpty()) {
            enemiesIncoming.removeLastOrNull()?.let {
                delay(it.delay)
                enemiesOnField.add(it)
            }
        }
    }

suspend fun fireLetters(
    queue: SnapshotStateList<Word>,
    enemiesOnField: SnapshotStateList<Enemy>,
    chambers: LetterChambers
) = coroutineScope {
    while (true) {
        if (queue.isNotEmpty()) { // && enemiesOnField.isNotEmpty()
            queue.removeFirst().letters.forEach {

                //TODO implement "damage" first enemy in line
                chambers.loadLetters(listOf(it))

                delay(200)
            }
        }
        delay(1000)
    }
}

//TODO drop special letters