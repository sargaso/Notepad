package com.example.lesson21.presentation.notes.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson21.databinding.RecyclerViewNoteBinding
import com.example.lesson21.presentation.notes.adapters.models.NoteVO

class NotesAdapter : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    var notesList = mutableListOf<NoteVO>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(binding = RecyclerViewNoteBinding
            .inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.bind(note = notesList[position])
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    class NotesViewHolder(val binding: RecyclerViewNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(note: NoteVO) {
            with(binding) {
                tvNoteTitle.text = note.noteTitle
                tvNoteCreatedTime.text = note.noteCreatedTime
            }
        }
    }
}