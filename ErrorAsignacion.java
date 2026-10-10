package com.taller.practico;

public class ErrorAsignacion {
    public static void main(String[] args) {
        boolean cookieActiva = false;

        // ERROR ORIGINAL: if (cookieActiva = true)
        // '=' ASIGNA true a la variable y la expresión vale true, por eso siempre
        // entraba al bloque "activa" aunque la cookie estuviera en false.
        // CORRECCIÓN: usar la comparación '==' (o mejor, la variable directamente).
        if (cookieActiva) {
            System.out.println("Estado de sesión: Sesión del usuario activa.");
        } else {
            System.out.println("Estado de sesión: Sesión inactiva.");
        }
    }
}
