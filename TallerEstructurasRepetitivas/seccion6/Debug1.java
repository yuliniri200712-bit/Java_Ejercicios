package com.taller.practico.ciclos.seccion6;

public class Debug1 {
    public static void main(String[] args) {
        int contador = 1;
        int limite = 10;
        System.out.println("Iniciando procesamiento...");
        while (contador <= limite) {
            System.out.println("Procesando elemento número: " + contador);
            contador++; // Corrección: se actualiza la variable de control
        }
        System.out.println("Procesamiento finalizado con éxito.");
    }
}