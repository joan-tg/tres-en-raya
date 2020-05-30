package com.a3ensemble.joan.tresenraya.modelo


class Tablero {
    /*
    casillas: Matriz de 3x3 de enteros
    Posibles valores:
    0->no hay ficha en esa posición
    1->hay una ficha X
    2->hay una ficha O
     */
    companion object {
        const val TAMANYO = 3
    }

    private val casillas = Array(TAMANYO) {IntArray(
        TAMANYO
    ) {0} }
    //antes private val casillas = arrayOf(IntArray(tamanyo) {0}, IntArray(tamanyo) {0}, IntArray(tamanyo) {0})
    private var casillasLibres = TAMANYO * TAMANYO

    //Reiniciamos a 0 todos los valores
    fun reiniciarTablero() {
        casillasLibres = casillas.size * casillas[0].size //es decir, 9
        for(i in 0..2)
            for(j in 0..2)
                casillas[i][j] = 0
    }

    //devuelve el array de los valores de todas las casillas del Tablero
    fun getCasillas():Array<IntArray> {
        return casillas
    }

    fun getCasillasLibres():Int {
        return casillasLibres
    }

    //Ponemos una ficha. Si esa casilla está ocupada, devuelve false (tipoFicha=1 -> X)
    fun ponerFicha(x:Int, y:Int, tipoFicha:Int):Boolean {
        return if (casillas[x][y]==0) {
            casillas[x][y]=tipoFicha
            casillasLibres--
            true
        } else false
    }


    fun haGanadoAlIntroducir(x: Int, y: Int, tipoFicha: Int):Boolean {
        if (casillasLibres <= 5) {
            //check col
            for (i in 0 until TAMANYO) {
                if (casillas[x][i] != tipoFicha) break
                if (i == TAMANYO - 1) {
                    return true
                }
            }

            //check row
            for (i in 0 until TAMANYO) {
                if (casillas[i][y] != tipoFicha) break
                if (i == TAMANYO - 1) {
                    return true
                }
            }

            //check diag
            if (x == y) {
                //we're on a diagonal
                for (i in 0 until TAMANYO) {
                    if (casillas[i][i] != tipoFicha) break
                    if (i == TAMANYO - 1) {
                        return true
                    }
                }
            }

            //check anti diag (thanks rampion)
            if (x + y == TAMANYO - 1) {
                for (i in 0 until TAMANYO) {
                    if (casillas[i][TAMANYO - 1 - i] != tipoFicha) break
                    if (i == TAMANYO - 1) {
                        return true
                    }
                }
            }
        }
        else return false
        return false //por qué??
    }


    /*
    Método para saber si ha ganado el jugador (devuelve true en caso afirmativo)
     */
    fun haGanadoAlIntroducir2(x: Int, y: Int):Boolean {
        /*
         Solamente se comprueba en el caso de que queden 4 o menos casillas libres,
         ya que significará que hay 5 piezas o más introducidas
         (son las mínimas para que pueda haber algun resultado ganador)
         */
        if (casillasLibres <= 5) {
            val valorCasilla = casillas[x][y]
            /*
            x será el eje X de la matriz
            y será el eje Y de la matriz
              Y
              ^
              |  - - -
              |  - - -
              |  - - -
                --------> X
             */
            if ((x + y) % 2 != 0) {
                /*
                La condición de arriba contempla los casos donde la suma de X e Y es impar:
                - X -
                X - X
                - X -
                 */
                if (x == 0) {
                    /*
                    - - -
                    X - -
                    - - -
                     */
                    if (casillas[x + 1][y] == valorCasilla && casillas[x + 2][y] == valorCasilla) return true
                    else if (casillas[x][y + 1] == valorCasilla && casillas[x][y - 1] == valorCasilla) return true
                } else if (x == 2) {
                    /*
                    - - -
                    - - X
                    - - -
                     */
                    if (casillas[x - 1][y] == valorCasilla && casillas[x - 2][y] == valorCasilla) return true
                    else if (casillas[x][y + 1] == valorCasilla && casillas[x][y - 1] == valorCasilla) return true
                } else if (x == 1) {
                    if (casillas[x - 1][y] == valorCasilla && casillas[x + 1][y] == valorCasilla) return true
                    if (y == 2) {
                        /*
                        - X -
                        - - -
                        - - -
                         */
                        if (casillas[x][y - 1] == valorCasilla && casillas[x][y - 2] == valorCasilla) return true
                    } else if (y == 0) {
                        /*
                        - - -
                        - - -
                        - X -
                         */
                        if (casillas[x][y + 1] == valorCasilla && casillas[x][y + 2] == valorCasilla) return true
                    }

                }
            } else {
                /*
                Casos:
                X - X
                - X -
                X - X
                Ahora sí que hay que comprobar diagonales
                 */
                return false
            }
        } else return false
        return false //¿Por qué?
    }

    override fun toString():String {
        var s = ""
        for(y in (casillas.size-1) downTo 0) {
            for(x in 0 until (casillas[y].size)) {
                s += when {
                    casillas[x][y]==1 -> "X"
                    casillas[x][y] == 2 -> "O"
                    else -> "-"
                }
                s += "  "
            }
            s += "\n"
        }
        return s
    }
}