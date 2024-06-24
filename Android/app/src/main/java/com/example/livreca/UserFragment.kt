package com.example.livreca

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.livreca.data.AppDatabase
import com.example.livreca.data.User
import com.example.livreca.databinding.FragmentUserBinding
import com.example.livreca.viewModels.UserViewModel
import com.example.livreca.viewModels.UserViewModelFactory


class UserFragment : Fragment() {

    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!
    // Utilizarea viewModels delegate pentru a crea ViewModel
    private val viewModel: UserViewModel by viewModels {
        UserViewModelFactory(AppDatabase.getDatabase(requireContext()).userDao())
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Observarea LiveData pentru utilizatorul curent
        viewModel.currentUser.observe(viewLifecycleOwner, Observer { user ->
            if (user != null) {
                binding.tvUsername.text = "${user.username}"
                setupClickListeners(user)
            } else {
                // Redirecționare către fragmentul de autentificare dacă nu există utilizator autentificat
                findNavController().navigate(UserFragmentDirections.actionUserFragmentToLoginFragment())
            }
        })

        // Initiate ViewModel to fetch current user
        viewModel.fetchCurrentUser()
    }

    private fun setupClickListeners(user: User) {
        binding.btnUserLibrary.setOnClickListener {
            val action = UserFragmentDirections.actionUserFragmentToBookListFragment(user.id)
            findNavController().navigate(action)
        }

        // Add more click listeners as needed
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
