/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ianpedraza.android.navigation.ui

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import com.ianpedraza.android.navigation.R
import com.ianpedraza.android.navigation.databinding.FragmentGameWonBinding

class GameWonFragment : Fragment(), MenuProvider {

    private var _binding: FragmentGameWonBinding? = null
    private val binding get() = _binding!!

    private val navigateToGame by lazy {
        val direction = GameWonFragmentDirections.actionGameWonFragmentToGameFragment()
        Navigation.createNavigateOnClickListener(direction)
    }

    private val args: GameWonFragmentArgs? by lazy {
        arguments?.run {
            GameWonFragmentArgs.fromBundle(this)
        }
    }

    private val shareIntent: Intent? by lazy {
        args?.run {
            /*Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(
                    Intent.EXTRA_TEXT,
                    getString(R.string.share_success_text, numCorrect, numQuestions)
                )
            }*/
            ShareCompat.IntentBuilder(requireContext())
                .setText(getString(R.string.share_success_text, numCorrect, numQuestions))
                .setType("text/plain")
                .intent
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_game_won, container, false
        )

        setupUI()

        return binding.root
    }

    private fun setupUI() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        binding.nextMatchButton.setOnClickListener(navigateToGame)

        showResults()
    }

    private fun showResults() {
        args?.let { args ->
            Toast.makeText(
                context,
                "NumCorrect: ${args.numCorrect}, NumQuestion: ${args.numQuestions}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun shareSuccess() {
        shareIntent?.let { intent ->
            startActivity(intent)
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.winner_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.share -> shareSuccess()
        }

        return true
    }

}
