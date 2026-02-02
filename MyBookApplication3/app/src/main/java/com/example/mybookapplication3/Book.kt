package com.example.mybookapplication3

/**
 * Data class representing a book with title, author, ratings, and comments.
 * Supports multiple user ratings and comments per book.
 */
data class Book(
    val title: String,
    val author: String,
    val rating: MutableList<Int>,
    val comment: MutableList<String>
) {
    /**
     * Calculates the average rating for this book using a loop.
     * @return Average rating or 0.0 if no ratings exist
     */
    fun averageRating(): Double {
        return if (rating.isNotEmpty()) {
            var sum = 0
            for (r in rating) {
                sum += r
            }
            sum.toDouble() / rating.size
        } else {
            0.0
        }
    }
}