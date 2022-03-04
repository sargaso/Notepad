package com.example.lesson21.presentation.add_note.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lesson21.data.datasource.database.room.models.NoteDB
import com.example.lesson21.data.repository.NotesRepository
import com.example.lesson21.databinding.FragmentNoteAddBinding
import com.example.lesson21.presentation.notes.view.NotesFragment.Companion.noteExists
import java.text.SimpleDateFormat
import java.util.*

class AddNoteFragment : Fragment() {

    private var binding: FragmentNoteAddBinding? = null
    private var notesRepository: NotesRepository? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentNoteAddBinding
            .inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initNotesRepository()
        setListeners()
    }

    private fun initNotesRepository() {
        notesRepository = NotesRepository(context = requireContext()
            .applicationContext)
    }

    private fun setListeners() {
        binding?.btnSaveNote?.setOnClickListener {
            val sdf = SimpleDateFormat("dd.M.yyyy hh:mm:ss", Locale.getDefault())

            val noteDB = NoteDB(noteTitle =
            binding?.etNoteTitle?.text.toString(),
                noteDescription = binding?.etNoteDescription?.text.toString(),
            noteCreatedTime = sdf.format(Date()))

            notesRepository?.insertNote(note = noteDB)
            noteExists = true
        }
    }
}