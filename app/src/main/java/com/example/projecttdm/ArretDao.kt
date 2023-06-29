package com.example.projecttdm

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.OnConflictStrategy

@Dao
interface ArretDao {
    @Query("SELECT * FROM arret_table")
    fun getAll(): List<Arret>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(arret : Arret)

    @Delete
    fun delete(arret:Arret)

    @Query("DELETE from arret_table")
    fun deleteAll()
}