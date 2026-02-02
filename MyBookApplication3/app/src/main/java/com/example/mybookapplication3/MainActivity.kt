package com.example.mybookapplication3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val btnListBooks = findViewById<Button>(R.id.btnListBooks)
        val btnAverageRating = findViewById<Button>(R.id.btnAverageRating)
        val edtTitle = findViewById<EditText>(R.id.edtTitle)
        val edtAuthor = findViewById<EditText>(R.id.edtAuthor)
        val edtRatings = findViewById<EditText>(R.id.edtRatings)
        val edtComments = findViewById<EditText>(R.id.edtComments)

        // Add to playlist button - adds new book from input fields
        btnAdd.setOnClickListener {
            val title = edtTitle.text.toString().trim()
            val author = edtAuthor.text.toString().trim()
            val ratingsStr = edtRatings.text.toString().trim()
            val commentStr = edtComments.text.toString().trim()

            if (title.isEmpty() || author.isEmpty()) {
                Toast.makeText(this, "Please enter title and author", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Parse ratings - comma-separated or single value
            val ratings = mutableListOf<Int>()
            if (ratingsStr.isNotEmpty()) {
                try {
                    for (part in ratingsStr.split(",")) {
                        val value = part.trim().toInt().coerceIn(1, 5)
                        ratings.add(value)
                    }
                    if (ratings.isEmpty()) ratings.add(5)
                } catch (e: NumberFormatException) {
                    ratings.add(5)
                }
            } else {
                ratings.add(5)
            }

            val comments = if (commentStr.isNotEmpty()) {
                commentStr.split(",").map { it.trim() }.filter { it.isNotEmpty() }.toMutableList()
            } else {
                mutableListOf("No comment")
            }
            if (comments.isEmpty()) comments.add("No comment")

            BookRepository.addBook(Book(title, author, ratings, comments))
            Toast.makeText(this, "Book added to list!", Toast.LENGTH_SHORT).show()
            edtTitle.text.clear()
            edtAuthor.text.clear()
            edtRatings.text.clear()
            edtComments.text.clear()
        }

        // Button to display book list using a loop
        btnListBooks.setOnClickListener {
            val intent = Intent(this, DisplayActivity::class.java)
            intent.putExtra("mode", "list")
            startActivity(intent)
        }

        // Button to calculate and display average rating
        btnAverageRating.setOnClickListener {
            val intent = Intent(this, DisplayActivity::class.java)
            intent.putExtra("mode", "average")
            startActivity(intent)
        }
    }
}