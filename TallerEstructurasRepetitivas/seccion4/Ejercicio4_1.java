package com.taller.practico.ciclos.seccion4;

public class Ejercicio4_1 {
    public static void main(String[] args) {
        for (int i = 1; i <= 100; i++) {
            if (i % 7 == 0 && i % 11 == 0) {
                System.out.println("Primer número divisible entre 7 y 11: " + i);
                break;
            }
        }
    }
}