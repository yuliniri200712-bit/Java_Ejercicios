package com.taller.practico.ciclos.seccion6;

public class Debug2 {
    public static void main(String[] args) {
        int totalElementos = 5;
        System.out.println("Imprimiendo elementos del 1 al " + totalElementos + ":");
        // Corrección: se usa <= para incluir el último elemento
        for (int i = 1; i <= totalElementos; i++) {
            System.out.println("Elemento " + i);
        }
    }
}
