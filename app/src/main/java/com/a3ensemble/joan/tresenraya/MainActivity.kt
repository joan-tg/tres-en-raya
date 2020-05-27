package com.a3ensemble.joan.tresenraya

import android.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        boton_salir.setOnClickListener { finish() }

        val tablero = Tablero()
        tablero.ponerFicha(0, 0, 1)
        println(tablero.toString())
        tablero.ponerFicha(1, 1, 2)
        println(tablero.toString())
        tablero.ponerFicha(0, 1, 1)
        println(tablero.toString())
    }
}
