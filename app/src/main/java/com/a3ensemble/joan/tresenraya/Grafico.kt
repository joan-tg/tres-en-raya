package com.a3ensemble.joan.tresenraya

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.view.View

class Grafico(var posicionX: Int? = 0,
              var posicionY: Int? = 0,
              val view: View? = View(null),
              val drawable: Drawable? = null) {

    var ancho:Int? = 0
    val cenX:Int = (view?.width)?.div(2) ?: 0
    val cenY:Int = (view?.height)?.div(2) ?: 0

    fun dibujaGrafico(canvas: Canvas?) {
        if (canvas != null) {
            //TODO cambiar funcionamiento para mostrar las fichas correctamente
            drawable?.setBounds(posicionX!!, posicionY!!, view?.width!!, view.width)
            drawable?.draw(canvas)
        }
    }

    fun dibujaTablero(canvas: Canvas?) {
        if (canvas != null) {
            drawable?.setBounds(posicionX!!, posicionY!!, posicionX!!+ancho!!, posicionY!!+ancho!!)
            drawable?.draw(canvas)
        }
    }

}