package com.example.aston_intensive_2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.aston_intensive_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        binding.buttonFirstLesson.setOnClickListener {
            goToFirstLesson()
        }
        binding.buttonSecondLesson.setOnClickListener {
            goToSecondLesson()
        }

    }

    private fun goToFirstLesson() {
        val intent = Intent(this, FirstLessonActivity::class.java)

        startActivity(intent)
    }

    private fun goToSecondLesson() {
        val intent = Intent(this, SecondLessonFirstActivity::class.java)

        startActivity(intent)
    }
}
