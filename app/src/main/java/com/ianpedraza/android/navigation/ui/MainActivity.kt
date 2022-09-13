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
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.drawerlayout.widget.DrawerLayout.LOCK_MODE_LOCKED_CLOSED
import androidx.drawerlayout.widget.DrawerLayout.LOCK_MODE_UNLOCKED
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.ianpedraza.android.navigation.R
import com.ianpedraza.android.navigation.databinding.ActivityMainBinding
import com.ianpedraza.android.navigation.utils.viewBinding

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    private val drawerLayout: DrawerLayout by lazy {
        binding.drawerLayout
    }

    private val onDestinationChangedListener: NavController.OnDestinationChangedListener by lazy {
        NavController.OnDestinationChangedListener { controller, destination, _ ->
            val lockMode = if (destination.id == controller.graph.startDestinationId) {
                LOCK_MODE_UNLOCKED
            } else {
                LOCK_MODE_LOCKED_CLOSED
            }

            drawerLayout.setDrawerLockMode(lockMode)
        }
    }

    private val navController by lazy {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navHostFragment.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupUI()
    }

    private fun setupUI() {
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        navController.addOnDestinationChangedListener(onDestinationChangedListener)
        NavigationUI.setupWithNavController(binding.navView, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        // navController.navigateUp()
        return NavigationUI.navigateUp(navController, drawerLayout)
    }
}
