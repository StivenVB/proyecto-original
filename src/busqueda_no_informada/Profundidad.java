/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busqueda_no_informada;

import java.util.Stack;

/**
 *
 * @author Stiven Vélez Bedoya
 * @author Cristian David Gómez Becerra
 */
public class Profundidad {

    public char[][] busquedaProfundidad(char[][] mapa, int[] coordenadaInicial, int[] coordenadaFinal) {

        int filas = mapa.length;
        int columnas = mapa[0].length;

        Stack<int[]> pila = new Stack<>();
        boolean[][] visitados = new boolean[filas][columnas];

        pila.push(coordenadaInicial);

        while (!pila.isEmpty()) {

            int[] actual = pila.pop();
            int fila = actual[0];
            int columna = actual[1];

            // Si se llega a la casilla final, salir del bucle
            if (fila == coordenadaFinal[0] && columna == coordenadaFinal[1]) {
                break;
            }

            if (visitados[fila][columna]) {
                continue;
            }

            // Marcar la casilla como visitada en el mapa
            mapa[fila][columna] = 'V';
            visitados[fila][columna] = true;

            //Se definen los movimientos: derecha, izquierda, abajo y arriba
            int[][] direcciones = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

            for (int[] direccion : direcciones) {
                //A la coordenada actual se le suma la direccion hacia la que se movera
                int nuevaFila = fila + direccion[0];
                int nuevaColumna = columna + direccion[1];

                //Validamos que no hayamos salido del laberinto
                if (nuevaFila < 0 || nuevaFila >= mapa.length || nuevaColumna < 0 || nuevaColumna >= mapa[0].length) {
                    continue;
                }

                if (mapa[nuevaFila][nuevaColumna] != 'R' && mapa[nuevaFila][nuevaColumna] != 'M'
                        && !visitados[nuevaFila][nuevaColumna]) {
                    pila.push(new int[]{nuevaFila, nuevaColumna});
                }
            }
        }

        return mapa;
    }
}
