package persistence.repos

import constants.Language
import persistence.StatisticsData
import java.io.File

object StatisticsDataRepo : AbstractJsonRepo<StatisticsData>() {

    override val filePath: String
        get() = "$BASE_PATH/statisticsData%d.json"

    override fun getList(vararg keys: Any): List<StatisticsData> {
        val playerId = keys[0] as Int
        val language = keys[1] as Language

        return getAll(playerId)[language] ?: listOf()
    }

    override fun persist(entity: StatisticsData, vararg keys: Any) {
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

    private fun getAll(playerId: Int): Map<Language, List<StatisticsData>> {
        val file = File(filePath.format(playerId))

        if (file.isFile) {
            return gson.fromJson(
                file.readText(), genericType<Map<Language, List<StatisticsData>>>()
            )
        }

        return mapOf()
    }
}