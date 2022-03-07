package com.example.lesson21.data.datasource.database.room.dao

import androidx.room.*
import com.example.lesson21.data.datasource.database.room.models.NoteDB

@Dao
interface NotesDao {


    @Insert
    fun insertNote(note: NoteDB)

    @Query("SELECT * FROM Notes")
    fun getAllNotes(): List<NoteDB>

    @Delete
    fun deleteNote(note: NoteDB)

    @Update
    fun updateNote(note: NoteDB)
}