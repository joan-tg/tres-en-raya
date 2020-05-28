package com.a3ensemble.joan.tresenraya

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import java.util.ArrayList

class VistaJuego(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    private val posicionesX = Array(3) {IntArray(3) {0} }
    private val posicionesY = Array(3) {IntArray(3) {0} }
    private val fichasX = Array(3) {Array(3) {Any()} }
    private val fichasO = Array(3) {Array(3) {Any()} }
    private val tablero = Grafico(1, 1, this, AppCompatResources.getDrawable(context, R.drawable.tablero))

    init {
        for (x in posicionesX.indices)
            for (y in posicionesX[x].indices) {
                fichasX[x][y] =
                    Grafico(x, y, this, AppCompatResources.getDrawable(context, R.drawable.equis))
                fichasO[x][y] =
                    Grafico(x, y, this, AppCompatResources.getDrawable(context, R.drawable.circulo))
            }
    }


    override fun onSizeChanged(ancho:Int, alto:Int, ancho_anter:Int, alto_anter:Int) {
        super.onSizeChanged(ancho, alto, ancho_anter, alto_anter)

        tablero.posicionX = if(ancho<alto) 0 else (ancho-alto)/2
        tablero.posicionY = if(ancho<alto) (alto-ancho)/2 else 0
        tablero.ancho = if(ancho<alto) ancho else alto

        val tamanyo = if(ancho<alto) ancho else alto
        for(x in posicionesX.indices)
            for(y in posicionesX[x].indices) {
                //TODO cambiar las posiciones X e Y en función de cada casilla
                posicionesX[x][y] = width
                posicionesY[x][y] = height
            }

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        //TODO for for para dibujar las fichas y poner alpha a 0
        tablero.dibujaTablero(canvas)
    }
}