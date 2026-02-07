package com.example.mynotes.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class MyNotesDatabase: RoomDatabase() {
    abstract fun MyNotesDao(): MyNotesDao

    companion object {
        private var Instance: MyNotesDatabase? = null

        fun getDatabase(context: Context): MyNotesDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, MyNotesDatabase::class.java, "my_notes_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}