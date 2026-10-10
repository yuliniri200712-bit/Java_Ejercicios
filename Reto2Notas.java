package com.taller.practico;

/** Sección 2 - Reto 2: Sistema de aprobación de notas y asistencia. */
public class Reto2Notas {
    public static void main(String[] args) {
        System.out.println("=== RETO 2: SISTEMA DE APROBACIÓN ===");
        evaluar(85.0, 90, false); // Aprobado
        evaluar(65.0, 70, true);  // Pendiente de Examen Extraordinario
        evaluar(75.0, 60, true);  // Pendiente de Examen Extraordinario
        evaluar(50.0, 95, true);  // Reprobado
        evaluar(65.0, 85, false); // Reprobado
    }

    static void evaluar(double calificacionFinal, int porcentajeAsistencia, boolean entregoProyectoFinal) {
        String estado;
        if (calificacionFinal >= 70.0 && porcentajeAsistencia >= 80) {
            estado = "Aprobado";
        } else if (entregoProyectoFinal && calificacionFinal >= 60.0) {
            estado = "Pendiente de Examen Extraordinario";
        } else {
            estado = "Reprobado";
        }
        System.out.println("Nota: " + calificacionFinal + " | Asistencia: " + porcentajeAsistencia
                + "% | Proyecto final: " + (entregoProyectoFinal ? "Sí" : "No")
                + " -> Dictamen: " + estado);
    }
}
