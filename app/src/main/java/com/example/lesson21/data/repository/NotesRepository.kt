package com.example.lesson21.data.repository

import android.content.Context
import com.example.lesson21.data.datasource.database.room.database.NotesDatabase.Companion.getNotesDatabase
import com.example.lesson21.data.datasource.database.room.models.NoteDB
import com.example.lesson21.data.datasource.database.room.models.toNoteVO
import com.example.lesson21.presentation.notes.adapters.models.NoteVO

class NotesRepository(private val context: Context) {

    fun insertNote(note: NoteDB) {
        getNotesDatabase(context = context)
            .getNotesDao().insertNote(note = note)
    }

    fun getAllNotes(): List<NoteVO> {
        return getNotesDatabase(context = context).getNotesDao()
            .getAllNotes().map { noteDB ->
                noteDB.toNoteVO()
            }
    }

    fun deleteNote(note: NoteDB) {
        getNotesDatabase(context = context)
            .getNotesDao().deleteNote(note = note)
    }

    fun updateNote(note: NoteDB) {
        getNotesDatabase(context = context)
            .getNotesDao().updateNote(note = note)
    }
}