package com.example.livreca

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {

    companion object {
        const val UI_MODE = "theme"
        const val UI_MODE_DARK = 1
        const val UI_MODE_LIGHT = 0

        lateinit var sharedPreferences: SharedPreferences

        fun getUiModeValue(context: Context): Int {
            sharedPreferences = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
            return sharedPreferences.getInt(UI_MODE, UI_MODE_LIGHT)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)

        // Set the theme based on user preference
        AppCompatDelegate.setDefaultNightMode(if (getUiModeValue(this) == UI_MODE_DARK) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO)


        setContentView(R.layout.activity_main)

        val switchButtonView = findViewById<SwitchCompat>(R.id.btnSwitch)

        // Initialize switch state based on current theme
        switchButtonView.isChecked = getUiModeValue(this) == UI_MODE_DARK

        // Switching theme manually using switch button
        switchButtonView.setOnCheckedChangeListener { _, isChecked ->
            val newMode = if (isChecked) UI_MODE_DARK else UI_MODE_LIGHT
            sharedPreferences.edit().putInt(UI_MODE, newMode).apply()
            applyTheme(newMode)
        }

        // Setăm Toolbar-ul ca ActionBar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Setăm NavController
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        setupActionBarWithNavController(navController)
    }

    private fun applyTheme(mode: Int) {
        val currentMode = AppCompatDelegate.getDefaultNightMode()
        val newMode = if (mode == UI_MODE_DARK) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO

        if (currentMode != newMode) {
            AppCompatDelegate.setDefaultNightMode(newMode)
            recreate()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
