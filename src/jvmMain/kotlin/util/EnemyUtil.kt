package util

import model.Enemy
import model.Path

class EnemyUtil {
    companion object {
        fun getDefaultEnemies(path: Path): List<Enemy> {
            return listOf(Enemy(path = path))//, Enemy(path = PATH, delay = 60000))
        }
    }
    //TODO: give Enemy-List for various levels/difficulties
}