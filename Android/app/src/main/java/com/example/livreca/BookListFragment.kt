package com.example.livreca

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.livreca.data.AppDatabase
import com.example.livreca.databinding.FragmentBookListBinding
import kotlinx.coroutines.launch

class BookListFragment : Fragment() {

    private var _binding: FragmentBookListBinding? = null
    private val binding get() = _binding!!
    private lateinit var bookAdapter: BookAdapter
    private var userId: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userId = arguments?.getInt("userId") ?: -1

        bookAdapter = BookAdapter()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = bookAdapter
        }

        loadBooks()

        binding.btnAddBook.setOnClickListener {
            val action = BookListFragmentDirections.actionBookListFragmentToAddBookFragment(userId)
            findNavController().navigate(action)
        }
    }

    private fun loadBooks() {
        lifecycleScope.launch {
            context?.let {
                val books = AppDatabase.getDatabase(it).bookDao().getBooksForUser(userId)
                bookAdapter.submitList(books)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
