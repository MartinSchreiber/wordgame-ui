package control

import androidx.compose.runtime.snapshots.SnapshotStateList
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import model.*


suspend fun backgroundTasks(wordGame: WordGame) {
    spawnEnemies(
        enemiesIncoming = wordGame.enemiesIncoming,
        enemiesOnField = wordGame.enemiesOnField
    )
    moveEnemies(
        enemies = wordGame.enemiesOnField
    )
    fireLetters(
        queue = wordGame.mutableStateQueue.toMutableStateList(),
        enemiesOnField = wordGame.enemiesOnField,
        chambers = wordGame.letterChambers
    )
}

suspend fun moveEnemies(enemies: SnapshotStateList<Enemy>) = coroutineScope {
    //TODO implement
}

suspend fun spawnEnemies(enemiesIncoming: MutableList<Enemy>, enemiesOnField: SnapshotStateList<Enemy>) =
    coroutineScope {
        //TODO implement
    }

suspend fun fireLetters(queue: SnapshotStateList<Word>, enemiesOnField: SnapshotStateList<Enemy>, chambers: LetterChambers) = coroutineScope {
    while(true) {
        if (queue.isNotEmpty()) { // && enemiesOnField.isNotEmpty()
            queue.removeFirst().letters.forEach {


                //TODO implement damage first enemy in line
                chambers.loadLetters(listOf(it))


                delay(200)
            }
        }
        delay(1000)
    }
}