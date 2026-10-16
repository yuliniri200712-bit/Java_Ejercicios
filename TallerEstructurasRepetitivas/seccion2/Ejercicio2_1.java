package com.taller.practico.ciclos.seccion2;

import java.util.Scanner;

public class Ejercicio2_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int pin;

        do {
            System.out.print("Ingrese su PIN de 4 dígitos: ");
            pin = scanner.nextInt();

            if (pin < 1000 || pin > 9999) {
                System.out.println("Error: La clave debe tener exactamente 4 dígitos positivos");
            }
        } while (pin < 1000 || pin > 9999);

        System.out.println("Clave aceptada. Bienvenido.");
        scanner.close();
    }
}