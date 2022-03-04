package com.example.lesson21.data.datasource.database.room.models

import androidx.room.Entity
import com.example.lesson21.presentation.notes.adapters.models.NoteVO

@Entity(tableName = "Notes")
data class NoteDB(val noteTitle: String, val noteDescription: String,
                  val noteCreatedTime: String)

fun NoteDB.toNoteVO(): NoteVO {
    return NoteVO(noteTitle = noteTitle,
        noteCreatedTime = noteCreatedTime)
}
