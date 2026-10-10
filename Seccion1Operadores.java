package com.taller.practico;

/** Sección 1: Calentamiento de operadores. Estado inicial: int x = 7; boolean flag = false; */
public class Seccion1Operadores {
    public static void main(String[] args) {
        System.out.println("=== SECCIÓN 1: CALENTAMIENTO DE OPERADORES ===");

        // Fila 1: operador residuo. 86400 = 24 * 3600 -> residuo 0
        int seg = 86400 % 3600;
        System.out.println("int seg = 86400 % 3600;          -> seg = " + seg);

        // Fila 2: prefijo vs posfijo
        int x = 7;
        int y = ++x + x++; // 8 + 8 = 16, x termina en 9
        System.out.println("int y = ++x + x++;               -> y = " + y + " (x queda en " + x + ")");

        // Fila 3: división entera vs real
        double div1 = 15 / 2;   // 7 -> 7.0
        double div2 = 15.0 / 2; // 7.5
        System.out.println("double div1 = 15 / 2;            -> div1 = " + div1);
        System.out.println("double div2 = 15.0 / 2;          -> div2 = " + div2);

        // Fila 4: cortocircuito
        x = 7;
        boolean flag = false;
        boolean res = flag && (++x > 0); // el lado derecho no se evalúa
        System.out.println("boolean res = flag && (++x > 0); -> res = " + res + " (x sigue en " + x + ")");
    }
}
