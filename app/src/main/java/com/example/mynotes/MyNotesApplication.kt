package com.example.mynotes

import android.app.Application
import com.example.mynotes.data.NotesContainer

class MyNotesApplication: Application() {
    lateinit var container: NotesContainer

    override fun onCreate() {
        super.onCreate()
        container = NotesContainer(this)
    }

}