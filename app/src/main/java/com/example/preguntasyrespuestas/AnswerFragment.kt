package com.example.preguntasyrespuestas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment


class AnswerFragment : Fragment() {
    companion object {
        private const val ARG_IS_CORRECT = "is_correct"
        private const val ARG_EXPLANATION = "explanation"

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
            loadNextQuestion()
        }
        return view
    }

    private fun loadNextQuestion() {

        val nextQuestionIndex = (parentFragment as QuestionFragment).currentQuestionIndex  + 1
        if (nextQuestionIndex < (parentFragment as QuestionFragment).questions.size) {
            (parentFragment as QuestionFragment).currentQuestionIndex = nextQuestionIndex
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, QuestionFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        } else {
            val totalQuestions = (parentFragment as QuestionFragment).questions.size
            val correctAnswers = (parentFragment as QuestionFragment).questions.count { question ->
                question.userAnswer == question.correctAnswer
            }
            val incorrectAnswers = totalQuestions - correctAnswers
            val scoreMessage = "Has respondido $correctAnswers de $totalQuestions preguntas correctamente."
        }
    }
}
