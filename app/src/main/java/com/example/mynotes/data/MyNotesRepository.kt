package com.example.mynotes.data

import kotlinx.coroutines.flow.Flow

class MyNotesRepository(
    private val myNotesDao: MyNotesDao
) {
    suspend fun addNote(note: Note) = myNotesDao.insert(note)

    suspend fun updateNote(note: Note) = myNotesDao.update(note)

    fun getNotes(): Flow<List<Note>> = myNotesDao.getNotes()

    suspend fun deleteNotes(notes: List<Int>) = myNotesDao.deleteNotes(notes)
}