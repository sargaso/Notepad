package com.example.Notepad.presentation.add_note.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.Notepad.data.datasource.database.room.models.NoteDB
import com.example.Notepad.data.repository.NotesRepository
import com.example.Notepad.databinding.FragmentAddNoteBinding
import com.example.Notepad.presentation.notes.view.NotesFragment
import com.example.Notepad.utils.extensions.navigateToFragment
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*

class AddNoteFragment : Fragment() {
    companion object {
        private const val DATE_FORMAT = "dd.M.yyyy hh:mm:ss"
        private const val SNACK_BAR_FIELDS_ARE_BLANK_TEXT =
            "Please make sure that fields are filed up"
    }

    private var binding: FragmentAddNoteBinding? = null
    private var notesRepository: NotesRepository? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentAddNoteBinding
            .inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initNotesRepository()
        setListeners()
    }

    private fun initNotesRepository() {
        notesRepository = NotesRepository(
            context = requireContext()
                .applicationContext
        )
    }

    private fun setListeners() {
        binding?.btnSaveNote?.setOnClickListener {
            if ((binding?.etNoteTitle?.text?.isNotBlank() == true) &&
                (binding?.etNoteDescription?.text?.isNotBlank() == true)
            ) {
                val simpleDateFormat = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())

                val noteDB = NoteDB(
                    noteTitle =
                    binding?.etNoteTitle?.text.toString(),
                    noteDescription = binding?.etNoteDescription?.text.toString(),
                    noteCreatedTime = simpleDateFormat.format(Date())
                )

                notesRepository?.insertNote(note = noteDB)

                with(requireActivity().supportFragmentManager) {
                    navigateToFragment(fragment = NotesFragment())
                }
            } else {
                binding?.root?.let { view ->
                    Snackbar.make(
                        view, SNACK_BAR_FIELDS_ARE_BLANK_TEXT,
                        Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }
}