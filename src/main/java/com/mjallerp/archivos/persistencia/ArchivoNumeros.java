package com.mjallerp.archivos.persistencia;

import com.mjallerp.archivos.modelo.DatosArchivo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

public final class ArchivoNumeros {
  private static final String SEPARADORES = "[\\s,;]+";

  private ArchivoNumeros() {}

  public static DatosArchivo leer(Path ruta) throws IOException {
    if (ruta == null || !Files.isRegularFile(ruta)) {
      throw new IOException("El archivo no existe: " + ruta);
    }
    String contenido = Files.readString(ruta, StandardCharsets.UTF_8).replace("﻿", "").trim();
    String[] tokens = contenido.isEmpty() ? new String[0] : contenido.split(SEPARADORES);

    int validos = 0;
    for (String t : tokens) if (esEntero(t)) validos++;

    int[] numeros = new int[validos];
    String[] invalidos = new String[tokens.length - validos];
    int k = 0, m = 0;
    for (String t : tokens) {
      if (esEntero(t)) numeros[k++] = Integer.parseInt(t);
      else invalidos[m++] = t;
    }
    return new DatosArchivo(ruta, numeros, invalidos);
  }

  public static void escribir(Path ruta, int[] numeros) throws IOException {
    Path carpeta = ruta.toAbsolutePath().getParent();
    if (carpeta != null) Files.createDirectories(carpeta);
    StringBuilder sb = new StringBuilder();
    for (int v : numeros) sb.append(v).append(System.lineSeparator());
    Files.writeString(ruta, sb.toString(), StandardCharsets.UTF_8);
  }

  public static int[] generarAleatorio(Path ruta, int cantidad, int minimo, int maximo) throws IOException {
    if (cantidad < 0) throw new IllegalArgumentException("La cantidad no puede ser negativa");
    if (minimo > maximo) throw new IllegalArgumentException("El mínimo no puede ser mayor que el máximo");
    Random random = new Random();
    int[] numeros = new int[cantidad];
    for (int i = 0; i < cantidad; i++) numeros[i] = minimo + random.nextInt(maximo - minimo + 1);
    escribir(ruta, numeros);
    return numeros;
  }

  public static Path rutaSalida(Path entrada, Path carpetaSalida) {
    String nombre = entrada.getFileName().toString();
    int punto = nombre.lastIndexOf('.');
    String base = punto > 0 ? nombre.substring(0, punto) : nombre;
    return carpetaSalida.resolve(base + "_ordenado.txt");
  }

  private static boolean esEntero(String t) {
    try {
      Integer.parseInt(t);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }
}
