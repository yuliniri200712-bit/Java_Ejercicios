package com.taller.practico;

public class ErrorPrecision {
    public static void main(String[] args) {
        int tareasCompletadas = 3;
        int tareasTotales = 4;

        // ERROR ORIGINAL: (tareasCompletadas / tareasTotales) * 100
        // 3 / 4 entre enteros = 0 (se truncan los decimales) y 0 * 100 = 0.0%.
        // CORRECCIÓN: moldeo (double) para forzar división real -> 0.75 * 100 = 75.0
        double porcentajeAvance = ((double) tareasCompletadas / tareasTotales) * 100;

        System.out.println("El porcentaje de avance es: " + porcentajeAvance + "%");
    }
}
