package com.example.mybookapplication3

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DisplayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display2)

        val txtOutput = findViewById<TextView>(R.id.txtOutput)
        val btnBack = findViewById<Button>(R.id.btnBack)

        val mode = intent.getStringExtra("mode")
        val books = BookRepository.getBooks()

        when (mode) {
            "list" -> {
                // Display list of books with details using a loop
                val builder = StringBuilder()
                for (i in books.indices) {
                    val book = books[i]
                    builder.append("Book: ${book.title}\n")
                    builder.append("Author: ${book.author}\n")
                    builder.append("Rating: ${"%.1f".format(book.averageRating())}/5\n")
                    builder.append("Comments: ")
                    for (j in book.comment.indices) {
                        builder.append(book.comment[j])
                        if (j < book.comment.size - 1) builder.append(", ")
                    }
                    builder.append("\n---------------------------------\n\n")
                }
                txtOutput.text = if (builder.isEmpty()) "No books in list." else builder.toString()
            }
            "average" -> {
                // Calculate and display average rating across all books using a loop
                var totalRating = 0.0
                var count = 0
                for (book in books) {
                    val avg = book.averageRating()
                    totalRating += avg
                    count++
                }
                val overallAverage = if (count > 0) totalRating / count else 0.0
                txtOutput.text = "Average Book Rating:\n\n${"%.2f".format(overallAverage)} / 5.0"
            }
            else -> {
                txtOutput.text = "Invalid mode."
            }
        }

        // Button to return to main screen
        btnBack.setOnClickListener {
            finish()
        }
    }
}
