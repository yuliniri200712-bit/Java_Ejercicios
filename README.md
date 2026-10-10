# Java_Ejercicios

<div align="center">

# Taller Unidad 1.2 — Operadores y Estructuras de Decisión en Java

**Taller práctico intensivo desarrollado en IntelliJ IDEA**

![Java](https://img.shields.io/badge/Java-JDK%2017%2B-orange?style=for-the-badge&logo=openjdk&logoColor=white)
![IntelliJ IDEA](https://img.shields.io/badge/IDE-IntelliJ%20IDEA-000000?style=for-the-badge&logo=intellijidea&logoColor=white)
![Estado](https://img.shields.io/badge/Estado-Completado-success?style=for-the-badge)

*Operadores aritméticos y lógicos · if-else · operador ternario · switch tradicional y moderno · depuración*

</div>

---

## Tabla de contenido

1. [Descripción](#descripción)
2. [Estructura del proyecto](#estructura-del-proyecto)
3. [Cómo ejecutar](#cómo-ejecutar)
4. [Sección 1 — Calentamiento de operadores](#sección-1--calentamiento-de-operadores)
5. [Sección 2 — Decisiones con if-else y ternario](#sección-2--decisiones-con-if-else-y-operador-ternario)
6. [Sección 3 — Selección múltiple con switch](#sección-3--selección-múltiple-con-switch)
7. [Sección 4 — Reto integrador](#sección-4--reto-integrador-simulador-de-caja)
8. [Sección 5 — Depuración de código](#sección-5--análisis-y-depuración-de-código)
9. [Checklist de autoevaluación](#checklist-de-autoevaluación)

---

## Descripción

Este repositorio contiene la solución completa del taller **"Operadores y Estructuras de Decisión en Java con IntelliJ IDEA"**. El objetivo es dominar la lógica de control en Java: cómo la JVM evalúa operadores, cómo se bifurca el flujo de un programa con condicionales y cómo diagnosticar errores clásicos usando el depurador del IDE.

Cada reto está implementado en **su propia clase con su propio método `main`**, de modo que cualquiera puede ejecutarse de forma independiente para verificar su funcionamiento.

---

## Estructura del proyecto

```
TallerUnidad1_2/
└── src/
    └── com/
        └── taller/
            └── practico/
                ├── Seccion1Operadores.java      ← Sección 1
                ├── Reto1Edades.java             ← Sección 2
                ├── Reto2Notas.java              ← Sección 2
                ├── Reto3Tarifas.java            ← Sección 2
                ├── RefactorTernario.java        ← Sección 2
                ├── SwitchTradicional.java       ← Sección 3
                ├── SwitchModerno.java           ← Sección 3
                ├── RetoIntegrador.java          ← Sección 4
                ├── ErrorAsignacion.java         ← Sección 5
                ├── ErrorComparacionString.java  ← Sección 5
                ├── ErrorFallThrough.java        ← Sección 5
                └── ErrorPrecision.java          ← Sección 5
```

| Configuración | Valor |
|---|---|
| Nombre del proyecto | `TallerUnidad1_2` |
| Lenguaje | Java |
| Sistema de construcción | IntelliJ |
| JDK | 17 o superior (probado con JDK 27) |
| Paquete | `com.taller.practico` |

---

## Cómo ejecutar

### Desde IntelliJ IDEA
1. Abrir la carpeta `TallerUnidad1_2` con **File → Open**.
2. Abrir la clase que se quiera probar dentro de `com.taller.practico`.
3. Presionar **`Shift + F10`** (Windows/Linux) o **`Control + R`** (macOS), o hacer clic en la flecha verde junto al `main`.

### Desde la terminal
```bash
cd TallerUnidad1_2
javac -encoding UTF-8 -d out src/com/taller/practico/*.java
java -cp out com.taller.practico.RetoIntegrador
```
> Cambia `RetoIntegrador` por el nombre de cualquier otra clase.

> **Nota:** la expresión `switch` con flecha (`->`) requiere **JDK 14 o superior**; el taller recomienda JDK 17 LTS.

---

## Sección 1 — Calentamiento de operadores

**Clase:** `Seccion1Operadores.java`

Se analiza la precedencia de operadores, el operador residuo (`%`), el incremento prefijo vs posfijo, la promoción de tipos y la evaluación en cortocircuito. Cada expresión parte del estado inicial `int x = 7;` y `boolean flag = false;`.

| Expresión en Java | Predicción del estudiante | Resultado real | Explicación |
|---|---|---|---|
| `int seg = 86400 % 3600;` | `0` | `0` | 86400 = 24 × 3600, por lo tanto no queda residuo. |
| `int y = ++x + x++;` | `16` | `16` (x queda en `9`) | `++x` incrementa primero (x = 8) y usa 8; `x++` usa 8 y después incrementa (x = 9). |
| `double div1 = 15 / 2;`<br>`double div2 = 15.0 / 2;` | `7.5` y `7.5` | `7.0` y `7.5` | `15 / 2` es división **entera** (da 7) y luego se convierte a `7.0`. En `15.0 / 2` el `2` se promueve a `double`. |
| `boolean res = flag && (++x > 0);` | `false` | `false` (x sigue en `7`) | **Cortocircuito:** como `flag` es `false`, Java no evalúa el lado derecho y `++x` nunca se ejecuta. |

**Evidencia: tabla resuelta en el PDF del taller**

![Tabla de evaluación de expresiones resuelta](tabla_seccion1.png)

> **Aprendizaje clave:** el tipo de la variable que recibe el resultado (`double`) no cambia cómo se calcula la operación. Si ambos operandos son `int`, la división se trunca **antes** de asignarse.

---

## Sección 2 — Decisiones con if-else y operador ternario

### Reto 1: Clasificación de edades
**Clase:** `Reto1Edades.java`

Estructura en cascada `if - else if - else` que asigna una categoría y un perfil de acceso según la edad.

| Rango de edad | Categoría | Perfil de acceso |
|---|---|---|
| `< 12` | Infante | Requiere tutor de cuenta |
| `12 – 17` | Adolescente | Acceso restringido a foros comunitarios |
| `18 – 64` | Adulto | Acceso a funcionalidades estándar |
| `≥ 65` | Adulto Mayor | Acceso con interfaz adaptada |

**Casos probados:** 10 → Infante · 15 → Adolescente · 28 → Adulto · 68 → Adulto Mayor

---

### Reto 2: Sistema de aprobación de notas y asistencia
**Clase:** `Reto2Notas.java`

Combina operadores lógicos (`&&`) para determinar el dictamen académico:

```java
if (calificacionFinal >= 70.0 && porcentajeAsistencia >= 80) {
    estado = "Aprobado";
} else if (entregoProyectoFinal && calificacionFinal >= 60.0) {
    estado = "Pendiente de Examen Extraordinario";
} else {
    estado = "Reprobado";
}
```

| Nota | Asistencia | Proyecto final | Dictamen |
|---|---|---|---|
| 85.0 | 90 % | No | Aprobado |
| 65.0 | 70 % | Sí | Pendiente de Examen Extraordinario |
| 75.0 | 60 % | Sí | Pendiente de Examen Extraordinario |
| 50.0 | 95 % | Sí | Reprobado |
| 65.0 | 85 % | No | Reprobado |

---

### Reto 3: Tarifas de entrada
**Clase:** `Reto3Tarifas.java`

Calcula el precio final a partir de un precio base de **$100.00**. Los `String` se comparan con `.equals()`, nunca con `==`.

| Tipo de entrada | Regla | Total a pagar |
|---|---|---|
| VIP | +10 % de recargo | $110.00 |
| ESTUDIANTE | −50 % de descuento | $50.00 |
| ADULTO_MAYOR | −40 % de descuento | $60.00 |
| GENERAL | Sin modificación | $100.00 |

---

### Refactorización a operador ternario
**Clase:** `RefactorTernario.java`

**Antes** (if-else de 6 líneas):
```java
String estadoConexion;
if (cookieSesionActiva == true) {
    estadoConexion = "ACTIVA";
} else {
    estadoConexion = "INACTIVA";
}
```

**Después** (una sola línea):
```java
String estadoConexion = cookieSesionActiva ? "ACTIVA" : "INACTIVA";
```

> Además de reducir líneas, se elimina la comparación redundante `== true`.

---

## Sección 3 — Selección múltiple con switch

Programa que recibe el número de un día (1 = Lunes … 7 = Domingo) y devuelve el tipo de jornada. Ambas versiones se prueban con los valores **0 a 8** para verificar también el caso `default`.

### Implementación 1 — Switch tradicional
**Clase:** `SwitchTradicional.java`
```java
switch (numeroDia) {
    case 1:
        resultado = "Lunes - Jornada Laboral";
        break;
    // ...
    default:
        resultado = "Día no válido (debe estar entre 1 y 7)";
        break;
}
```

### Implementación 2 — Switch moderno (expresión con `->`)
**Clase:** `SwitchModerno.java`
```java
final String resultado = switch (numeroDia) {
    case 1 -> "Lunes - Jornada Laboral";
    // ...
    default -> "Día no válido (debe estar entre 1 y 7)";
};
```

### Comparación

| Criterio | Switch tradicional | Switch moderno (`->`) |
|---|---|---|
| Control de flujo | Necesita `break` en cada caso; sin él cae en cascada | No usa `break`; solo se ejecuta el caso correspondiente |
| Retorno de valores | Es una sentencia: hay que declarar la variable antes y mutarla | Es una expresión: devuelve el valor directamente, permite `final` |
| Riesgo de errores | Alto (fall-through involuntario) | Bajo |

| Día | Resultado |
|---|---|
| 1 – 5 | Lunes a Viernes — Jornada Laboral |
| 6 – 7 | Sábado y Domingo — Fin de Semana |
| 0 y 8 | Día no válido |

---

## Sección 4 — Reto integrador: simulador de caja

**Clase:** `RetoIntegrador.java`

Motor de liquidación para una tienda de tecnología que combina **operadores aritméticos**, **switch** y **if-else con operadores lógicos**.

### Reglas de negocio
1. **Subtotal base:** `cantidad × $250.00`
2. **Descuento por cliente** (vía `switch`): VIP 20 % · REGULAR 10 % · NUEVO 5 % · otro 0 %
3. **Cupón** (vía `if` + `&&`): si el cupón es `"PROMO1 año"` **y** el subtotal con descuento es ≥ $500.00, se descuentan $50.00 adicionales
4. **IVA:** 16 % sobre la base imponible
5. **Total:** base imponible + IVA

### Resultados

| Paso | Caso A — 3 unidades, VIP | Caso B — 1 unidad, NUEVO |
|---|---|---|
| Subtotal base | $750.00 | $250.00 |
| Descuento por cliente | −$150.00 (20 %) | −$12.50 (5 %) |
| Subtotal con descuento | $600.00 | $237.50 |
| Cupón "PROMO1 año" | −$50.00 aplica | $0.00 no aplica (< $500) |
| Base imponible | $550.00 | $237.50 |
| IVA 16 % | +$88.00 | +$38.00 |
| **Total a pagar** | **$638.00** | **$275.50** |

> Ambos resultados coinciden exactamente con los esperados por el taller.

> En equipos configurados en español (Colombia) `printf` muestra coma decimal (`$638,00`); el valor es el mismo.

---

## Sección 5 — Análisis y depuración de código

Cuatro fragmentos con errores clásicos, cada uno en su propia clase. Se inspeccionaron con el depurador de IntelliJ IDEA y se corrigieron. En cada archivo, el comentario **"ERROR ORIGINAL"** documenta el código con fallo.

| # | Clase | Error | Corrección | Salida corregida |
|---|---|---|---|---|
| 1 | `ErrorAsignacion` | `if (cookieActiva = true)` **asigna** en vez de comparar, por lo que siempre entra al bloque "activa" | `if (cookieActiva)` | Sesión inactiva |
| 2 | `ErrorComparacionString` | `==` compara **referencias de memoria**; `new String()` crea un objeto distinto al literal del String Pool | `proveedor1.equals(proveedor2)` | Los proveedores coinciden |
| 3 | `ErrorFallThrough` | Sin `break`, el flujo **cae en cascada** e imprime todos los casos siguientes | `break` al final de cada `case` | Solo imprime "Lunes" |
| 4 | `ErrorPrecision` | `3 / 4` entre enteros da `0`, así que el porcentaje era `0.0%` | `(double) tareasCompletadas / tareasTotales` | 75.0 % |

### Proceso de depuración en IntelliJ IDEA

| Paso | Acción | Atajo |
|---|---|---|
| 1 | Colocar un **breakpoint** en la primera línea del `main` | Clic en el margen izquierdo |
| 2 | Ejecutar en **modo Debug** | `Shift + F9` |
| 3 | Avanzar línea por línea (**Step Over**) observando la pestaña *Variables* | `F8` |
| 4 | Evaluar expresiones (**Evaluate Expression**) | `Alt + F8` |

---

## Checklist de autoevaluación

- [x] Proyecto `TallerUnidad1_2` creado en IntelliJ IDEA con el paquete `com.taller.practico`
- [x] Tabla de la Sección 1 con predicción y resultado real verificado en el IDE
- [x] Los 3 retos de if-else implementados (edades, notas y tarifas)
- [x] Asignación refactorizada con operador ternario (`? :`)
- [x] Switch implementado en sus dos variantes: tradicional con `break` y moderno con `->`
- [x] Reto integrador con totales exactos: **$638.00** (Caso A) y **$275.50** (Caso B)
- [x] Los 4 fragmentos defectuosos identificados y corregidos con el depurador
