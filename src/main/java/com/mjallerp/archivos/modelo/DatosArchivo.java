package com.mjallerp.archivos.modelo;

import java.nio.file.Path;

public class DatosArchivo {
  private final Path ruta;
  private final int[] numeros;
  private final String[] invalidos;

  public DatosArchivo(Path ruta, int[] numeros, String[] invalidos) {
    this.ruta = ruta;
    this.numeros = numeros;
    this.invalidos = invalidos;
  }

  public Path getRuta() { return ruta; }
  public int[] getNumeros() { return numeros; }
  public String[] getInvalidos() { return invalidos; }

  public boolean tieneInvalidos() {
    return invalidos.length > 0;
  }
}
