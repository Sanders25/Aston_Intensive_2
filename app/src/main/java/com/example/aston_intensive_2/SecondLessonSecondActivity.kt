package com.example.aston_intensive_2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.aston_intensive_2.databinding.ActivitySecondLessonSecondBinding

const val EXTRA_REPLY = "com.example.android.aston_intensive_2.extra.REPLY"

class SecondLessonSecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondLessonSecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondLessonSecondBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        val message = intent.getStringExtra(EXTRA_MESSAGE)

        binding.textMessage.text = message

        binding.buttonSecond.setOnClickListener {
            returnReply()
        }
    }

    private fun returnReply() {
        val reply = binding.editTextSecond.text.toString()
        val replyIntent = intent.putExtra(EXTRA_REPLY, reply)

        setResult(RESULT_OK, replyIntent)

        finish()
    }
}