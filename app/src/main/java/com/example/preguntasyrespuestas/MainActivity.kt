/**
 * Clase principal de la aplicación.
 * Esta clase se encarga de gestionar la actividad principal y su contenido.
 * Autor: Abel Luciano Aragon Alvaro
 * Correo electrónico: aaragona@ulasalle.edu.pe
 */
package com.example.preguntasyrespuestas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    /**
     * Método llamado al crear la actividad por primera vez.
     * Configura el diseño de la actividad y reemplaza el fragmento inicial.
     * @param savedInstanceState Estado de la actividad en caso de recreación.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Reemplaza el contenedor de fragmentos con el fragmento de bienvenida.
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, WelcomeFragment())
            .commit()
    }
}
