package org.example.Practica2;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

/**
 * Copyright 2023 Elena Megía Cañaveras
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific
 * language governing permissions and limitations under the
 * License.
 */
public class Tablero {
    public Scanner scanner = new Scanner(System.in);
    private static int DIMENSION = 30;
    private int[][] estadoActual;
    private int[][] estadoSiguiente = new int[DIMENSION][DIMENSION];

    public void leerEstadoActual() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/example/Practica2/matriz.txt"));
            estadoActual = new int[DIMENSION][DIMENSION];
            for (int i = 0; i < DIMENSION; i++) {
                String fila = br.readLine();
                for (int j = 0; j < DIMENSION; j++) {
                    estadoActual[i][j] = Character.getNumericValue(fila.charAt(j));
                    estadoSiguiente[i][j] = 0;
                }
            }
            br.close();

        } catch (IOException e) {
            System.out.println("Error al leer el fichero");
        }
    }

// La secuencia de ceros y unos del fichero es guardada en ‘estadoActual‘ y, utilizando las reglas del juego de la vida, se insertan los ceros y unos correspondientes en ‘estadoSiguiente‘.

    /********************************************************
     * Genera un estado inicial aleatorio. Para cada celda
     * genera un número aleatorio en el intervalo [0, 1). Si
     * el número es menor que 0,5, entonces la celda está
     * inicialmente viva. En caso contrario, está muerta.
     *******************************************************/
    public void generarEstadoActualPorMontecarlo() {
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                estadoActual[i][j] = Math.random() < 0.5 ? 1 : 0;
                estadoSiguiente[i][j] = 0;
            }
        }
    }
// La secuencia de ceros y unos generada es guardada en ‘estadoActual‘ y, utilizando las reglas del juego de la vida, se insertan los ceros y unos correspondientes en ‘estadoSiguiente‘.

    /********************************************************
     * Transita al estado siguiente según las reglas del
     * juego de la vida.
     ********************************************************/
    public void transitarAlEstadoSiguiente() {
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                int vecinos = 0;
                if (i > 0 && j > 0 && estadoSiguiente[i - 1][j - 1] == 1) {
                    vecinos++;
                }
                if (i > 0 && estadoSiguiente[i - 1][j] == 1) {
                    vecinos++;
                }
                if (i > 0 && j < DIMENSION - 1 && estadoSiguiente[i - 1][j + 1] == 1) {
                    vecinos++;
                }
                if (j > 0 && estadoSiguiente[i][j - 1] == 1) {
                    vecinos++;
                }
                if (j < DIMENSION - 1 && estadoSiguiente[i][j + 1] == 1) {
                    vecinos++;
                }
                if (i < DIMENSION - 1 && j > 0 && estadoSiguiente[i + 1][j - 1] == 1) {
                    vecinos++;
                }
                if (i < DIMENSION - 1 && estadoSiguiente[i + 1][j] == 1) {
                    vecinos++;
                }
                if (i < DIMENSION - 1 && j < DIMENSION - 1 && estadoSiguiente[i + 1][j + 1] == 1) {
                    vecinos++;
                }
                if (estadoSiguiente[i][j] == 1) {
                    if (vecinos < 2 || vecinos > 3) {
                        estadoSiguiente[i][j] = 0;
                    }
                } else {
                    if (vecinos == 3) {
                        estadoSiguiente[i][j] = 1;
                    }
                }
            }
        }

    }
// La variable ‘estadoActual‘ pasa a tener el contenido de ‘estadoSiguiente‘ y, éste útimo atributo pasar a reflejar el siguiente estado.

    /*******************************************************
     * Devuelve, en modo texto, el estado actual.
     * @return el estado actual.
     *******************************************************/
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                if (estadoActual[i][j] == 1) {
                    sb.append("*");
                } else {
                    sb.append(".");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}