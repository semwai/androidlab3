package ru.semwai.androidlab3

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import ru.semwai.androidlab3.databinding.FragmentFr1Binding


class fr1 : Fragment() {

    private val navigationController by lazy{ findNavController() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = FragmentFr1Binding.inflate(layoutInflater)

        binding.bnToSecond.setOnClickListener {
            navigationController.navigate(R.id.action_fr1_to_fr2)
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