package com.a3ensemble.joan.tresenraya

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.view.View

class Grafico(var posicionX: Int? = 0,
              var posicionY: Int? = 0,
              val drawable: Drawable? = null) {

    var ancho:Int? = 0

    fun dibujaGrafico(canvas: Canvas?) {
        if (canvas != null) {
            //el ajuste se realiza para añadir un padding ficticio
            val ajuste:Int? = ancho?.div(6)
            drawable?.setBounds(posicionX!!+ajuste!!, posicionY!!+ ajuste, posicionX!!+ancho!!- ajuste, posicionY!!+ancho!!- ajuste)
            drawable?.draw(canvas)
        }
    }

    //Para dibujar el tablero no se realiza el ajuste anterior, ya que queremos que ocupe el espacio entero
    fun dibujaTablero(canvas: Canvas?) {
        if (canvas != null) {
            drawable?.setBounds(posicionX!!, posicionY!!, posicionX!!+ancho!!, posicionY!!+ancho!!)
            drawable?.draw(canvas)
        }
    }

}