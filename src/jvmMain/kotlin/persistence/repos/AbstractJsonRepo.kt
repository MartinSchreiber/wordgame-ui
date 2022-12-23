package persistence.repos

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.File

abstract class AbstractJsonRepo<T> {
    companion object {
        const val BASE_PATH = "./wordGameData"

        fun initDir() {
            File(BASE_PATH).mkdir()
        }
    }

    protected inline fun <reified T> genericType() = object : TypeToken<T>() {}.type!!

    protected val gson = GsonBuilder().create()!!
    abstract val filePath: String

    abstract fun getList(vararg keys: Any): List<T>
    abstract fun persist(entity: T, vararg keys: Any)
}