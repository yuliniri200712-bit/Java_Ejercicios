package com.taller.practico;

/** Sección 4: Simulador práctico de caja e inventario. */
public class RetoIntegrador {
    public static void main(String[] args) {
        System.out.println("=== RETO INTEGRADOR: SIMULADOR DE CAJA ===");
        System.out.println(">>> Caso de Prueba A");
        procesarCompra(3, "VIP", "PROMO1 año");   // Esperado: $638.00
        System.out.println();
        System.out.println(">>> Caso de Prueba B");
        procesarCompra(1, "NUEVO", "PROMO1 año"); // Esperado: $275.50
    }

    static void procesarCompra(int cantidad, String tipoCliente, String cupon) {
        final double COSTO_UNITARIO = 250.00;
        final double IVA = 0.16;

        // 1. Subtotal base
        double subtotalBase = cantidad * COSTO_UNITARIO;

        // 2. Descuento por categoría de cliente (switch)
        double porcentajeDescuento;
        switch (tipoCliente) {
            case "VIP":
                porcentajeDescuento = 0.20;
                break;
            case "REGULAR":
                porcentajeDescuento = 0.10;
                break;
            case "NUEVO":
                porcentajeDescuento = 0.05;
                break;
            default:
                porcentajeDescuento = 0.0;
                break;
        }
        double descuentoCliente = subtotalBase * porcentajeDescuento;
        double subtotalConDescuento = subtotalBase - descuentoCliente;

        // 3. Cupón y monto mínimo (if-else con &&)
        double descuentoCupon;
        if (cupon.equals("PROMO1 año") && subtotalConDescuento >= 500.00) {
            descuentoCupon = 50.00;
        } else {
            descuentoCupon = 0.00;
        }

        // 4. Impuestos y total
        double baseImponible = subtotalConDescuento - descuentoCupon;
        double montoIva = baseImponible * IVA;
        double totalFinal = baseImponible + montoIva;

        System.out.printf("Datos: cantidad=%d, tipoCliente=\"%s\", cupon=\"%s\"%n", cantidad, tipoCliente, cupon);
        System.out.printf("Subtotal Base:          $%.2f%n", subtotalBase);
        System.out.printf("Descuento %s (%.0f%%):   -$%.2f -> Subtotal con descuento = $%.2f%n",
                tipoCliente, porcentajeDescuento * 100, descuentoCliente, subtotalConDescuento);
        System.out.printf("Descuento por cupón:    -$%.2f (%s)%n", descuentoCupon,
                descuentoCupon > 0 ? "Aplica" : "NO aplica");
        System.out.printf("Base Imponible:         $%.2f%n", baseImponible);
        System.out.printf("IVA (16%%):             +$%.2f%n", montoIva);
        System.out.printf("Total a pagar = $%.2f%n", totalFinal);
    }
}
