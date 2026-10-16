package com.taller.practico.ciclos.seccion6;

public class Debug3 {
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            if (i % 2 != 0) {
                System.out.println("Número impar detectado: " + i);
                continue; // Corrección: continue omite la iteración sin abortar el ciclo
            }
            System.out.println("Número par procesado: " + i);
        }
    }
}