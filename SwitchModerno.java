package com.taller.practico;

/** Sección 3 - Implementación 2: expresión switch moderna con notación de flecha (->). */
public class SwitchModerno {
    public static void main(String[] args) {
        System.out.println("=== SWITCH MODERNO: DÍAS DE LA SEMANA ===");
        for (int numeroDia = 0; numeroDia <= 8; numeroDia++) {
            final String resultado = switch (numeroDia) {
                case 1 -> "Lunes - Jornada Laboral";
                case 2 -> "Martes - Jornada Laboral";
                case 3 -> "Miércoles - Jornada Laboral";
                case 4 -> "Jueves - Jornada Laboral";
                case 5 -> "Viernes - Jornada Laboral";
                case 6 -> "Sábado - Fin de Semana";
                case 7 -> "Domingo - Fin de Semana";
                default -> "Día no válido (debe estar entre 1 y 7)";
            };
            System.out.println("Día " + numeroDia + " -> " + resultado);
        }
    }
}
