package com.example.mynotes.data

import android.content.Context

class NotesContainer(context: Context) {
    val notesRepository: MyNotesRepository by lazy {
        MyNotesRepository(MyNotesDatabase.getDatabase(context).MyNotesDao())
    }
}