package com.a3ensemble.joan.tresenraya.presentacion

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Canvas
import android.os.Handler
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import com.a3ensemble.joan.tresenraya.R
import com.a3ensemble.joan.tresenraya.casosuso.CasosUsoJuego
import com.a3ensemble.joan.tresenraya.modelo.Grafico
import kotlin.math.floor

class VistaJuego(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    private val fichasX = Array(3) {Array(3) { Grafico() } }
    private val fichasO = Array(3) {Array(3) { Grafico() } }
    private val tablero = Grafico(
        1,
        1,
        AppCompatResources.getDrawable(context,
            R.drawable.tablero
        )
    )

    private var ancho = 0
    private var alto = 0

    private val usoJuego =
        CasosUsoJuego()

    init {
        for (x in fichasX.indices)
            for (y in fichasX[x].indices) {
                fichasX[x][y] =
                    Grafico(
                        x,
                        y,
                        AppCompatResources.getDrawable(context,
                            R.drawable.equis
                        )
                    )
                fichasO[x][y] =
                    Grafico(
                        x,
                        y,
                        AppCompatResources.getDrawable(context,
                            R.drawable.circulo
                        )
                    )
            }
    }


    override fun onSizeChanged(ancho:Int, alto:Int, ancho_anter:Int, alto_anter:Int) {
        super.onSizeChanged(ancho, alto, ancho_anter, alto_anter)

        tablero.posicionX = if(ancho<alto) 0 else (ancho-alto)/2
        tablero.posicionY = if(ancho<alto) (alto-ancho)/2 else 0
        tablero.ancho = if(ancho<alto) ancho else alto

        this.ancho = ancho
        this.alto = alto

        //unidadMedida es el ancho que ocupa cada casilla, es decir, cada ficha
        val unidadMedida = if(ancho<alto) ancho/3 else alto/3

        for(x in fichasX.indices)
            for(y in fichasX[x].indices) {
                //TODO cambiar las posiciones X e Y en función de cada casilla
                fichasX[x][y].ancho = unidadMedida
                fichasX[x][y].posicionX = tablero.posicionX + unidadMedida * x
                fichasX[x][y].posicionY = tablero.posicionY + tablero.ancho!! - unidadMedida * (y + 1)

                fichasO[x][y].ancho = unidadMedida
                fichasO[x][y].posicionX = tablero.posicionX + unidadMedida * x
                fichasO[x][y].posicionY = tablero.posicionY + tablero.ancho!! - unidadMedida * (y + 1)
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

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        when(event?.action) {
            MotionEvent.ACTION_UP -> {
                val x = event.x
                val y = event.y

                val i: Int
                val j: Int

                if(ancho<alto) { //Portrait
                    i = floor(x/(ancho/3)).toInt()
                    j = floor((tablero.posicionY+ancho-y)/(ancho/3)).toInt()
                    //Toast.makeText(context, "x:$i, y:$j" , Toast.LENGTH_SHORT).show()
                }
                else { //Landscape
                    i = floor((x-tablero.posicionX)/(ancho/3)).toInt()
                    j = floor(y/(ancho/3)).toInt()
                    //Toast.makeText(context, "x:$i, y:$j", Toast.LENGTH_SHORT).show()
                }

                if(i in 0..2 && j in 0..2) {
                    if(!usoJuego.ponerFicha(i, j, usoJuego.getJugadorActual()))
                        Toast.makeText(context, "No se ha introducido. Casilla ocupada", Toast.LENGTH_LONG).show()
                    else {
                        if(usoJuego.getJugadorActual()==1) fichasX[i][j].drawable?.setVisible(true, true)
                        else fichasO[i][j].drawable?.setVisible(true, true)

                        if(usoJuego.haGanadoAlIntroducir(i, j, usoJuego.getJugadorActual())) {
                            Toast.makeText(context, "Has ganado", Toast.LENGTH_LONG).show()

                            val handler = Handler()
                            handler.postDelayed(Runnable { getActivity()?.finish() }, 2000)

                        }
                        if(usoJuego.getJugadorActual()==1) usoJuego.setJugadorActual(2)
                        else usoJuego.setJugadorActual(1)
                    }
                }
                invalidate()
            }
        }
        return true
    }

    fun View.getActivity(): Activity? {
        var context = context
        while (context is ContextWrapper) {
            if(context is Activity) {
                return context
            }
            context = context.baseContext
        }
        return null
    }

}