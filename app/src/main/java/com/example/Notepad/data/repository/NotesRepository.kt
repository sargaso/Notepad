package com.example.Notepad.data.repository

import android.content.Context
import com.example.Notepad.data.datasource.database.room.database.NotesDatabase.Companion.getNotesDatabase
import com.example.Notepad.data.datasource.database.room.models.NoteDB
import com.example.Notepad.data.datasource.database.room.models.toNoteVO
import com.example.Notepad.presentation.notes.adapters.models.NoteVO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class NotesRepository(private val context: Context) {

    fun insertNote(note: NoteDB) {
       CoroutineScope(Dispatchers.IO).launch {
           getNotesDatabase(context = context)
               .getNotesDao().insertNote(note = note)
       }
    }

    suspend  fun getAllNotes(): List<NoteVO> {
        val notes= CoroutineScope(Dispatchers.IO).async {
            getNotesDatabase(context = context).getNotesDao()
                .getAllNotes().map { noteDB ->
                    noteDB.toNoteVO()
                }
        }
        return notes.await()
    }

    fun deleteNote(note: NoteDB) {
        CoroutineScope(Dispatchers.IO).launch {
            getNotesDatabase(context = context)
                .getNotesDao().deleteNote(note = note)
        }
    }

    fun updateNote(note: NoteDB) {
        CoroutineScope(Dispatchers.IO).launch {
            getNotesDatabase(context = context)
                .getNotesDao().updateNote(note = note)
        }
    }
}