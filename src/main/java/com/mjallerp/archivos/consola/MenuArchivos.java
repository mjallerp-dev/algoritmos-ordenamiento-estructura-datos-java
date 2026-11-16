package com.mjallerp.archivos.consola;

import com.mjallerp.archivos.modelo.DatosArchivo;
import com.mjallerp.archivos.persistencia.ArchivoNumeros;
import com.mjallerp.archivos.pruebas.PruebasMergeSort;
import com.mjallerp.archivos.servicio.MergeSortConTraza;
import com.mjallerp.consola.LectorConsola;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class MenuArchivos {
  private static final Path CARPETA_ENTRADA = Path.of("datos", "entrada");
  private static final Path CARPETA_SALIDA = Path.of("datos", "salida");
  private static final int LIMITE_TRAZA = 64;
  private static final int LIMITE_IMPRESION = 100;

  private final LectorConsola lector;
  private Path entrada;
  private Path ultimaSalida;

  public MenuArchivos(LectorConsola lector) {
    this.lector = lector;
    this.entrada = CARPETA_ENTRADA.resolve("numeros.txt");
  }

  public void mostrar() {
    int opcion;
    do {
      System.out.println("\n===== ORDENAMIENTO DE ARCHIVOS NUMÉRICOS =====");
      System.out.println("Archivo de entrada: " + entrada);
      System.out.println("1. Seleccionar archivo de entrada");
      System.out.println("2. Ver números del archivo de entrada");
      System.out.println("3. Ordenar con Merge Sort y guardar el resultado");
      System.out.println("4. Ver archivo de salida");
      System.out.println("5. Generar archivo de números aleatorios");
      System.out.println("6. Ejecutar casos de prueba");
      System.out.println("7. Memoria auxiliar y complejidad de Merge Sort");
      System.out.println("0. Volver");
      opcion = lector.leerEntero("Seleccione una opción: ");
      System.out.println();
      switch (opcion) {
        case 1: seleccionarArchivo(); break;
        case 2: verEntrada(); break;
        case 3: ordenarYGuardar(); break;
        case 4: verSalida(); break;
        case 5: generarAleatorio(); break;
        case 6: PruebasMergeSort.ejecutar(); break;
        case 7: mostrarExplicacion(); break;
        case 0: break;
        default: System.out.println("Opción inválida.");
      }
      if (opcion != 0) lector.pausar();
    } while (opcion != 0);
  }

  private void seleccionarArchivo() {
    Path[] archivos = listarEntrada();
    if (archivos.length == 0) {
      System.out.println("No hay archivos .txt en " + CARPETA_ENTRADA.toAbsolutePath());
    } else {
      System.out.println("Archivos disponibles en " + CARPETA_ENTRADA + ":");
      for (int i = 0; i < archivos.length; i++) {
        System.out.println("  " + (i + 1) + ". " + archivos[i].getFileName());
      }
    }
    int opcion = lector.leerEntero("Número del archivo (0 para escribir otra ruta): ");
    if (opcion >= 1 && opcion <= archivos.length) {
      entrada = archivos[opcion - 1];
    } else if (opcion == 0) {
      String ruta = lector.leerTexto("Ruta del archivo: ");
      if (ruta.isEmpty()) {
        System.out.println("No se cambió el archivo.");
        return;
      }
      Path nueva = Path.of(ruta);
      if (!Files.isRegularFile(nueva)) {
        System.out.println("El archivo no existe: " + nueva.toAbsolutePath());
        return;
      }
      entrada = nueva;
    } else {
      System.out.println("Opción inválida.");
      return;
    }
    System.out.println("Archivo seleccionado: " + entrada);
  }

  private void verEntrada() {
    DatosArchivo datos = leerEntrada();
    if (datos == null) return;
    System.out.println("Cantidad de números: " + datos.getNumeros().length);
    System.out.println("Números: " + formatear(datos.getNumeros()));
  }

  private void ordenarYGuardar() {
    DatosArchivo datos = leerEntrada();
    if (datos == null) return;
    int[] numeros = datos.getNumeros();
    if (numeros.length == 0) {
      System.out.println("El archivo no contiene números enteros para ordenar.");
      return;
    }

    boolean mostrarPasos = numeros.length <= LIMITE_TRAZA;
    boolean detalle = false;
    if (mostrarPasos) {
      detalle = lector.leerEntero("¿Mostrar las comparaciones de cada mezcla? (1 = sí, 0 = no): ") == 1;
    } else {
      System.out.println("El archivo tiene más de " + LIMITE_TRAZA + " números: se muestra solo el resumen de las operaciones.");
    }

    System.out.println("\nArreglo original (" + numeros.length + " números):");
    System.out.println(formatear(numeros));
    if (mostrarPasos) System.out.println("\n--- Divisiones y mezclas ---");

    MergeSortConTraza sorter = new MergeSortConTraza(mostrarPasos, detalle);
    long inicio = System.nanoTime();
    sorter.mergeSort(numeros);
    long tiempo = System.nanoTime() - inicio;

    System.out.println("\nArreglo ordenado:");
    System.out.println(formatear(numeros));
    System.out.println("¿Ordenado ascendente? " + MergeSortConTraza.isSortedAsc(numeros));
    System.out.printf("Divisiones: %d | Mezclas: %d | Comparaciones: %d | Movimientos: %d | Llamadas recursivas: %d | Profundidad: %d%n",
        sorter.getDivisiones(), sorter.getMezclas(), sorter.getComparaciones(), sorter.getMovimientos(),
        sorter.getLlamadas(), sorter.getProfundidadMaxima());
    if (!mostrarPasos) System.out.printf("Tiempo de ordenamiento: %.3f ms%n", tiempo / 1_000_000.0);

    Path salida = ArchivoNumeros.rutaSalida(entrada, CARPETA_SALIDA);
    try {
      ArchivoNumeros.escribir(salida, numeros);
      ultimaSalida = salida;
      System.out.println("Resultado guardado en: " + salida.toAbsolutePath());
    } catch (IOException e) {
      System.out.println("No se pudo guardar el archivo de salida: " + e.getMessage());
    }
  }

  private void verSalida() {
    if (ultimaSalida == null) {
      Path esperada = ArchivoNumeros.rutaSalida(entrada, CARPETA_SALIDA);
      if (!Files.isRegularFile(esperada)) {
        System.out.println("Todavía no se ha generado un archivo de salida. Use la opción 3.");
        return;
      }
      ultimaSalida = esperada;
    }
    try {
      DatosArchivo datos = ArchivoNumeros.leer(ultimaSalida);
      System.out.println("Archivo de salida: " + ultimaSalida.toAbsolutePath());
      System.out.println("Cantidad de números: " + datos.getNumeros().length);
      System.out.println("Números: " + formatear(datos.getNumeros()));
    } catch (IOException e) {
      System.out.println("No se pudo leer el archivo de salida: " + e.getMessage());
    }
  }

  private void generarAleatorio() {
    int cantidad = lector.leerEntero("Cantidad de números a generar: ");
    if (cantidad < 1 || cantidad > 1_000_000) {
      System.out.println("La cantidad debe estar entre 1 y 1.000.000.");
      return;
    }
    int minimo = lector.leerEntero("Valor mínimo: ");
    int maximo = lector.leerEntero("Valor máximo: ");
    if (minimo > maximo || (long) maximo - minimo >= Integer.MAX_VALUE) {
      System.out.println("Rango inválido.");
      return;
    }
    String nombre = lector.leerTexto("Nombre del archivo (Enter = aleatorio.txt): ");
    if (nombre.isEmpty()) nombre = "aleatorio.txt";
    if (!nombre.endsWith(".txt")) nombre += ".txt";
    Path ruta = CARPETA_ENTRADA.resolve(nombre);
    try {
      ArchivoNumeros.generarAleatorio(ruta, cantidad, minimo, maximo);
      entrada = ruta;
      System.out.println("Archivo generado y seleccionado: " + ruta.toAbsolutePath());
    } catch (IOException e) {
      System.out.println("No se pudo generar el archivo: " + e.getMessage());
    }
  }

  private void mostrarExplicacion() {
    System.out.println("¿Por qué Merge Sort usa memoria auxiliar?");
    System.out.println("Para mezclar dos mitades ordenadas se escribe el menor elemento de cada paso en un arreglo");
    System.out.println("auxiliar (buffer). Si se escribiera directamente en el arreglo original se sobrescribirían");
    System.out.println("valores de la mitad derecha que todavía no se han comparado. Al terminar la mezcla, el");
    System.out.println("tramo ordenado del buffer se copia de vuelta al arreglo original con System.arraycopy.");
    System.out.println();
    System.out.println("Complejidad temporal: O(n log n) en el mejor, promedio y peor caso.");
    System.out.println("  - El arreglo se divide a la mitad log2(n) veces (niveles de recursión).");
    System.out.println("  - En cada nivel las mezclas recorren los n elementos: O(n) por nivel.");
    System.out.println("Memoria adicional: O(n) para el buffer + O(log n) para la pila de llamadas recursivas.");
    System.out.println("Es estable: con a[i] <= a[j] los valores repetidos conservan su orden original.");
  }

  private DatosArchivo leerEntrada() {
    try {
      DatosArchivo datos = ArchivoNumeros.leer(entrada);
      System.out.println("Archivo leído: " + entrada.toAbsolutePath());
      if (datos.tieneInvalidos()) {
        System.out.println("Se ignoraron " + datos.getInvalidos().length + " valores que no son enteros: "
            + String.join(", ", datos.getInvalidos()));
      }
      return datos;
    } catch (IOException e) {
      System.out.println("No se pudo leer el archivo: " + e.getMessage());
      return null;
    }
  }

  private Path[] listarEntrada() {
    if (!Files.isDirectory(CARPETA_ENTRADA)) return new Path[0];
    try (Stream<Path> archivos = Files.list(CARPETA_ENTRADA)) {
      return archivos.filter(p -> p.toString().endsWith(".txt")).toArray(Path[]::new);
    } catch (IOException e) {
      return new Path[0];
    }
  }

  private static String formatear(int[] a) {
    StringBuilder sb = new StringBuilder("[");
    int limite = Math.min(a.length, LIMITE_IMPRESION);
    for (int i = 0; i < limite; i++) {
      if (i > 0) sb.append(", ");
      sb.append(a[i]);
    }
    if (a.length > limite) sb.append(", ... (").append(a.length - limite).append(" más)");
    return sb.append("]").toString();
  }
}
