package com.example.workapplication.utils

import android.util.Patterns

object FormatChecker {

    fun String?.isValidEmail() = !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

}