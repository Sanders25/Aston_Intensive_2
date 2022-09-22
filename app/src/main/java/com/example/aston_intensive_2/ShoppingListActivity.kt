package com.example.aston_intensive_2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.aston_intensive_2.databinding.ActivityShoppingListBinding

class ShoppingListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShoppingListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingListBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        binding.button.setOnClickListener {
            addToShoppingList("Cheese")
        }
        binding.button2.setOnClickListener {
            addToShoppingList("Rice")
        }
        binding.button3.setOnClickListener {
            addToShoppingList("Apples")
        }
        binding.button4.setOnClickListener {
            addToShoppingList("Onions")
        }

    }

    private fun addToShoppingList(data: String) {
        val shoppingListItemIntent = intent.putExtra("item", data)

        shoppingListItemIntent.putExtra("id", 1)
        setResult(RESULT_OK, shoppingListItemIntent)
        finish()
    }
}