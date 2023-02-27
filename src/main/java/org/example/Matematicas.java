package org.example;
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
import java.util.Random;

public class Matematicas {

    /**
     * Genera una aproximación al número pi mediante el método de Montecarlo. El
     * parámetro 'pasos' indica el número de puntos generado.
     */
    public static double generarNumeroPi(long pasos) {
        int aciertos = 0;
        double areaCuadrado = 4;
        double x, y;
        Random rnd = new Random();

        for (long i = 1; i <= pasos; i++) {
            x = rnd.nextDouble() * 2 - 1; // Generamos un número aleatorio entre -1 y 1
            y = rnd.nextDouble() * 2 - 1; // Generamos otro número aleatorio entre -1 y 1

            if (Math.pow(x, 2) + Math.pow(y, 2) <= 1) {
                aciertos++; // Si está dentro del círculo, contamos un acierto
            }
        }

        return areaCuadrado * (aciertos / pasos);


    }
}