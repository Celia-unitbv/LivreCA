package com.example.livreca.managers

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate

object PreferencesManager {
    private const val FILE_NAME = "app_preferences"
    private const val PREF_DARK_MODE = "pref_dark_mode"

    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
    }

    fun saveThemePreference(context: Context, isDarkMode: Boolean) {
        val sharedPreferences = getSharedPreferences(context)
        val editor = sharedPreferences.edit()
        editor.putBoolean(PREF_DARK_MODE, isDarkMode)
        editor.apply()
    }

    fun applyTheme(context: Context) {
        val sharedPreferences = getSharedPreferences(context)
        val isDarkMode = sharedPreferences.getBoolean(PREF_DARK_MODE, false)
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    fun isDarkModeEnabled(context: Context): Boolean {
        return getSharedPreferences(context).getBoolean(PREF_DARK_MODE, false)
    }
}
