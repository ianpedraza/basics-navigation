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

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.ianpedraza.android.navigation.R
import com.ianpedraza.android.navigation.databinding.FragmentGameOverBinding

class GameOverFragment : Fragment() {

    private var _binding: FragmentGameOverBinding? = null
    private val binding get() = _binding!!

    private val navigateToGame by lazy {
        val direction = GameOverFragmentDirections.actionGameOverFragment2ToGameFragment()
        Navigation.createNavigateOnClickListener(direction)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_game_over, container, false
        )

        setupUI()

        return binding.root
    }

    private fun setupUI() {
        binding.tryAgainButton.setOnClickListener(navigateToGame)
    }
}
