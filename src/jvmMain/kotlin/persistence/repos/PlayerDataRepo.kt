package persistence.repos

import persistence.PlayerData
import util.LetterUtil
import java.io.File

object PlayerDataRepo : AbstractJsonRepo<PlayerData>() {
    override val filePath: String
        get() = "$BASE_PATH/playerData.json"

    override fun getList(vararg keys: Any): List<PlayerData> {
        val file = File(filePath)

        return file.takeIf { it.isFile }?.let { f ->
            gson.fromJson<List<PlayerData>?>(f.readText(), genericType<List<PlayerData>>()).sortedBy { it.id }
        } ?: listOf()
    }

    override fun persist(entity: PlayerData, vararg keys: Any) {
        val list = getList(keys).toMutableList()

        if (!entity.laboratory.containsKey(entity.language)) {
            entity.laboratory.put(
                entity.language,
                PlayerData.Laboratory(activeLetters = LetterUtil.getSpecialLetters(entity.language))
            )
        }

        list.removeIf { it.id == entity.id }
        list.add(entity)

        File(filePath).writeText(gson.toJson(list))
    }
}