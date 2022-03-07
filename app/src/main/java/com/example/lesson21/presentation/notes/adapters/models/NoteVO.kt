package com.example.lesson21.presentation.notes.adapters.models

import com.example.lesson21.data.datasource.database.room.models.NoteDB
import java.io.Serializable

data class NoteVO(
    val id: Int,
    val noteTitle: String,
    val noteCreatedTime: String,
    val noteDescription: String
): Serializable

fun NoteVO.toNoteDB(): NoteDB {
    return NoteDB(
        id = id,
        noteTitle = noteTitle,
        noteDescription = noteDescription,
        noteCreatedTime = noteCreatedTime
    )
}