package com.taller.practico;

public class ErrorComparacionString {
    public static void main(String[] args) {
        // Generación explícita de dos objetos String en posiciones distintas de memoria
        String proveedor1 = new String("OneTrust");
        String proveedor2 = "OneTrust";

        // ERROR ORIGINAL: if (proveedor1 == proveedor2)
        // '==' compara REFERENCIAS: new String crea un objeto nuevo en el heap,
        // distinto del literal del String Pool, por eso daba false.
        // CORRECCIÓN: .equals() compara el CONTENIDO.
        if (proveedor1.equals(proveedor2)) {
            System.out.println("Los proveedores coinciden exactamente.");
        } else {
            System.out.println("Los proveedores son distintos según la comparación por contenido.");
        }
    }
}
