package com.example.preguntasyrespuestas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

/**
 * Fragmento que muestra la respuesta a una pregunta y permite al usuario avanzar a la siguiente pregunta.
 */
class AnswerFragment : Fragment() {
    companion object {
        private const val ARG_IS_CORRECT = "is_correct"
        private const val ARG_EXPLANATION = "explanation"

        /**
         * Método estático para crear una instancia de AnswerFragment con argumentos.
         * @param isCorrect Indica si la respuesta anterior fue correcta.
         * @param explanation Explicación de la respuesta.
         * @return Instancia de AnswerFragment.
         */
        fun newInstance(isCorrect: Boolean, explanation: String): AnswerFragment {
            val args = Bundle()
            args.putBoolean(ARG_IS_CORRECT, isCorrect)
            args.putString(ARG_EXPLANATION, explanation)
            val fragment = AnswerFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_answer, container, false)
        val isCorrect = arguments?.getBoolean(ARG_IS_CORRECT) ?: false
        val explanation = arguments?.getString(ARG_EXPLANATION) ?: ""

        view.findViewById<TextView>(R.id.answerText).text = if (isCorrect) "Correcto" else "Incorrecto"
        view.findViewById<TextView>(R.id.explanationText).text = explanation

        view.findViewById<Button>(R.id.nextButton).setOnClickListener {
            loadNextQuestion() // Carga la siguiente pregunta al hacer clic en el botón.
        }
        return view
    }

    /**
     * Método para cargar la siguiente pregunta al finalizar la respuesta actual.
     */
    private fun loadNextQuestion() {
        val parentQuestionFragment = parentFragment as? QuestionFragment
        parentQuestionFragment?.let {
            val nextQuestionIndex = it.currentQuestionIndex + 1
            if (nextQuestionIndex < it.questions.size) {
                it.currentQuestionIndex = nextQuestionIndex
                val transaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragment_container, QuestionFragment())
                transaction.addToBackStack(null)
                transaction.commit()
            } else {
                val totalQuestions = it.questions.size
                val correctAnswers = it.questions.count { question ->
                    question.userAnswer == question.correctAnswer
                }
                val incorrectAnswers = totalQuestions - correctAnswers
                val scoreMessage = "Has respondido $correctAnswers de $totalQuestions preguntas correctamente."
                // Aquí podrías mostrar un mensaje con la puntuación final.
            }
        }
    }
}
