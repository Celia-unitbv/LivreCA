package com.example.livreca.data

import androidx.room.*

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(book: Book)

    @Query("SELECT * FROM books WHERE userId = :userId")
    suspend fun getBooksForUser(userId: Int): List<Book>

    @Delete
    suspend fun delete(book: Book)

    @Query("UPDATE books SET progress = :progress WHERE id = :bookId")
    suspend fun updateProgress(bookId: Int, progress: Int)
}

