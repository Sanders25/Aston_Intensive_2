package com.example.aston_intensive_2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.aston_intensive_2.databinding.ActivitySecondLessonExtraBinding

class SecondLessonExtra : AppCompatActivity() {
    private lateinit var binding: ActivitySecondLessonExtraBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondLessonExtraBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        val textToShow = intent.getIntExtra(TEXT_TO_SHOW, 0)

        binding.textToShow.text = resources.getString(textToShow)

        binding.buttonBack.setOnClickListener {
            super.onBackPressed()
        }
    }
}