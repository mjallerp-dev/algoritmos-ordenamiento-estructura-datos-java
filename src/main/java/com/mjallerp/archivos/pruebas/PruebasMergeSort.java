package com.mjallerp.archivos.pruebas;

import com.mjallerp.archivos.modelo.DatosArchivo;
import com.mjallerp.archivos.persistencia.ArchivoNumeros;
import com.mjallerp.archivos.servicio.MergeSortConTraza;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public final class PruebasMergeSort {
  private static int exitosas;
  private static int fallidas;

  private PruebasMergeSort() {}

  public static void ejecutar() {
    exitosas = 0;
    fallidas = 0;

    probarOrden("Arreglo desordenado", new int[] {38, 27, 43, 3, 9, 82, 10}, new int[] {3, 9, 10, 27, 38, 43, 82});
    probarOrden("Valores repetidos", new int[] {5, 1, 5, 3, 1, 5}, new int[] {1, 1, 3, 5, 5, 5});
    probarOrden("Arreglo ya ordenado", new int[] {1, 2, 3, 4, 5, 6}, new int[] {1, 2, 3, 4, 5, 6});
    probarOrden("Arreglo invertido", new int[] {9, 7, 5, 3, 1}, new int[] {1, 3, 5, 7, 9});
    probarOrden("Números negativos", new int[] {0, -5, 12, -1, 7}, new int[] {-5, -1, 0, 7, 12});
    probarOrden("Un solo elemento", new int[] {42}, new int[] {42});
    probarOrden("Arreglo vacío", new int[0], new int[0]);

    MergeSortConTraza sorter = new MergeSortConTraza(false, false);
    sorter.mergeSort(null);
    registrar("Arreglo nulo no lanza excepción", true, "sin excepción", "sin excepción");

    int[] siete = {38, 27, 43, 3, 9, 82, 10};
    sorter.mergeSort(siete);
    registrar("Divisiones y mezclas para n = 7 (n - 1 = 6)",
        sorter.getDivisiones() == 6 && sorter.getMezclas() == 6, "6 y 6",
        sorter.getDivisiones() + " y " + sorter.getMezclas());

    Path temporal = null;
    Path salida = null;
    try {
      temporal = Files.createTempFile("numeros", ".txt");
      Files.writeString(temporal, "10, 3;7\n abc 5\n2.5 -8", StandardCharsets.UTF_8);
      DatosArchivo datos = ArchivoNumeros.leer(temporal);
      verificar("Lectura con separadores mixtos", new int[] {10, 3, 7, 5, -8}, datos.getNumeros());
      registrar("Valores inválidos ignorados", Arrays.equals(new String[] {"abc", "2.5"}, datos.getInvalidos()),
          "[abc, 2.5]", Arrays.toString(datos.getInvalidos()));

      int[] numeros = datos.getNumeros();
      new MergeSortConTraza(false, false).mergeSort(numeros);
      salida = Files.createTempFile("numeros_ordenado", ".txt");
      ArchivoNumeros.escribir(salida, numeros);
      verificar("Guardar y volver a leer el archivo de salida", new int[] {-8, 3, 5, 7, 10},
          ArchivoNumeros.leer(salida).getNumeros());

      Files.writeString(temporal, "   ", StandardCharsets.UTF_8);
      verificar("Archivo vacío", new int[0], ArchivoNumeros.leer(temporal).getNumeros());
    } catch (IOException e) {
      registrar("Pruebas con archivos", false, "sin errores", e.getMessage());
    } finally {
      borrar(temporal);
      borrar(salida);
    }

    try {
      ArchivoNumeros.leer(Path.of("no_existe_123.txt"));
      registrar("Archivo inexistente", false, "IOException", "sin excepción");
    } catch (IOException e) {
      registrar("Archivo inexistente", true, "IOException", "IOException");
    }

    System.out.println("\nResultado: " + exitosas + " exitosas, " + fallidas + " fallidas");
  }

  private static void probarOrden(String nombre, int[] datos, int[] esperado) {
    new MergeSortConTraza(false, false).mergeSort(datos);
    verificar(nombre, esperado, datos);
  }

  private static void verificar(String nombre, int[] esperado, int[] obtenido) {
    registrar(nombre, Arrays.equals(esperado, obtenido), Arrays.toString(esperado), Arrays.toString(obtenido));
  }

  private static void registrar(String nombre, boolean ok, String esperado, String obtenido) {
    if (ok) exitosas++; else fallidas++;
    System.out.printf("[%s] %s -> obtenido %s%n", ok ? "OK" : "FALLO", nombre, obtenido);
    if (!ok) System.out.println("       esperado " + esperado);
  }

  private static void borrar(Path ruta) {
    if (ruta == null) return;
    try {
      Files.deleteIfExists(ruta);
    } catch (IOException ignored) {
    }
  }
}
