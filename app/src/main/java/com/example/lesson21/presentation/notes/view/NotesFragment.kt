package com.example.lesson21.presentation.notes.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lesson21.R
import com.example.lesson21.data.repository.NotesRepository
import com.example.lesson21.databinding.FragmentNotesBinding
import com.example.lesson21.presentation.add_note.view.AddNoteFragment
import com.example.lesson21.presentation.notes.adapters.NotesAdapter
import com.example.lesson21.presentation.notes.adapters.models.NoteVO
import com.example.lesson21.utils.extensions.navigateToFragment

class NotesFragment : Fragment(R.layout.fragment_notes) {

    companion object {
        var noteExists = false
    }

    private var binding: FragmentNotesBinding? = null
    private val notesAdapter = NotesAdapter()
    private var notesRepository: NotesRepository? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentNotesBinding
            .inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initNotesRepository()
        setNotesAdapter()
        if (noteExists) {
            updateNotesAdapter()
        }
        setListeners()
    }

    private fun setListeners() {
        binding?.fabAddNote?.setOnClickListener {
            requireActivity()
                .supportFragmentManager
                .navigateToFragment(fragment = AddNoteFragment(),
                    addToBackStack = true)
        }
    }

    private fun initNotesRepository() {
        notesRepository = NotesRepository(context = requireContext()
            .applicationContext)
    }

    private fun setNotesAdapter() {
        binding?.rvNotes?.layoutManager = LinearLayoutManager(requireContext())
        binding?.rvNotes?.adapter = notesAdapter
    }

    private fun updateNotesAdapter() {
        notesAdapter.notesList = notesRepository?.getAllNotes()
                as MutableList<NoteVO>
    }
}