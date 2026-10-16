package com.taller.practico.ciclos.seccion1;

import java.util.Scanner;

public class Ejercicio1_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        final int CENTINELA = -99;
        int acumulador = 0;
        int contador = 0;
        System.out.print("Ingrese un número entero (-99 para terminar): ");
        int numero = scanner.nextInt();

        while (numero != CENTINELA) {
            acumulador += numero;
            contador++;

            System.out.print("Ingrese un número entero (-99 para terminar): ");
            numero = scanner.nextInt();
        }

        System.out.println("\n--- Resultados ---");
        System.out.println("Total acumulado: " + acumulador);
        System.out.println("Cantidad de números ingresados: " + contador);

        scanner.close();
    }
}