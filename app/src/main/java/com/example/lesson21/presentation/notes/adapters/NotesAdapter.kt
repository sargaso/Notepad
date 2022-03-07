package com.example.lesson21.presentation.notes.adapters

import android.icu.text.Transliterator
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson21.databinding.RecyclerViewNoteBinding
import com.example.lesson21.presentation.notes.adapters.models.NoteVO

class NotesAdapter : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    var notesList = mutableListOf<NoteVO>()
    var noteClickImpl: NoteClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(
            binding = RecyclerViewNoteBinding
                .inflate(LayoutInflater.from(parent.context), parent, false),
            noteClickImpl = noteClickImpl
        )
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        with(holder) {
            bind(note = notesList[position])
            setListeners(notesList[position],
                deleteNote = {
                    notesList.removeAt(position)
                    notifyItemRemoved(adapterPosition)
                    notifyItemRangeChanged(position, notesList.size)
                })


        }

    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    class NotesViewHolder(
        val binding: RecyclerViewNoteBinding,
        private val noteClickImpl: NoteClick?
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(note: NoteVO) {
            with(binding) {
                tvNoteTitle.text = note.noteTitle
                tvNoteCreatedTime.text = note.noteCreatedTime
            }
        }

        fun setListeners(note: NoteVO, deleteNote: () -> Unit) {
            with(binding) {
                ivDeleteNote.setOnClickListener {
                    noteClickImpl?.deleteNote(note = note)
                    deleteNote()
                }
                root.setOnClickListener {
                    noteClickImpl?.getNote(note = note)
                }
            }
        }
    }
}

interface NoteClick {

    fun getNote(note: NoteVO)

    fun deleteNote(note: NoteVO)
}