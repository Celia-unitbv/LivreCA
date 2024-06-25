package com.example.livreca

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.livreca.data.AppDatabase
import com.example.livreca.data.Book
import com.example.livreca.databinding.FragmentAddBookBinding
import kotlinx.coroutines.launch

class AddBookFragment : Fragment() {

    private var _binding: FragmentAddBookBinding? = null
    private val binding get() = _binding!!
    private val args: AddBookFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBookBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSaveBook.setOnClickListener {
            val bookName = binding.etBookName.text.toString()
            val bookAuthor = binding.etBookAuthor.text.toString()
            val bookGenre = binding.etBookGenre.text.toString()
            val bookProgress = binding.etBookProgress.text.toString().toIntOrNull() ?: 0

            if (bookName.isNotEmpty() && bookAuthor.isNotEmpty() && bookGenre.isNotEmpty()) {
                val newBook = Book(
                    userId = args.userId,
                    name = bookName,
                    author = bookAuthor,
                    genre = bookGenre,
                    progress = bookProgress
                )
                lifecycleScope.launch {
                    context?.let {
                        AppDatabase.getDatabase(it).bookDao().insert(newBook)
                        Toast.makeText(it, "Book added successfully", Toast.LENGTH_SHORT).show()
                        findNavController().navigateUp()
                    }
                }
            } else {
                Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
