package com.taller.practico;

/** Sección 3 - Implementación 1: switch tradicional con case, break y default. */
public class SwitchTradicional {
    public static void main(String[] args) {
        System.out.println("=== SWITCH TRADICIONAL: DÍAS DE LA SEMANA ===");
        for (int numeroDia = 0; numeroDia <= 8; numeroDia++) {
            String resultado;
            switch (numeroDia) {
                case 1:
                    resultado = "Lunes - Jornada Laboral";
                    break;
                case 2:
                    resultado = "Martes - Jornada Laboral";
                    break;
                case 3:
                    resultado = "Miércoles - Jornada Laboral";
                    break;
                case 4:
                    resultado = "Jueves - Jornada Laboral";
                    break;
                case 5:
                    resultado = "Viernes - Jornada Laboral";
                    break;
                case 6:
                    resultado = "Sábado - Fin de Semana";
                    break;
                case 7:
                    resultado = "Domingo - Fin de Semana";
                    break;
                default:
                    resultado = "Día no válido (debe estar entre 1 y 7)";
                    break;
            }
            System.out.println("Día " + numeroDia + " -> " + resultado);
        }
    }
}
