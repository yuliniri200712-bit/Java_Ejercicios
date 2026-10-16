package com.educacion.ahorcado;

import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static final int MAX_INTENTOS = 6;
    static final double PUNTAJE_BASE = 100.0;
    static final double PUNTOS_ACIERTO = 10.0;
    static final double PUNTOS_FALLO = 5.0;
    static final double COSTO_PISTA = 15.0;
    static final String LINEA = "====================================================";

    static final String[] PALABRAS = {
            "JAVA", "CLASE", "OBJETO", "METODO", "VARIABLE",
            "PROGRAMA", "COMPILADOR", "INTERFAZ", "HERENCIA", "BUCLE"
    };

    static final Scanner scanner = new Scanner(System.in);
    static final Random random = new Random();

    static int partidasJugadas = 0;
    static int partidasGanadas = 0;
    static double puntajeAcumulado = 0.0;
    static double mejorPuntaje = 0.0;
    static String mejorJugador = "-";

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

        int opcion;
        do {
            mostrarMenu();
            opcion = leerOpcionMenu();

            switch (opcion) {
                case 1: {
                    String otraVez;
                    do {
                        jugar();
                        System.out.print("¿Jugar otra vez? (S/N): ");
                        otraVez = scanner.nextLine().trim();
                    } while (otraVez.equalsIgnoreCase("S"));
                    break;
                }
                case 2:
                    verEstadisticas();
                    break;
                case 3:
                    System.out.println("\n¡Gracias por jugar! Hasta pronto.");
                    break;
                default:
                    System.out.println("Opción no reconocida.");
            }
        } while (opcion != 3);

        scanner.close();
    }

    static void mostrarMenu() {
        System.out.println();
        System.out.println(LINEA);
        System.out.println("        ADIVINA LA PALABRA SECRETA / AHORCADO");
        System.out.println(LINEA);
        System.out.println("1. Jugar");
        System.out.println("2. Ver Estadísticas");
        System.out.println("3. Salir");
    }

    static int leerOpcionMenu() {
        int opcion = 0;
        boolean opcionValida = false;

        while (!opcionValida) {
            System.out.print("Seleccione una opción (1-3): ");
            String entrada = scanner.nextLine().trim();
            try {
                opcion = Integer.parseInt(entrada);
                if (opcion >= 1 && opcion <= 3) {
                    opcionValida = true;
                } else {
                    System.out.println("[!] La opción " + opcion
                            + " está fuera de rango. Solo se permiten 1, 2 o 3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("[!] \"" + entrada
                        + "\" no es un número válido. Escriba 1, 2 o 3.");
            }
        }
        return opcion;
    }

    static String registrarJugador() {
        String nombreJugador = "";
        while (nombreJugador.length() == 0) {
            System.out.print("\nIngrese su nombre: ");
            nombreJugador = scanner.nextLine()
                    .trim()          
                    .toUpperCase();   
            if (nombreJugador.length() == 0) { 
                System.out.println("[!] El nombre no puede estar vacío.");
            }
        }
        return nombreJugador;
    }

    static void jugar() {
        String nombreJugador = registrarJugador();
        String palabraSecreta = PALABRAS[random.nextInt(PALABRAS.length)];

        final int maxIntentosPartida = calcularMaxIntentos(palabraSecreta);

        int intentosRestantes = maxIntentosPartida;
        double puntaje = PUNTAJE_BASE;
        boolean juegoGanado = false;
        boolean seRindio = false;
        boolean pistaUsada = false;
        char ultimaLetra = ' ';
        int aciertos = 0;
        int fallos = 0;
        int turnos = 0;

        StringBuilder mascara = new StringBuilder();
        for (int i = 0; i < palabraSecreta.length(); i++) {
            mascara.append('_');
            if (i < palabraSecreta.length() - 1) {
                mascara.append(' ');
            }
        }
        StringBuilder historial = new StringBuilder();

        System.out.printf("%nBienvenido, %s. La palabra tiene %d letras y tienes %d intentos.%n",
                nombreJugador, palabraSecreta.length(), maxIntentosPartida);
        System.out.println("Escribe una letra por turno o SALIR para rendirte.");

        while (intentosRestantes > 0 && !juegoGanado) {
            System.out.println();
            System.out.println("Palabra:  " + mascara);
            System.out.println("Usadas:   " + (historial.length() > 0 ? historial : "(ninguna)"));
            System.out.printf("Intentos: %d / %d%n", intentosRestantes, maxIntentosPartida);

            if (intentosRestantes == 1 && !pistaUsada) {
                int posicionOculta = mascara.indexOf("_") / 2;
                String pista = palabraSecreta.substring(posicionOculta, posicionOculta + 1);
                System.out.println("[PISTA] Te queda 1 intento. Una letra oculta es: " + pista
                        + " (-" + (int) COSTO_PISTA + " pts)");
                puntaje -= COSTO_PISTA;
                pistaUsada = true;
            }

            System.out.print("Letra: ");
            String entrada = scanner.nextLine().trim();

            if (entrada.length() == 0) {
                System.out.println("[!] No escribiste nada.");
                continue;
            }

            if (entrada.equalsIgnoreCase("SALIR")) {
                seRindio = true;
                break;
            }

            if (entrada.length() > 1 || !Character.isLetter(entrada.charAt(0))) {
                System.out.println("[!] Ingresa una sola letra (A-Z).");
                continue;
            }

            ultimaLetra = Character.toUpperCase(entrada.charAt(0));
            String letra = String.valueOf(ultimaLetra);

            if (historial.indexOf(letra) != -1) {
                System.out.println("[!] Ya probaste la letra " + letra + ". No pierdes intento.");
                continue;
            }

            historial.append(letra).append(' ');
            turnos++;

            boolean acierto = false;
            for (int i = 0; i < palabraSecreta.length(); i++) {
                String letraPalabra = String.valueOf(palabraSecreta.charAt(i));
                if (letraPalabra.equalsIgnoreCase(letra)) {
                    mascara.setCharAt(i * 2, ultimaLetra);
                    acierto = true;
                }
            }

            if (acierto) {
                aciertos++;
                puntaje += PUNTOS_ACIERTO;
            } else {
                fallos++;
                intentosRestantes--;
                puntaje -= PUNTOS_FALLO;
            }

            System.out.println(acierto
                    ? "[OK] ¡Bien! La letra " + letra + " está en la palabra."
                    : "[X] La letra " + letra + " no está. Pierdes un intento.");

            if (mascara.indexOf("_") == -1) {
                juegoGanado = true;
                break;
            }
            if (intentosRestantes == 0) {
                break;
            }
        }

        mostrarResumen(nombreJugador, palabraSecreta, juegoGanado, seRindio,
                intentosRestantes, maxIntentosPartida, aciertos, fallos, turnos, puntaje);
    }

    static int calcularMaxIntentos(String palabra) {
        int longitud = palabra.length();
        if (longitud <= 4) {
            return MAX_INTENTOS;
        } else if (longitud <= 7) {
            return MAX_INTENTOS + 1;
        } else {
            return MAX_INTENTOS + 2;
        }
    }

    static double calcularMultiplicador(boolean juegoGanado, String palabra,
                                        int turnos, int fallos, int maxIntentos) {
        if (!juegoGanado) {
            return 0.5;
        }
        int letrasDistintas = 0;
        for (int i = 0; i < palabra.length(); i++) {
            if (palabra.indexOf(palabra.charAt(i)) == i) {
                letrasDistintas++;
            }
        }
        double velocidad = (double) letrasDistintas / turnos;              
        double precision = (double) (maxIntentos - fallos) / maxIntentos; 
        return 1.0 + 0.5 * velocidad + 0.5 * precision;
    }

    static void mostrarResumen(String nombreJugador, String palabraSecreta,
                               boolean juegoGanado, boolean seRindio,
                               int intentosRestantes, int maxIntentos,
                               int aciertos, int fallos, int turnos, double puntaje) {

        double porcentajeAciertos = turnos > 0 ? (double) aciertos / turnos * 100 : 0.0;
        double multiplicador = calcularMultiplicador(juegoGanado, palabraSecreta,
                turnos, fallos, maxIntentos);
        double puntajeFinal = Math.max(0.0, puntaje) * multiplicador;

        String estadoFinal = juegoGanado ? "¡VICTORIA!" : (seRindio ? "RENDICIÓN" : "DERROTA");

        System.out.printf("%n%s%n", LINEA);
        System.out.printf("%33s%n", "RESUMEN DE LA PARTIDA");
        System.out.printf("%s%n", LINEA);
        System.out.printf("%-21s%s%n", "Jugador:", nombreJugador);
        System.out.printf("%-21s%s%n", "Estado Final:", estadoFinal);
        System.out.printf("%-21s%s%n", "Palabra Secreta:", palabraSecreta);
        System.out.printf("%-21s%d / %d%n", "Intentos Restantes:", intentosRestantes, maxIntentos);
        System.out.printf("%-21s%.2f%%%n", "Porcentaje Aciertos:", porcentajeAciertos);
        System.out.printf("%-21s%.2fx%n", "Multiplicador:", multiplicador);
        System.out.printf("%-21s%.2f pts%n", "Puntaje Final:", puntajeFinal);
        System.out.printf("%s%n", LINEA);

        partidasJugadas++;
        if (juegoGanado) {
            partidasGanadas++;
        }
        puntajeAcumulado += puntajeFinal;
        if (puntajeFinal > mejorPuntaje) {
            mejorPuntaje = puntajeFinal;
            mejorJugador = nombreJugador;
        }
    }

    static void verEstadisticas() {
        System.out.printf("%n%s%n", LINEA);
        System.out.printf("%32s%n", "ESTADÍSTICAS");
        System.out.printf("%s%n", LINEA);

        if (partidasJugadas == 0) {
            System.out.printf("%s%n", "Aún no se ha jugado ninguna partida.");
        } else {
            double porcentajeVictorias = (double) partidasGanadas / partidasJugadas * 100;
            double promedio = puntajeAcumulado / partidasJugadas;
            System.out.printf("%-21s%d%n", "Partidas Jugadas:", partidasJugadas);
            System.out.printf("%-21s%d%n", "Partidas Ganadas:", partidasGanadas);
            System.out.printf("%-21s%.2f%%%n", "Porcentaje Victoria:", porcentajeVictorias);
            System.out.printf("%-21s%.2f pts%n", "Puntaje Promedio:", promedio);
            System.out.printf("%-21s%.2f pts (%s)%n", "Mejor Puntaje:", mejorPuntaje, mejorJugador);
        }
        System.out.printf("%s%n", LINEA);
    }
}
