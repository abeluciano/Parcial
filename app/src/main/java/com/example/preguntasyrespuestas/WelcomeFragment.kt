package com.example.preguntasyrespuestas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

/**
 * Fragmento de bienvenida que se muestra al iniciar la aplicación.
 * Contiene un botón para comenzar el juego.
 */
class WelcomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el diseño del fragmento de bienvenida.
        val view = inflater.inflate(R.layout.fragment_welcome, container, false)

        // Configurar el clic del botón de inicio.
        view.findViewById<Button>(R.id.startButton).setOnClickListener {
            startGame() // Llama al método para comenzar el juego.
        }

        return view
    }

    /**
     * Método privado para iniciar el juego al hacer clic en el botón de inicio.
     * Reemplaza el fragmento actual con el fragmento de preguntas.
     */
    private fun startGame() {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, QuestionFragment()) // Reemplazar con el fragmento de preguntas.
        transaction.addToBackStack(null) // Agregar a la pila de retroceso para volver al fragmento de bienvenida.
        transaction.commit() // Confirmar la transacción.
    }
}
