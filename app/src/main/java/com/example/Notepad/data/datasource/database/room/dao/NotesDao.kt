package com.example.Notepad.data.datasource.database.room.dao

import androidx.room.*
import com.example.Notepad.data.datasource.database.room.models.NoteDB

//функции Room для работы с БД
@Dao
interface NotesDao {


    @Insert
    fun insertNote(note: NoteDB)

    @Query("SELECT * FROM Notes") //пишем через query потому что нет функции get
    fun getAllNotes(): List<NoteDB>

    @Delete
    fun deleteNote(note: NoteDB)

    @Update
    fun updateNote(note: NoteDB)
}