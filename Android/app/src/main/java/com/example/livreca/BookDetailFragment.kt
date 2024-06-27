package com.example.livreca

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.livreca.data.AppDatabase
import kotlinx.coroutines.launch

class BookDetailFragment : Fragment() {

    private lateinit var bookTitleTextView: TextView
    private lateinit var currentPageEditText: EditText
    private lateinit var calculateButton: Button
    private lateinit var percentageReadTextView: TextView
    private var bookId: Int = 0
    private var totalPages: Int = 0

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_book_detail, container, false)

        bookTitleTextView = view.findViewById(R.id.book_title)
        currentPageEditText = view.findViewById(R.id.current_page)
        calculateButton = view.findViewById(R.id.calculate_button)
        percentageReadTextView = view.findViewById(R.id.percentage_read)


        arguments?.let { args ->
            val bookTitle = args.getString("BOOK_TITLE", "")
            bookId = args.getInt("BOOK_ID", 0)
            totalPages = args.getInt("TOTAL_PAGES", 0)

            bookTitleTextView.text = bookTitle

            calculateButton.setOnClickListener {
                val currentPageStr = currentPageEditText.text.toString()
                if (currentPageStr.isNotEmpty()) {
                    val currentPage = currentPageStr.toInt()
                    if (currentPage > 0 && currentPage <= totalPages) {
                        val percentage = (currentPage.toDouble() / totalPages * 100).toInt()


                        lifecycleScope.launch {
                            val db = AppDatabase.getInstance(requireContext())
                            db.bookDao().updateProgress(bookId, percentage)
                        }


                        percentageReadTextView.text = "Ai citit $percentage% din carte."
                        percentageReadTextView.visibility = View.VISIBLE
                    } else {
                        percentageReadTextView.text = "Te rog, adaugă un număr valid."
                        percentageReadTextView.visibility = View.VISIBLE
                    }
                }
            }
        }

        return view
    }
}
