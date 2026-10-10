package com.taller.practico;

/** Sección 2 - Refactorización de un if-else a operador ternario. */
public class RefactorTernario {
    public static void main(String[] args) {
        System.out.println("=== REFACTORIZACIÓN A OPERADOR TERNARIO ===");
        boolean cookieSesionActiva = true;

        // Código original:
        // String estadoConexion;
        // if (cookieSesionActiva == true) {
        //     estadoConexion = "ACTIVA";
        // } else {
        //     estadoConexion = "INACTIVA";
        // }

        // Versión refactorizada en una sola línea:
        String estadoConexion = cookieSesionActiva ? "ACTIVA" : "INACTIVA";

        System.out.println("Estado de conexión: " + estadoConexion);
    }
}
