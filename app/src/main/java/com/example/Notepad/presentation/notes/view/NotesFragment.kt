package com.example.Notepad.presentation.notes.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.Notepad.R
import com.example.Notepad.data.repository.NotesRepository
import com.example.Notepad.databinding.FragmentNotesBinding
import com.example.Notepad.presentation.add_note.view.AddNoteFragment
import com.example.Notepad.presentation.change_note.view.ChangeNotesFragment
import com.example.Notepad.presentation.notes.adapters.NoteClick
import com.example.Notepad.presentation.notes.adapters.NotesAdapter
import com.example.Notepad.presentation.notes.adapters.models.NoteVO
import com.example.Notepad.presentation.notes.adapters.models.toNoteDB
import com.example.Notepad.utils.extensions.navigateToFragment

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesFragment : Fragment(R.layout.fragment_notes), NoteClick {

    private var binding: FragmentNotesBinding? = null
    private val notesAdapter = NotesAdapter()
    private var notesRepository: NotesRepository? = null

    companion object {
        const val GET_NOTE_EXTRA_KEY = "GET_NOTE_EXTRA_KEY"
    }

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
        updateNotesAdapter()
        setListeners()
    }

    private fun setListeners() {
        binding?.fabAddNote?.setOnClickListener {
            requireActivity()
                .supportFragmentManager
                .navigateToFragment(
                    fragment = AddNoteFragment(),
                    addToBackStack = true
                )
        }
    }

    private fun initNotesRepository() {
        notesRepository = NotesRepository(
            context = requireContext()
                .applicationContext
        )
    }

    private fun setNotesAdapter() {
        notesAdapter.noteClickImpl = this
        binding?.rvNotes?.layoutManager = LinearLayoutManager(requireContext())
        binding?.rvNotes?.adapter = notesAdapter
    }

    private fun updateNotesAdapter() {
        lifecycleScope.launch(Dispatchers.IO) {
            notesAdapter.notesList = notesRepository?.getAllNotes()
                    as MutableList<NoteVO>
        }
    }

    override fun getNote(note: NoteVO) {
        val bundle = Bundle()
        bundle.putSerializable(GET_NOTE_EXTRA_KEY , note)

        val fragment = ChangeNotesFragment()
        fragment.arguments = bundle

        requireActivity().supportFragmentManager.navigateToFragment(
            fragment = fragment,
            addToBackStack = true
        )

    }

    override fun deleteNote(note: NoteVO) {
        notesRepository?.deleteNote(note = note.toNoteDB())

    }
}