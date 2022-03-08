package com.example.cyptocodetest.database

import androidx.room.*
import com.example.cyptocodetest.model.CurrencyInfo

@Dao
interface RoomDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: CurrencyInfo): Long

    @Insert
    fun insertAll(vararg item: CurrencyInfo)

    @Query("SELECT * FROM Currency")
    fun getAll(): List<CurrencyInfo>

    @Query("SELECT * FROM Currency ORDER BY name ASC")
    fun getASCSorting(): List<CurrencyInfo>

    @Query("SELECT * FROM Currency ORDER BY name DESC")
    fun getDESCSorting(): List<CurrencyInfo>

    @Delete
    fun delete(item: CurrencyInfo)

    @Update
    fun update(item: CurrencyInfo)
}