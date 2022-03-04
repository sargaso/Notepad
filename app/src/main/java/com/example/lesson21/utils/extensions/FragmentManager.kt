package com.example.lesson21.utils.extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.lesson21.R

fun FragmentManager.navigateToFragment(fragment: Fragment, addToBackStack: Boolean = false) {
    val fragmentTransaction = this.beginTransaction().replace(R.id.fragmentContainer, fragment)

    if (addToBackStack) {
        fragmentTransaction.addToBackStack(null).commit()
    } else {
        fragmentTransaction.commit()
    }
}