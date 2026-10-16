package com.taller.practico.ciclos.seccion3;

import java.util.Scanner;

public class Ejercicio3_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese un número entero N: ");
        int n = scanner.nextInt();

        System.out.println("\n--- Tabla de multiplicar del " + n + " ---");
        for (int i = 1; i <= 12; i++) {
            System.out.printf("%d x %2d = %d%n", n, i, n * i);
        }

        System.out.println("\n--- Conteo regresivo ---");
        for (int i = n; i >= 0; i--) {
            System.out.println(i);
        }

        scanner.close();
    }
}