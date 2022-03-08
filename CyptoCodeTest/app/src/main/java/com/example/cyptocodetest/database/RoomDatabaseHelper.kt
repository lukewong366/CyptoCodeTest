package com.example.cyptocodetest.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cyptocodetest.model.CurrencyInfo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.io.InputStream

@Database(entities = [(CurrencyInfo::class)], version = 1)
abstract class RoomDatabaseHelper : RoomDatabase() {

    companion object {
        @Volatile private var instance: RoomDatabaseHelper? = null
        private val LOCK = Any()
        private const val DATABASE_NAME = "Database.db"

        operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it}
        }

        private fun buildDatabase(context: Context): RoomDatabaseHelper {
            val database = Room.databaseBuilder(
                context,
                RoomDatabaseHelper::class.java, DATABASE_NAME
            )
                .build()

            // insert mock data
            if (database.getRoomDao().getAll().isEmpty()) {
                val jsonFileString = getJsonDataFromAsset(context, "CurrencyData.json")
                val gson = Gson()
                val listCurrencyInfoType = object : TypeToken<List<CurrencyInfo>>() {}.type
                val currencyInfo: List<CurrencyInfo> =
                    gson.fromJson(jsonFileString, listCurrencyInfoType)
                database.getRoomDao().insertAll(*currencyInfo.toTypedArray())
            }
           return database
        }

        private fun getJsonDataFromAsset(context: Context, fileName: String): String? {
            val jsonString: String
            try {
                jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
            } catch (ioException: IOException) {
                ioException.printStackTrace()
                return null
            }
            return jsonString
        }
    }

    abstract fun getRoomDao(): RoomDao
}