package ru.semwai.androidlab3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import ru.semwai.androidlab3.databinding.Activity3Binding

class Activity3 : AppCompatActivity() {
    private val binding by lazy { Activity3Binding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.toFirst.setOnClickListener {
            setResult(RESULT_OK)
            finish()
        }
        binding.toSecond.setOnClickListener {
            finish()
        }
        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_about, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.about -> {
                startActivity(Intent(this, Activity_about::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}