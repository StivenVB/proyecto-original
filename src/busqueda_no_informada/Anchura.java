/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busqueda_no_informada;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author Stiven Vélez Bedoya
 * @author Cristian David Gómez Becerra
 */
public class Anchura {

    public char[][] busquedaAnchura(char[][] mapa, int filaInicio, int columnaInicio, int filaFin, int columnaFin) {

        int filas = mapa.length;
        int columnas = mapa[0].length;
        boolean[][] visitados = new boolean[filas][columnas];

        // Definir movimientos arriba y abajo
        int[] direccionesFila = {-1, 1, 0, 0};
        int[] direccionesColumna = {0, 0, -1, 1};
        String respuesta = "";
        Queue<Integer> cola = new LinkedList<>();
        cola.offer(filaInicio);
        cola.offer(columnaInicio);
        visitados[filaInicio][columnaInicio] = true;

        while (!cola.isEmpty()) {
            int fila = cola.poll();
            int columna = cola.poll();
            coordenadasVisitadas.add(mapa[fila][columna]);
            // Marcar como visitado
            visitados[fila][columna] = true;

            // Verificar si se llegó a la posición de destino
            if (fila == filaFin && columna == columnaFin) {
                return mapa;
            }

            // Explorar los vecinos arriba, abajo, izquierda, derecha
            for (int i = 0; i < 4; i++) {
                int nuevaFila = fila + direccionesFila[i];
                int nuevaColumna = columna + direccionesColumna[i];

                // Verificar si el vecino está dentro de los límites del mapaa
                // y si es un camino 'C' y no ha sido visitado
                if (nuevaFila >= 0 && nuevaFila < filas && nuevaColumna >= 0 && nuevaColumna < columnas
                        && mapa[nuevaFila][nuevaColumna] != '#' && !visitados[nuevaFila][nuevaColumna]) {

                    cola.offer(nuevaFila);
                    cola.offer(nuevaColumna);
                    visitados[nuevaFila][nuevaColumna] = true;

                }
            }

        }

        return null;
    }
}
