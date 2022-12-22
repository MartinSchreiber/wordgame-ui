package constants

import model.gameField.EnemyGroup

enum class Level(val enemyGroups: List<EnemyGroup>) {
    L1(
        listOf(EnemyGroup(10f, 20))
    ),
    L2(
        EnemyGroup(10f, 10) +
                EnemyGroup(20f, 10)
    ),
    L3(
        EnemyGroup(10f, 10) +
                EnemyGroup(20f, 5) +
                EnemyGroup(10f, 10)
    ),
    L4(
        EnemyGroup(10f, 10) +
                EnemyGroup(20f, 5) +
                EnemyGroup(50f, 1)
    ),
    L5(
        EnemyGroup(10f, 5, 2f) +
                EnemyGroup(20f, 5) +
                EnemyGroup(10f, 5, 2f) +
                EnemyGroup(50f, 1)
    ),
    L6(
        EnemyGroup(20f, 5) +
                EnemyGroup(50f, 1) +
                EnemyGroup(20f, 5) +
                EnemyGroup(50f, 1) +
                EnemyGroup(10f, 5, 2f)
    ),
    L7(
        EnemyGroup(20f, 5, 2f) +
                EnemyGroup(50f, 1) +
                EnemyGroup(20f, 5, 2f) +
                EnemyGroup(50f, 1) +
                EnemyGroup(10f, 5, 2f)
    ),
    L8(
        EnemyGroup(10f, 10, 2f) +
                EnemyGroup(50f, 1, 2f) +
                EnemyGroup(10f, 10, 2f) +
                EnemyGroup(50f, 1, 2f)
    ),
    L9(
        EnemyGroup(10f, 10, 2f) +
                EnemyGroup(50f, 1, 2f) +
                EnemyGroup(20f, 5, 2.5f) +
                EnemyGroup(50f, 1, 2f)
    ),
    L10(
        EnemyGroup(10f, 10, 2.5f) +
                EnemyGroup(50f, 2, 2f) +
                EnemyGroup(20f, 5, 2.5f) +
                EnemyGroup(50f, 2, 2f)
    );

    fun hasNext(): Boolean {
        return ordinal < values().size - 1
    }

    fun getNext(): Level {
        return values()[ordinal + 1]
    }
}