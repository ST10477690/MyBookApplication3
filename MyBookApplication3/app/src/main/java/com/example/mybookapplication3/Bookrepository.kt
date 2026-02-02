package com.example.mybookapplication3

/**
 * Singleton object to store the book collection.
 * Shared across activities for data consistency.
 */
object BookRepository {
    // Array to store all books in the application
    private val bookList = mutableListOf(
        Book(
            "1984",
            "George Orwell",
            mutableListOf(4, 5, 4),
            mutableListOf("Great book", "Thought-Provoking")
        ),
        Book(
            "To Kill A Mockingbird",
            "Harper Lee",
            mutableListOf(4, 5, 5),
            mutableListOf("Emotional", "Thought-Provoking")
        ),
        Book(
            "The Great Gatsby",
            "F. Scott Fitzgerald",
            mutableListOf(4, 4, 5),
            mutableListOf("Classic", "Thought-Provoking")
        )
    )

    /**
     * Returns the array of all books.
     */
    fun getBooks(): Array<Book> = bookList.toTypedArray()

    /**
     * Adds a new book to the collection.
     * @param book The book to add
     */
    fun addBook(book: Book) {
        bookList.add(book)
    }
}