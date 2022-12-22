package persistence.repos

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.File

abstract class AbstractJsonRepo<T> {
    companion object {
        fun initDir() {
            File("./wordGameData").mkdir()
        }
    }

    protected inline fun <reified T> genericType() = object : TypeToken<T>() {}.type!!

    protected val gson = GsonBuilder().create()!!
    protected val basePath = "./wordGameData"
    abstract val filePath: String

    abstract fun getList(vararg keys: Any): List<T>
    abstract fun persist(entity: T, vararg keys: Any)
}