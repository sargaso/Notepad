package com.example.Notepad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.Notepad.presentation.notes.view.NotesFragment
import com.example.Notepad.utils.extensions.navigateToFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.navigateToFragment(fragment = NotesFragment())
    }
}