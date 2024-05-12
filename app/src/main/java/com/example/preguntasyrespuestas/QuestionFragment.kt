package com.example.preguntasyrespuestas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

/**
 * Fragmento que muestra una pregunta y opciones de respuesta.
 * Permite al usuario seleccionar una respuesta y verifica si es correcta.
 */
class QuestionFragment : Fragment() {
    // Lista de preguntas con sus respectivas opciones y respuesta correcta.
    val questions = mutableListOf<QuestionData>(
        QuestionData("¿Cuál es la capital de Francia?", listOf("Madrid", "París", "Londres", "Berlín"), "París"),
        QuestionData("¿Quién pintó la Mona Lisa?", listOf("Pablo Picasso", "Leonardo da Vinci", "Vincent van Gogh", "Salvador Dalí"), "Leonardo da Vinci"),
        QuestionData("¿Cuántos planetas hay en nuestro sistema solar?", listOf("8", "9", "10", "7"), "8")
    )
    var currentQuestionIndex = 0
    private lateinit var currentQuestion: QuestionData

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_question, container, false)
        currentQuestion = questions[currentQuestionIndex]
        view.findViewById<TextView>(R.id.questionText).text = currentQuestion.question

        val optionsLayout = view.findViewById<RadioGroup>(R.id.optionsLayout)
        currentQuestion.options.forEach { option ->
            val radioButton = RadioButton(requireContext())
            radioButton.text = option
            optionsLayout.addView(radioButton)
        }

        view.findViewById<Button>(R.id.submitButton).setOnClickListener {
            checkAnswer(optionsLayout) // Verifica la respuesta seleccionada por el usuario.
        }
        return view
    }

    /**
     * Método para verificar la respuesta seleccionada por el usuario.
     * Muestra un mensaje de respuesta correcta o incorrecta.
     * @param optionsLayout Layout que contiene las opciones de respuesta.
     */
    private fun checkAnswer(optionsLayout: RadioGroup) {
        val selectedOptionId = optionsLayout.checkedRadioButtonId
        if (selectedOptionId != -1) {
            val selectedRadioButton = optionsLayout.findViewById<RadioButton>(selectedOptionId)
            val selectedAnswer = selectedRadioButton.text.toString()
            val isCorrect = selectedAnswer == currentQuestion.correctAnswer
            val explanation = if (isCorrect) "¡Respuesta correcta!" else "Respuesta incorrecta. La respuesta correcta es: ${currentQuestion.correctAnswer}"
            showAnswerFragment(isCorrect, explanation) // Muestra el fragmento de respuesta.
            currentQuestion.userAnswer = selectedAnswer // Guarda la respuesta del usuario en el objeto de pregunta actual.
        } else {
            Toast.makeText(requireContext(), "Selecciona una respuesta", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Método para mostrar el fragmento de respuesta.
     * @param isCorrect Indica si la respuesta seleccionada por el usuario es correcta.
     * @param explanation Texto explicativo sobre la respuesta.
     */
    private fun showAnswerFragment(isCorrect: Boolean, explanation: String) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, AnswerFragment.newInstance(isCorrect, explanation))
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
