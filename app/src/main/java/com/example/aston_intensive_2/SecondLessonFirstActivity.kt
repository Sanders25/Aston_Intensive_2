package com.example.aston_intensive_2

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import com.example.aston_intensive_2.databinding.ActivitySecondLessonFirstBinding


const val EXTRA_MESSAGE = "com.example.android.aston_intensive_2.MESSAGE"
const val TEXT_TO_SHOW = "com.example.android.aston_intensive_2.TEXT_TO_SHOW"
const val COUNTER = "com.example.android.aston_intensive_2.COUNTER"


class SecondLessonFirstActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondLessonFirstBinding
    private var uiState: UiState = UiState.Stage_2_1
    private var counter = 0
    private var counterSaveState = 0
    private val shoppingListItems = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondLessonFirstBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        if (savedInstanceState != null) {
            binding.textMessageReply.text = savedInstanceState.getString("reply_text")
            uiState = UiState.valueOf(savedInstanceState.getString("ui_state").toString())
            counterSaveState = savedInstanceState.getInt("counter_3")
            binding.textCounter3.text = counterSaveState.toString()
            savedInstanceState.getStringArrayList("shoppingList")?.forEach {
                loadShoppingList(binding.linearLayout5, it)
            }
        }


        binding.buttonMain.setOnClickListener {
            launchSecondActivity(it)
        }
        binding.buttonTextOne.setOnClickListener {
            launchExtraActivity(R.string.text_1)
        }
        binding.buttonTextTwo.setOnClickListener {
            launchExtraActivity(R.string.text_2)
        }
        binding.buttonTextThree.setOnClickListener {
            launchExtraActivity(R.string.text_3)
        }
        binding.buttonSayHello.setOnClickListener {
            sayHello()
        }
        binding.buttonCount2.setOnClickListener {
            count()
        }
        binding.buttonCount3.setOnClickListener {
            counterWithSavingState()
        }
        binding.openWebsiteButton.setOnClickListener {
            openWebsite()
        }
        binding.openLocationButton.setOnClickListener {
            val loc = binding.locationEdittext.text.toString()
            if (loc.isNotBlank())
                openLocation(loc)
        }
        binding.buttonFind.setOnClickListener {
            val loc = binding.editTextFindShop.text.toString()
            if (loc.isNotBlank())
                openLocation(loc)
        }
        binding.shareTextButton.setOnClickListener {
            shareText()
        }
        binding.buttonTakePicture.setOnClickListener {
            takePicture()
        }
        binding.buttonAdd.setOnClickListener {
            addItem()
        }

        binding.stage21.visibility = View.GONE
        binding.stage2Cc.visibility = View.GONE
        binding.stage22.visibility = View.GONE
        binding.stage23.visibility = View.GONE
        binding.stage24.visibility = View.GONE
        binding.shoppingList.visibility = View.GONE

        binding.buttonNextStage2.setOnClickListener {
            nextStage(it)
        }

        updateUi()
    }

    private fun addItem() {
        val intent = Intent(this, ShoppingListActivity::class.java)
        getResult.launch(intent)
    }

    private fun takePicture() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent);
        } else {
            println("Cant handle camera")
        }
    }

    private fun openLocation(location: String) {
        val addressUri = Uri.parse("geo:0,0?q=$location")
        val intent = Intent(Intent.ACTION_VIEW, addressUri)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent);
        } else {
            println("Cant handle location")
        }
    }

    private fun shareText() {
        val txt = binding.shareEdittext.text.toString()
        val mimeType = "text/plain"

        ShareCompat.IntentBuilder
            .from(this)
            .setType(mimeType)
            .setChooserTitle(getString(R.string.share))
            .setText(txt)
            .startChooser();
    }

    private fun openWebsite() {
        val url = binding.websiteEdittext.text.toString()
        val webpage = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else
            println("Cant handle this")
    }

    private fun counterWithSavingState() {
        binding.textCounter3.text = counterSaveState++.toString()
    }

    private fun sayHello() {
        val intent = Intent(this, SecondLessonThird::class.java)
        var cnt = binding.showCount2.text.toString()

        intent.putExtra(COUNTER, cnt)
        startActivity(intent)
    }

    private fun count() {
        binding.showCount2.text = counter++.toString()
    }

    enum class UiState {
        Stage_2_1,
        Stage_2_CC,
        Stage_2_2,
        Stage_2_3,
        Stage_shopping_list,
        Stage_2_4,
    }

    private fun updateUi() {
        when (uiState) {
            UiState.Stage_2_1 -> {
                binding.stage21.visibility = View.VISIBLE
            }
            UiState.Stage_2_CC -> {
                binding.stage21.visibility = View.GONE
                binding.stage2Cc.visibility = View.VISIBLE
                binding.textStageHeader2.text = "Coding challenge"
            }
            UiState.Stage_2_2 -> {
                binding.stage2Cc.visibility = View.GONE
                binding.stage22.visibility = View.VISIBLE
                binding.textStageHeader2.text = "Homework 2"
            }
            UiState.Stage_2_3 -> {
                binding.stage22.visibility = View.GONE
                binding.stage23.visibility = View.VISIBLE
                binding.textStageHeader2.text = "Homework 3"
            }
            UiState.Stage_shopping_list -> {
                binding.stage23.visibility = View.GONE
                binding.shoppingList.visibility = View.VISIBLE
                binding.textStageHeader2.text = "Coding challenge 2"
            }
            UiState.Stage_2_4 -> {
                binding.shoppingList.visibility = View.GONE
                binding.stage24.visibility = View.VISIBLE
                binding.textStageHeader2.text = "Homework 4"
                binding.buttonNextStage2.text = "К выбору урока"
            }
        }
    }

    private fun nextStage(view: View) {
        when (uiState) {
            UiState.Stage_2_1 -> uiState = UiState.Stage_2_CC
            UiState.Stage_2_CC -> uiState = UiState.Stage_2_2
            UiState.Stage_2_2 -> uiState = UiState.Stage_2_3
            UiState.Stage_2_3 -> uiState = UiState.Stage_shopping_list
            UiState.Stage_shopping_list -> uiState = UiState.Stage_2_4
            UiState.Stage_2_4 -> finish()
        }
        updateUi()
    }

    private fun launchExtraActivity(textToShow: Int) {
        val intent = Intent(this, SecondLessonExtra::class.java)

        intent.putExtra(TEXT_TO_SHOW, textToShow)

        startActivity(intent)
    }

    private fun launchSecondActivity(view: View) {
        val intent = Intent(this, SecondLessonSecondActivity::class.java)
        var message = binding.editTextMain.text.toString()

        intent.putExtra(EXTRA_MESSAGE, message)
        intent.putExtra("id", 0)
        getResult.launch(intent)
    }


    private val getResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == Activity.RESULT_OK) {
            when (it.data?.getIntExtra("id", 0)) {
                0 -> {
                    val reply = it.data?.getStringExtra(EXTRA_REPLY)
                    binding.textHeaderReply.visibility = View.VISIBLE
                    binding.textMessageReply.visibility = View.VISIBLE
                    binding.textMessageReply.text = reply
                }
                1 -> {
                    loadShoppingList(
                        binding.linearLayout5,
                        it.data?.getStringExtra("item").toString()
                    )
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("reply_text", binding.textMessageReply.text.toString())
        outState.putString("ui_state", uiState.name)
        outState.putInt("counter_3", counterSaveState)
        saveShoppingList(binding.linearLayout5)
        outState.putStringArrayList("shoppingList", shoppingListItems)
    }

    private fun loadShoppingList(parent: ViewGroup, item: String) {
        for (i in 0 until 10) {
            val child = parent.getChildAt(i) as TextView
            if (child.text.isBlank()) {
                child.text = item
                return
            }
        }
    }

    private fun saveShoppingList(parent: ViewGroup) {
        shoppingListItems.clear()
        for (i in 0 until 10) {
            val child = parent.getChildAt(i) as TextView
            if (child.text.isNotBlank()) {
                shoppingListItems.add(child.text.toString())
            }
        }
    }
}