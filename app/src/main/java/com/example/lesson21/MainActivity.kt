package com.example.lesson21

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lesson21.presentation.notes.view.NotesFragment
import com.example.lesson21.utils.extensions.navigateToFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.navigateToFragment(fragment = NotesFragment())
    }
}