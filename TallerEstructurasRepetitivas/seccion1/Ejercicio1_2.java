package com.taller.practico.ciclos.seccion1;

import java.util.Scanner;

public class Ejercicio1_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        final double LIMITE = 1_000_000;
        double saldo = 0;
        boolean procesando = true;
        String causa = "";

        while (procesando) {
            System.out.print("Ingrese el monto de la transacción: ");
            double monto = scanner.nextDouble();

            if (monto < 0) {
                procesando = false;
                causa = "Se ingresó un monto negativo (no válido).";
            } else {
                saldo += monto;
                if (saldo > LIMITE) {
                    procesando = false;
                    causa = "Se superó el límite máximo de $1'000.000.";
                }
            }
        }

        System.out.println("\n--- Informe final ---");
        System.out.println("Saldo final acumulado: $" + saldo);
        System.out.println("Causa de finalización: " + causa);

        scanner.close();
    }
}