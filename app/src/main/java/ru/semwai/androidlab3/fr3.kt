package ru.semwai.androidlab3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import ru.semwai.androidlab3.databinding.FragmentFr3Binding


class fr3 : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = FragmentFr3Binding.inflate(layoutInflater)
        val navigationController = findNavController()
        binding.toFirst.setOnClickListener {
            navigationController.navigate(R.id.action_fr3_to_fr1)
        }
        binding.toSecond.setOnClickListener {
            navigationController.navigate(R.id.action_fr3_to_fr2)
        }
        return binding.root
    }

}