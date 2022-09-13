package com.ianpedraza.android.navigation.utils

import com.ianpedraza.android.navigation.model.QuestionModel

object DummyQuestions {
    val data = mutableListOf(
        QuestionModel(
            text = "What is Android Jetpack?",
            answers = listOf("all of these", "tools", "documentation", "libraries")
        ),
        QuestionModel(
            text = "Base class for Layout?",
            answers = listOf("ViewGroup", "ViewSet", "ViewCollection", "ViewRoot")
        ),
        QuestionModel(
            text = "Layout for complex Screens?",
            answers = listOf("ConstraintLayout", "GridLayout", "LinearLayout", "FrameLayout")
        ),
        QuestionModel(
            text = "Pushing structured data into a Layout?",
            answers = listOf("Data Binding", "Data Pushing", "Set Text", "OnClick")
        ),
        QuestionModel(
            text = "Inflate layout in fragments?",
            answers = listOf(
                "onCreateView",
                "onViewCreated",
                "onCreateLayout",
                "onInflateLayout"
            )
        ),
        QuestionModel(
            text = "Build system for Android?",
            answers = listOf("Gradle", "Graddle", "Grodle", "Groyle")
        ),
        QuestionModel(
            text = "Android vector format?",
            answers = listOf(
                "VectorDrawable",
                "AndroidVectorDrawable",
                "DrawableVector",
                "AndroidVector"
            )
        ),
        QuestionModel(
            text = "Android Navigation Component?",
            answers = listOf("NavController", "NavCentral", "NavMaster", "NavSwitcher")
        ),
        QuestionModel(
            text = "Registers app with launcher?",
            answers = listOf(
                "intent-filter",
                "app-registry",
                "launcher-registry",
                "app-launcher"
            )
        ),
        QuestionModel(
            text = "Mark a layout for Data Binding?",
            answers = listOf("<layout>", "<binding>", "<data-binding>", "<dbinding>")
        )
    )
}