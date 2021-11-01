package ru.semwai.androidlab3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import ru.semwai.androidlab3.databinding.FragmentFr2Binding


class fr2 : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = FragmentFr2Binding.inflate(layoutInflater)
        val navigationController = findNavController()
        binding.toFirst.setOnClickListener {
            navigationController.navigate(R.id.action_fr2_to_fr1)
        }
        binding.toThird.setOnClickListener {
            navigationController.navigate(R.id.action_fr2_to_fr3)
        }
        return binding.root
    }

}