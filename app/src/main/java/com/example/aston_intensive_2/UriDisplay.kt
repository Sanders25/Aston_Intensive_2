package com.example.aston_intensive_2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.aston_intensive_2.databinding.ActivityUriDisplayBinding

class UriDisplay : AppCompatActivity() {

    private lateinit var binding: ActivityUriDisplayBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUriDisplayBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        val uri = intent.data
        if (uri != null) {
            val uri_string = "URI: $uri"
            binding.textUriMessage.text = uri_string
        }

        binding.buttonBack3.setOnClickListener {
            onBackPressed()
        }
    }
}