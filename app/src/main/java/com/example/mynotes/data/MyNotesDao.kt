package com.example.mynotes.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface MyNotesDao {
    @Update
    suspend fun update(note: Note)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)

    @Query("SELECT * FROM notes ORDER BY lastInteraction DESC")
    fun getNotes(): Flow<List<Note>>

    @Query("DELETE from notes WHERE id in (:ids)")
    suspend fun deleteNotes(ids: List<Int>)
}