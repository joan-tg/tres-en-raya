package com.a3ensemble.joan.tresenraya.presentacion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.a3ensemble.joan.tresenraya.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        boton_salir.setOnClickListener { finish() }
        boton_jugar.setOnClickListener { startActivity(Intent(this, JuegoActivity::class.java))}

    }
}
