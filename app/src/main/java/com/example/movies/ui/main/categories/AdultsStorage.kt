package com.example.movies.ui.main.categories

import android.content.Context
import android.content.Context.MODE_PRIVATE
import javax.inject.Inject

class AdultsStorage @Inject constructor(private val context: Context) {
    companion object {
        private const val ADULTS_KEY = "ADULTS_KEY"
        private const val PREF_KEY = "PREF_KEY"
    }

    private val preferences = context.getSharedPreferences(PREF_KEY, MODE_PRIVATE)
    var isAdults: Boolean
        get() = preferences.getBoolean(ADULTS_KEY, false)
        set(value) = preferences
            .edit()
            .putBoolean(ADULTS_KEY, value)
            .apply()
}