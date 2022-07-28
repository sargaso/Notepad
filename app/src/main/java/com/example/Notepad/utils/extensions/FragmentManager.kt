package com.example.Notepad.utils.extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.Notepad.R

fun FragmentManager.navigateToFragment(fragment: Fragment, addToBackStack: Boolean = false) {
    val fragmentTransaction = this.beginTransaction().replace(R.id.fragmentContainer, fragment)

    if (addToBackStack) {
        fragmentTransaction.addToBackStack(null).commit()
    } else {
        fragmentTransaction.commit()
    }
}