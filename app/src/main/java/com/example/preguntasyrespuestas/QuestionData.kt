package com.example.preguntasyrespuestas

data class QuestionData(
    val question: String,
    val options: List<String>,
    val correctAnswer: String,
    var userAnswer: String? = null
)
