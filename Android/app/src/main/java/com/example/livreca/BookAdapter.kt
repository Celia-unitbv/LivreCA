package com.example.livreca

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.livreca.data.Book
import com.example.livreca.databinding.ItemBookBinding

class BookAdapter : ListAdapter<Book, BookAdapter.BookViewHolder>(BookDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = getItem(position)
        holder.bind(book)
    }

    class BookViewHolder(private val binding: ItemBookBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(book: Book) {
            binding.bookName.text = book.name
            binding.bookAuthor.text = book.author
            binding.bookGenre.text = book.genre
            binding.progressBar.progress = book.progress
            binding.progressText.text = "Progres: ${book.progress}%"
            binding.bookNumberOfPages.text = "Pagini: ${book.numberOfPages}"


            itemView.setOnClickListener {
                val action = BookListFragmentDirections.actionBookListFragmentToBookDetailFragment(
                    book.name,
                    book.id,
                    book.numberOfPages
                )
                it.findNavController().navigate(action)
            }


        }
    }
}


class BookDiffCallback : DiffUtil.ItemCallback<Book>() {
    override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem == newItem
    }
}
