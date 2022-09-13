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
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.ianpedraza.android.navigation.R
import com.ianpedraza.android.navigation.databinding.FragmentGameBinding
import com.ianpedraza.android.navigation.model.QuestionModel
import com.ianpedraza.android.navigation.utils.DummyQuestions
import com.ianpedraza.android.navigation.utils.navigate
import kotlin.math.min

class GameFragment : Fragment() {

    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!

    private val questionModels: MutableList<QuestionModel> by lazy {
        DummyQuestions.data
    }

    lateinit var currentQuestionModel: QuestionModel
    lateinit var answers: MutableList<String>
    private var questionIndex = 0
    private val numQuestions = min((questionModels.size + 1) / 2, 3)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_game, container, false
        )

        setupUI()

        return binding.root
    }

    private fun setupUI() {
        randomizeQuestions()

        binding.game = this

        binding.submitButton.setOnClickListener { submit(it) }
    }

    private fun submit(view: View) {
        val checkedId = binding.questionRadioGroup.checkedRadioButtonId
        // Do nothing if nothing is checked (id == -1)
        if (-1 != checkedId) {
            var answerIndex = 0
            when (checkedId) {
                R.id.secondAnswerRadioButton -> answerIndex = 1
                R.id.thirdAnswerRadioButton -> answerIndex = 2
                R.id.fourthAnswerRadioButton -> answerIndex = 3
            }
            // The first answer in the original question is always the correct one, so if our
            // answer matches, we have the correct answer.
            if (answers[answerIndex] == currentQuestionModel.answers[0]) {
                questionIndex++
                // Advance to the next question
                if (questionIndex < numQuestions) {
                    currentQuestionModel = questionModels[questionIndex]
                    setQuestion()
                    binding.invalidateAll()
                } else {
                    val direction = GameFragmentDirections.actionGameFragmentToGameWonFragment(
                        numQuestions,
                        questionIndex
                    )

                    view.navigate(direction)
                }
            } else {
                val direction = GameFragmentDirections.actionGameFragmentToGameOverFragment2()
                view.navigate(direction)
            }
        }
    }

    private fun randomizeQuestions() {
        questionModels.shuffle()
        questionIndex = 0
        setQuestion()
    }


    private fun setQuestion() {
        currentQuestionModel = questionModels[questionIndex]

        answers = currentQuestionModel.answers.toMutableList()

        answers.shuffle()

        (activity as AppCompatActivity).supportActionBar?.title =
            getString(R.string.title_android_trivia_question, questionIndex + 1, numQuestions)
    }
}
