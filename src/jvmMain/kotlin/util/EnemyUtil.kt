package util

import model.gameField.Enemy
import model.gameField.Path

class EnemyUtil {
    companion object {
        fun getDefaultEnemies(path: Path): MutableList<Enemy> {
            return mutableListOf(Enemy(path = path))//, Enemy(path = path, delay = 60000))
        }
    }
    //TODO: give Enemy-List for various levels/difficulties
}