package com.a3ensemble.joan.tresenraya.casosuso

import com.a3ensemble.joan.tresenraya.modelo.Tablero

class CasosUsoJuego {

    private val tablero = Tablero()
    private var jugadorActual = 1

    //más adelante
    //private val jugador1 = Jugador()
    //private val jugador2 = Jugador()

    fun ponerFicha(x: Int, y: Int, tipoFicha: Int): Boolean {
        return tablero.ponerFicha(x, y, tipoFicha)
    }

    fun haGanadoAlIntroducir(x: Int, y: Int, tipoFicha: Int):Boolean {
        return tablero.haGanadoAlIntroducir(x, y, tipoFicha)
    }

    fun getTablero(): Tablero {
        return this.tablero
    }

    fun getJugadorActual():Int {
        return this.jugadorActual
    }

    fun setJugadorActual(jugadorActual:Int) {
        this.jugadorActual = jugadorActual
    }
}