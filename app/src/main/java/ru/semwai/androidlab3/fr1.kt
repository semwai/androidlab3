package ru.semwai.androidlab3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import ru.semwai.androidlab3.databinding.FragmentFr1Binding


class fr1 : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = FragmentFr1Binding.inflate(layoutInflater)
        val navigationController = findNavController()
        binding.toSecond.setOnClickListener {
            navigationController.navigate(R.id.action_fr1_to_fr2)
        }
    }


}