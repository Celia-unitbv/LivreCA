package com.example.livreca

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.livreca.databinding.FragmentStartBinding
import com.example.livreca.managers.PreferencesManager

class StartFragment : Fragment() {

    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()
        val checkBox = binding.checkboxDarkMode;

        // Setăm listeneri pentru butoane
        view.findViewById<Button>(R.id.btn_already_have_account).setOnClickListener {
            navController.navigate(R.id.action_startFragment_to_loginFragment)
        }
        view.findViewById<Button>(R.id.btn_new_here).setOnClickListener {
            navController.navigate(R.id.action_startFragment_to_registerFragment)
        }

        // Initialize checkbox state based on saved preference
        //binding.checkboxDarkMode.isChecked = PreferencesManager.isDarkModeEnabled(requireContext())

        // Setup listener for checkbox
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            PreferencesManager.saveThemePreference(requireContext(), isChecked)
            applyTheme(isChecked)
        }

        // Aplicare temă inițială bazată pe preferința salvată
        applyTheme(PreferencesManager.isDarkModeEnabled(requireContext()))
    }

    private fun applyTheme(isDarkMode: Boolean) {
        // Aplicare temă în funcție de preferința salvată
        if (isDarkMode) {
            requireActivity().setTheme(R.style.DarkTheme)
        } else {
            requireActivity().setTheme(R.style.LightTheme)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
