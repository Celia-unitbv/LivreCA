package com.example.livreca

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.livreca.data.AppDatabase
import com.example.livreca.databinding.FragmentLoginBinding
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()
            if (username.isNotEmpty() && password.isNotEmpty()) {
                lifecycleScope.launch {
                    context?.let {
                        val user = AppDatabase.getDatabase(it).userDao().getUser(username, password)
                        if (user != null) {
                            Toast.makeText(it, "Conectare reușită!", Toast.LENGTH_SHORT).show()

                            val action = LoginFragmentDirections.actionLoginFragmentToBookListFragment(user.id)
                            findNavController().navigate(action)
                        } else {
                            Toast.makeText(it, "Parolă sau username greșite!", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            } else {
                Toast.makeText(context, "Completează toate câmpurile!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
