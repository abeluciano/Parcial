package com.example.preguntasyrespuestas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class WelcomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_welcome, container, false)
        view.findViewById<Button>(R.id.startButton).setOnClickListener {
            startGame()
        }
        return view
    }

    private fun startGame() {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, QuestionFragment())
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
