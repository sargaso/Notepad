package com.example.lesson21.presentation.change_note.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lesson21.data.repository.NotesRepository
import com.example.lesson21.databinding.FragmentChangeNotesBinding
import com.example.lesson21.presentation.notes.adapters.models.NoteVO
import com.example.lesson21.presentation.notes.adapters.models.toNoteDB
import com.example.lesson21.presentation.notes.view.NotesFragment
import com.example.lesson21.presentation.notes.view.NotesFragment.Companion.GET_NOTE_EXTRA_KEY
import com.example.lesson21.utils.extensions.navigateToFragment

class ChangeNotesFragment : Fragment() {

    private var binding: FragmentChangeNotesBinding? = null
    private var passedNote: NoteVO? = null
    private var notesRepository: NotesRepository? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentChangeNotesBinding
            .inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setInitialData()
        initNotesRepository()
        setListeners()
    }

    private fun setInitialData() {
        passedNote = arguments?.getSerializable(GET_NOTE_EXTRA_KEY) as NoteVO
        binding?.etNoteTitle?.setText(passedNote?.noteTitle)
        binding?.etNoteDescription?.setText(passedNote?.noteDescription)

    }

    private fun initNotesRepository() {
        notesRepository = NotesRepository(context = requireContext().applicationContext)
    }

    private fun setListeners() {
        binding?.btnChangeNote?.setOnClickListener {
            val changeNote = passedNote?.let { note ->
                NoteVO(
                    id = note.id,
                    noteTitle = binding?.etNoteTitle?.text.toString(),
                    noteDescription = binding?.etNoteDescription?.text.toString(),
                    noteCreatedTime = note.noteCreatedTime
                )
            }
            changeNote?.let { note ->
                notesRepository?.updateNote(note = note.toNoteDB())
            }
           requireActivity().supportFragmentManager.navigateToFragment(fragment = NotesFragment())
        }
    }
}