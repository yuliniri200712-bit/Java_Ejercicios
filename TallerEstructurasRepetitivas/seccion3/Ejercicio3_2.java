package com.taller.practico.ciclos.seccion3;

import java.math.BigInteger;
import java.util.Scanner;

public class Ejercicio3_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese un número entero positivo N: ");
        int n = scanner.nextInt();

        // Parte A: factorial con BigInteger para evitar desbordamiento
        BigInteger factorial = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }
        System.out.println("\n" + n + "! = " + factorial);

        // Parte B: triángulo con ciclos anidados
        System.out.println("\nTriángulo de altura " + n + ":");
        for (int fila = 1; fila <= n; fila++) {
            for (int col = 1; col <= fila; col++) {
                System.out.print("* ");
            }
            System.out.println();
        }

        scanner.close();
    }
}