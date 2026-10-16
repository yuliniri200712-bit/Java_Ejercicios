package com.educacion.ahorcado;

import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

/**
 * Taller Práctico Integrador: "Adivina la Palabra Secreta / Ahorcado de Consola".
 *
 * Unidad 1.1 -> Tipos de datos, constantes, acumuladores.
 * Unidad 1.2 -> Menú con switch y validación condicional.
 * Unidad 1.3 -> Bucles do-while / while / for, break y continue.
 * Unidad 1.4 -> Normalización de String y máscara dinámica con StringBuilder.
 */
public class Main {

    // ===================== Unidad 1.1: constantes =====================
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

    // Estadísticas acumuladas de la sesión (opción 2 del menú)
    static int partidasJugadas = 0;
    static int partidasGanadas = 0;
    static double puntajeAcumulado = 0.0;
    static double mejorPuntaje = 0.0;
    static String mejorJugador = "-";

    public static void main(String[] args) {
        // Fuerza el punto decimal (83.33 y no 83,33) en printf
        Locale.setDefault(Locale.US);

        // ============ Fase 1: menú principal con do-while + switch ============
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
                    // No debería ocurrir: leerOpcionMenu solo devuelve 1, 2 o 3
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

    /**
     * Validación repetitiva con while: lee la línea completa con nextLine()
     * y la convierte con Integer.parseInt(), sin romper el programa.
     */
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

    // ============ Fase 2: registro del jugador (normalización de String) ============
    static String registrarJugador() {
        String nombreJugador = "";
        while (nombreJugador.length() == 0) {
            System.out.print("\nIngrese su nombre: ");
            nombreJugador = scanner.nextLine()
                    .trim()           // 1. limpieza de espacios
                    .toUpperCase();   // 2. estandarización
            if (nombreJugador.length() == 0) { // 3. validación de contenido
                System.out.println("[!] El nombre no puede estar vacío.");
            }
        }
        return nombreJugador;
    }

    // ============ Fase 3: bucle del juego con StringBuilder ============
    static void jugar() {
        String nombreJugador = registrarJugador();
        String palabraSecreta = PALABRAS[random.nextInt(PALABRAS.length)];

        // Reto 2: dificultad dinámica según la longitud de la palabra
        final int maxIntentosPartida = calcularMaxIntentos(palabraSecreta);

        // Variables de estado (tabla de la Fase 2)
        int intentosRestantes = maxIntentosPartida;
        double puntaje = PUNTAJE_BASE;
        boolean juegoGanado = false;
        boolean seRindio = false;
        boolean pistaUsada = false;
        char ultimaLetra = ' ';
        int aciertos = 0;
        int fallos = 0;
        int turnos = 0;

        // Máscara oculta: "_ _ _ _" (letra i en la posición i * 2)
        StringBuilder mascara = new StringBuilder();
        for (int i = 0; i < palabraSecreta.length(); i++) {
            mascara.append('_');
            if (i < palabraSecreta.length() - 1) {
                mascara.append(' ');
            }
        }
        // Historial dinámico de letras arriesgadas
        StringBuilder historial = new StringBuilder();

        System.out.printf("%nBienvenido, %s. La palabra tiene %d letras y tienes %d intentos.%n",
                nombreJugador, palabraSecreta.length(), maxIntentosPartida);
        System.out.println("Escribe una letra por turno o SALIR para rendirte.");

        while (intentosRestantes > 0 && !juegoGanado) {
            System.out.println();
            System.out.println("Palabra:  " + mascara);
            System.out.println("Usadas:   " + (historial.length() > 0 ? historial : "(ninguna)"));
            System.out.printf("Intentos: %d / %d%n", intentosRestantes, maxIntentosPartida);

            // Reto 1: pista inteligente con substring() cuando queda 1 intento
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

            // Entrada vacía -> se salta el turno sin castigo
            if (entrada.length() == 0) {
                System.out.println("[!] No escribiste nada.");
                continue;
            }

            // Rendición -> sale del bucle inmediatamente
            if (entrada.equalsIgnoreCase("SALIR")) {
                seRindio = true;
                break;
            }

            // Carácter inválido -> se salta el turno
            if (entrada.length() > 1 || !Character.isLetter(entrada.charAt(0))) {
                System.out.println("[!] Ingresa una sola letra (A-Z).");
                continue;
            }

            ultimaLetra = Character.toUpperCase(entrada.charAt(0));
            String letra = String.valueOf(ultimaLetra);

            // Letra repetida -> se salta el turno sin perder intento
            if (historial.indexOf(letra) != -1) {
                System.out.println("[!] Ya probaste la letra " + letra + ". No pierdes intento.");
                continue;
            }

            historial.append(letra).append(' ');
            turnos++;

            // Comparación con charAt() + equalsIgnoreCase()
            boolean acierto = false;
            for (int i = 0; i < palabraSecreta.length(); i++) {
                String letraPalabra = String.valueOf(palabraSecreta.charAt(i));
                if (letraPalabra.equalsIgnoreCase(letra)) {
                    mascara.setCharAt(i * 2, ultimaLetra);
                    acierto = true;
                }
            }

            // Solo se decrementa si la letra NO está en la palabra
            if (acierto) {
                aciertos++;
                puntaje += PUNTOS_ACIERTO;
            } else {
                fallos++;
                intentosRestantes--;
                puntaje -= PUNTOS_FALLO;
            }

            // Retroalimentación con operador ternario
            System.out.println(acierto
                    ? "[OK] ¡Bien! La letra " + letra + " está en la palabra."
                    : "[X] La letra " + letra + " no está. Pierdes un intento.");

            // Victoria: ya no quedan guiones ocultos
            if (mascara.indexOf("_") == -1) {
                juegoGanado = true;
                break;
            }
            // Derrota: se agotaron los intentos
            if (intentosRestantes == 0) {
                break;
            }
        }

        mostrarResumen(nombreJugador, palabraSecreta, juegoGanado, seRindio,
                intentosRestantes, maxIntentosPartida, aciertos, fallos, turnos, puntaje);
    }

    /**
     * Reto 2: MAX_INTENTOS es final y no puede cambiar, así que se calcula
     * un límite propio de la partida a partir de la constante y de length().
     */
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

    /**
     * Reto 3: multiplicador según velocidad (letras distintas / turnos usados)
     * y precisión (fallos sobre el máximo permitido). Rango: 1.00x a 2.00x.
     */
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
        double velocidad = (double) letrasDistintas / turnos;              // 1.0 = sin turnos de más
        double precision = (double) (maxIntentos - fallos) / maxIntentos; // 1.0 = sin fallos
        return 1.0 + 0.5 * velocidad + 0.5 * precision;
    }

    // ============ Fase 4: resumen formateado con printf ============
    static void mostrarResumen(String nombreJugador, String palabraSecreta,
                               boolean juegoGanado, boolean seRindio,
                               int intentosRestantes, int maxIntentos,
                               int aciertos, int fallos, int turnos, double puntaje) {

        // Casting a double para evitar la división entera
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

        // Actualización de estadísticas de la sesión
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
