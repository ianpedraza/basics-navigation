package com.ianpedraza.android.navigation.utils

import android.view.View
import androidx.navigation.NavDirections
import androidx.navigation.findNavController

fun View.navigate(destination: NavDirections) {
    findNavController().navigate(destination)
}