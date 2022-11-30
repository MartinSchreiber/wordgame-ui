package util

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.Offset
import model.gameField.Enemy
import model.gameField.Path

class EnemyUtil {
    companion object {
        fun getDefaultEnemies(path: Path, position: Offset): MutableList<Enemy> {
            return mutableListOf(
                Enemy(
                    path = path,
                    position = mutableStateOf(position)
                )
            )//, Enemy(path = path, delay = 60000))
        }
    }
    //TODO: give Enemy-List for various levels/difficulties
}