package com.example.Notepad.data.datasource.database.room.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.Notepad.presentation.notes.adapters.models.NoteVO

@Entity(tableName = "Notes")//Создаем отдельную таблицу с указанным названием
data class NoteDB(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,//Автоматически генерируем id
    val noteTitle: String,
    val noteDescription: String,
    val noteCreatedTime: String
)

fun NoteDB.toNoteVO(): NoteVO { //Mappers- Маппим модель,из одного типа данных превращаем другой тип
    //из типа NoteDB в NoteVO
    return NoteVO(
        id = id,
        noteTitle = noteTitle,
        noteDescription =noteDescription,
        noteCreatedTime = noteCreatedTime
    )
}
