package com.taller.practico;

public class ErrorFallThrough {
    public static void main(String[] args) {
        int opcionCategoria = 1;

        // ERROR ORIGINAL: sin 'break', al entrar en case 1 la ejecución caía en
        // cascada e imprimía Lunes, Martes, Miércoles y "Día no identificado".
        // CORRECCIÓN: agregar 'break' al final de cada caso.
        switch (opcionCategoria) {
            case 1:
                System.out.println("Categoría Selección: Lunes (Jornada Laboral)");
                break;
            case 2:
                System.out.println("Categoría Selección: Martes (Jornada Laboral)");
                break;
            case 3:
                System.out.println("Categoría Selección: Miércoles (Jornada Laboral)");
                break;
            default:
                System.out.println("Día no identificado");
        }
    }
}
