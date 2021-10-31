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
            startActivity(Intent(this, Activity1::class.java).setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT))
        }
        binding.toSecond.setOnClickListener {
            startActivity(Intent(this, Activity2::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
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