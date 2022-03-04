package com.example.lesson21.data.datasource.database.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.lesson21.data.datasource.database.room.dao.NotesDao
import com.example.lesson21.data.datasource.database.room.models.NoteDB

@Database(entities = [NoteDB::class], version = 1)
abstract class NotesDatabase : RoomDatabase() {

    abstract fun getNotesDao(): NotesDao

    companion object {

        private const val DATABASE_NAME = "notes_database"

        @Volatile
        private var INSTANCE: NotesDatabase? = null

        fun getNotesDatabase(context: Context): NotesDatabase {
            if (INSTANCE != null) {
                return INSTANCE as NotesDatabase
            }

            synchronized(this) {
                INSTANCE = Room.databaseBuilder(context.applicationContext,
                    NotesDatabase::class.java, DATABASE_NAME).build()

                return INSTANCE as NotesDatabase
            }
        }
    }
}