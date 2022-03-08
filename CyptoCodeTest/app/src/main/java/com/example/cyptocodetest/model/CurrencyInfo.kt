package com.example.cyptocodetest.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Currency")
class CurrencyInfo {
    @PrimaryKey
    var id: String = ""
    @ColumnInfo(name = "name")
    var name: String = ""
    @ColumnInfo(name = "symbol")
    var symbol: String = ""
}

