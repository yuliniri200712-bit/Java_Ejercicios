package com.taller.practico;

/** Sección 2 - Reto 3: Cálculo de tarifas de entrada y descuentos. */
public class Reto3Tarifas {
    public static void main(String[] args) {
        System.out.println("=== RETO 3: TARIFAS DE ENTRADA ===");
        final double precioBase = 100.0;
        String[] tipos = {"VIP", "ESTUDIANTE", "GENERAL", "ADULTO_MAYOR"};

        for (String tipoEntrada : tipos) {
            double precioFinal;
            String detalle;
            if (tipoEntrada.equals("VIP")) {
                precioFinal = precioBase * 1.10;
                detalle = "Recargo del 10%";
            } else if (tipoEntrada.equals("ESTUDIANTE")) {
                precioFinal = precioBase * 0.50;
                detalle = "Descuento del 50%";
            } else if (tipoEntrada.equals("ADULTO_MAYOR")) {
                precioFinal = precioBase * 0.60;
                detalle = "Descuento del 40%";
            } else if (tipoEntrada.equals("GENERAL")) {
                precioFinal = precioBase;
                detalle = "Tarifa base sin modificaciones";
            } else {
                System.out.println("Tipo de entrada no válido: " + tipoEntrada);
                continue;
            }
            System.out.printf("Entrada: %-13s | Precio base: $%.2f | %s | Total a pagar: $%.2f%n",
                    tipoEntrada, precioBase, detalle, precioFinal);
        }
    }
}
