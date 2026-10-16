package com.taller.practico.ciclos.seccion4;

public class Ejercicio4_2 {
    public static void main(String[] args) {
        for (int i = 1; i <= 50; i++) {
            if (i % 2 != 0) {
                continue; // omite impares
            }
            if (i % 5 == 0) {
                continue; // omite múltiplos de 5
            }
            System.out.println(i);
        }
    }
}
