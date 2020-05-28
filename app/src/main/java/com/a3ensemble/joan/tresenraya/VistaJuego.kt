package com.a3ensemble.joan.tresenraya

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import java.util.ArrayList

class VistaJuego(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    //TODO Mejorar: Solamente se dibujan las fichas cuando se ponen sobre el tablero
    private val posicionesX = Array(3) {IntArray(3) {0} }
    private val posicionesY = Array(3) {IntArray(3) {0} }
    private val fichasX = Array(3) {Array(3) {Grafico()} }
    private val fichasO = Array(3) {Array(3) {Grafico()} }
    private val tablero = Grafico(1, 1, AppCompatResources.getDrawable(context, R.drawable.tablero))

    init {
        for (x in posicionesX.indices)
            for (y in posicionesX[x].indices) {
                fichasX[x][y] =
                    Grafico(x, y, AppCompatResources.getDrawable(context, R.drawable.equis))
                fichasO[x][y] =
                    Grafico(x, y, AppCompatResources.getDrawable(context, R.drawable.circulo))
            }
    }


    override fun onSizeChanged(ancho:Int, alto:Int, ancho_anter:Int, alto_anter:Int) {
        super.onSizeChanged(ancho, alto, ancho_anter, alto_anter)

        tablero.posicionX = if(ancho<alto) 0 else (ancho-alto)/2
        tablero.posicionY = if(ancho<alto) (alto-ancho)/2 else 0
        tablero.ancho = if(ancho<alto) ancho else alto

        //unidadMedida es el ancho que ocupa cada casilla, es decir, cada ficha
        val unidadMedida = if(ancho<alto) ancho/3 else alto/3

        for(x in fichasX.indices)
            for(y in fichasX[x].indices) {
                //TODO cambiar las posiciones X e Y en función de cada casilla
                fichasX[x][y].ancho = unidadMedida
                fichasX[x][y].posicionX = tablero.posicionX!! + unidadMedida * x
                fichasX[x][y].posicionY = tablero.posicionY!! + tablero.ancho!! - unidadMedida * (y + 1)

                fichasO[x][y].ancho = unidadMedida
                fichasO[x][y].posicionX = tablero.posicionX!! + unidadMedida * x
                fichasO[x][y].posicionY = tablero.posicionY!! + tablero.ancho!! - unidadMedida * (y + 1)
            }

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        //Poner alpha a 0
        for (i in fichasX.indices) {
            for (j in fichasX[i].indices) {
                fichasX[i][j].dibujaGrafico(canvas)
                fichasO[i][j].dibujaGrafico(canvas)
            }
        }

        tablero.dibujaTablero(canvas)
    }
}