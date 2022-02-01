package ru.semwai.androidlab3

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.semwai.androidlab3.databinding.FragmentFr3Binding


class fr3 : Fragment() {

    private val navigationController by lazy{ findNavController() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = FragmentFr3Binding.inflate(layoutInflater)

        binding.bnToFirst.setOnClickListener {
            navigationController.navigate(R.id.action_fr3_to_fr1)
        }
        binding.bnToSecond.setOnClickListener {
            navigationController.navigate(R.id.action_fr3_to_fr2)
        }
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_about, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.about -> {
                navigationController.navigate(R.id.global_about)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}