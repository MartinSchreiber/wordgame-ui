package persistence.repos

import constants.Language
import persistence.GameData
import java.io.File

object GameDataRepo : AbstractJsonRepo<GameData>() {
    override val filePath: String
        get() = "$BASE_PATH/gameData%d.json"

    override fun getList(vararg keys: Any): List<GameData> {
        val playerId = keys[0] as Int
        val language = keys[1] as Language

        return getAll(playerId)[language] ?: listOf()
    }

    override fun persist(entity: GameData, vararg keys: Any) {
        val playerId = keys[0] as Int
        val language = keys[1] as Language

        val all = getAll(playerId).mapValues { it.value.toMutableList() }.toMutableMap()

        if (all.containsKey(language)) {
            all[language]!!.add(entity)
        } else {
            all[language] = mutableListOf(entity)
        }

        File(filePath.format(playerId)).writeText(gson.toJson(all))
    }

    private fun getAll(playerId: Int): Map<Language, List<GameData>> {
        val file = File(filePath.format(playerId))

        if (file.isFile) {
            return gson.fromJson(
                file.readText(), genericType<Map<Language, List<GameData>>>()
            )
        }

        return mapOf()
    }
}