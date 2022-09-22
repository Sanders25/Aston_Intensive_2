package com.example.aston_intensive_2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.aston_intensive_2.databinding.ActivitySecondLessonThirdBinding

class SecondLessonThird : AppCompatActivity() {

    private lateinit var binding: ActivitySecondLessonThirdBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondLessonThirdBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        val count = intent.getStringExtra(COUNTER)

        binding.textCounter.text = count

        binding.buttonBack2.setOnClickListener {
            super.onBackPressed()
        }
    }
}