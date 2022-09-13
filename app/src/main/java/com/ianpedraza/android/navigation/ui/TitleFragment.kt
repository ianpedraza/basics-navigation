package com.ianpedraza.android.navigation.ui

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.fragment.app.Fragment
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.ianpedraza.android.navigation.R
import com.ianpedraza.android.navigation.databinding.FragmentTitleBinding

class TitleFragment : Fragment(), MenuProvider {

    private var _binding: FragmentTitleBinding? = null
    private val binding get() = _binding!!

    private val navigateToGame by lazy {
        val direction = TitleFragmentDirections.actionTitleFragmentToGameFragment()
        Navigation.createNavigateOnClickListener(direction)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTitleBinding.inflate(inflater, container, false)
        setupUI()
        return binding.root
    }

    private fun setupUI() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        binding.playButton.setOnClickListener(navigateToGame)
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(menuItem, findNavController())
    }

}