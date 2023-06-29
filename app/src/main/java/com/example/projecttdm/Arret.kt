package com.example.projecttdm

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import java.util.Date

@Entity(tableName="arret_table")
data class Arret (
    @PrimaryKey(autoGenerate = true) val num : Int?,
    @ColumnInfo(name = "source") val source : String?,

    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "is_favorite") var isFavorite : Boolean?
    )