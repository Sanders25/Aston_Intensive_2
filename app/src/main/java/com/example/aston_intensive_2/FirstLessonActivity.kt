package com.example.aston_intensive_2

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.aston_intensive_2.databinding.ActivityFirstLessonBinding

private const val TAG = "FirstLesson"

class FirstLessonActivity : AppCompatActivity() {

    private var mCount = 0
    private lateinit var binding: ActivityFirstLessonBinding
    var uiState: UiState = UiState.Stage_1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFirstLessonBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.stage1.visibility = View.GONE
        binding.stage2.visibility = View.GONE
        binding.stage3.visibility = View.GONE

        binding.buttonNextStage.setOnClickListener {
            nextStage(it)
        }
        binding.buttonPrintLogs.setOnClickListener {
            inflateLogTextViews()
        }
        binding.buttonToast.setOnClickListener {
            showToast(it)
        }
        binding.buttonCount.setOnClickListener {
            countUp(it)
        }
        binding.buttonZero.setOnClickListener {
            resetCounter(it)
        }
        updateUi()
    }

    private fun resetCounter(view: View) {
        mCount = 0
        binding.showCount.text = "0"
        view.setBackgroundColor(Color.DKGRAY)
    }

    private fun updateUi() {
        when (uiState) {
            UiState.Stage_1 -> {
                binding.stage1.visibility = View.VISIBLE
            }
            UiState.Stage_2 -> {
                binding.stage1.visibility = View.GONE
                binding.stage2.visibility = View.VISIBLE
                binding.textStageHeader.text = "Homework 2"
            }
            UiState.Stage_3 -> {
                binding.stage2.visibility = View.GONE
                binding.stage3.visibility = View.VISIBLE
                binding.textStageHeader.text = "Homework 3"
                binding.buttonNextStage.text = "К выбору урока"
            }
        }
    }

    private fun nextStage(view: View) {
        when (uiState) {
            UiState.Stage_1 -> uiState = UiState.Stage_2
            UiState.Stage_2 -> uiState = UiState.Stage_3
            UiState.Stage_3 -> finish()
        }
        updateUi()
    }

    private fun showToast(view: View) {
        val toast = Toast.makeText(this, R.string.toast_message, Toast.LENGTH_SHORT)
        toast.show()
    }

    private fun countUp(view: View) {
        mCount++
        binding.showCount.text = "$mCount"
        if (mCount % 2 == 0)
            view.setBackgroundColor(Color.BLUE)
        else
            view.setBackgroundColor(Color.YELLOW)

        binding.buttonZero.setBackgroundColor(Color.RED)
    }

    private fun inflateLogTextViews() {
        Log.d(TAG, "Debugging")
        Log.i(TAG, "Info message")
        Log.w(TAG, "Warning")
        Log.e(TAG, "Error")

        binding.textOutD.text = "Debugging"
        binding.textOutI.text = "Info message"
        binding.textOutW.text = "Warning"
        binding.textOutE.text = "Error"
    }

    enum class UiState {
        Stage_1,
        Stage_2,
        Stage_3
    }
}

