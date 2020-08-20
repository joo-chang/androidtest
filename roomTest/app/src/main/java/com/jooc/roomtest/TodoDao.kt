package com.jooc.roomtest

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface TodoDao {
    @Query("SELECT * FROM todo")
    fun getAll(): List<Todo>

    @Insert(onConflict = REPLACE)
    fun insert(todo: Todo)

    @Query("DELETE FROM todo")
    fun deleteAll()

}