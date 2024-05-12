package com.example.preguntasyrespuestas

/**
 * Clase de datos que representa una pregunta en la aplicación.
 * @property question Pregunta en sí misma.
 * @property options Lista de opciones de respuesta para la pregunta.
 * @property correctAnswer Respuesta correcta para la pregunta.
 * @property userAnswer Respuesta dada por el usuario (puede ser nula si aún no se ha respondido).
 */
data class QuestionData(
    val question: String, // La pregunta en sí misma.
    val options: List<String>, // Lista de opciones de respuesta para la pregunta.
    val correctAnswer: String, // Respuesta correcta para la pregunta.
    var userAnswer: String? = null // Respuesta dada por el usuario (puede ser nula si aún no se ha respondido).
)
