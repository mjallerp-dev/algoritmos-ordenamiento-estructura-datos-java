package com.mjallerp.archivos.servicio;

public class MergeSortConTraza {
  private final boolean mostrarPasos;
  private final boolean detalleMezcla;
  private int divisiones;
  private int mezclas;
  private int comparaciones;
  private int movimientos;
  private int llamadas;
  private int profundidadMaxima;

  public MergeSortConTraza(boolean mostrarPasos, boolean detalleMezcla) {
    this.mostrarPasos = mostrarPasos;
    this.detalleMezcla = mostrarPasos && detalleMezcla;
  }

  public void mergeSort(int[] a) {
    divisiones = mezclas = comparaciones = movimientos = llamadas = profundidadMaxima = 0;
    if (a == null || a.length < 2) return;
    int[] buffer = a.clone();
    mergeSortRecursive(a, buffer, 0, a.length, 0);
  }

  private void mergeSortRecursive(int[] a, int[] buffer, int lo, int hi, int nivel) {
    llamadas++;
    profundidadMaxima = Math.max(profundidadMaxima, nivel);
    if (hi - lo < 2) return;
    int mid = (lo + hi) / 2;
    divisiones++;
    imprimir(nivel, "Dividir " + rango(a, lo, hi) + " -> "
        + rango(a, lo, mid) + " | " + rango(a, mid, hi));
    mergeSortRecursive(a, buffer, lo, mid, nivel + 1);
    mergeSortRecursive(a, buffer, mid, hi, nivel + 1);
    merge(a, buffer, lo, mid, hi, nivel);
  }

  private void merge(int[] a, int[] buffer, int lo, int mid, int hi, int nivel) {
    String izquierda = rango(a, lo, mid);
    String derecha = rango(a, mid, hi);
    int i = lo, j = mid, k = lo;
    while (i < mid && j < hi) {
      comparaciones++;
      if (a[i] <= a[j]) {
        detalle(nivel, a[i] + " <= " + a[j] + " -> toma " + a[i] + " de la izquierda");
        buffer[k++] = a[i++];
      } else {
        detalle(nivel, a[i] + " > " + a[j] + " -> toma " + a[j] + " de la derecha");
        buffer[k++] = a[j++];
      }
    }
    while (i < mid) buffer[k++] = a[i++];
    while (j < hi) buffer[k++] = a[j++];
    System.arraycopy(buffer, lo, a, lo, hi - lo);
    movimientos += 2 * (hi - lo);
    mezclas++;
    imprimir(nivel, "Mezclar " + izquierda + " + " + derecha + " -> " + rango(a, lo, hi));
  }

  public static boolean isSortedAsc(int[] a) {
    if (a == null) return true;
    for (int i = 1; i < a.length; i++) {
      if (a[i - 1] > a[i]) return false;
    }
    return true;
  }

  private void imprimir(int nivel, String texto) {
    if (mostrarPasos) System.out.println("  ".repeat(nivel) + texto);
  }

  private void detalle(int nivel, String texto) {
    if (detalleMezcla) System.out.println("  ".repeat(nivel) + "   · " + texto);
  }

  private static String rango(int[] a, int lo, int hi) {
    StringBuilder sb = new StringBuilder("[");
    for (int i = lo; i < hi; i++) {
      if (i > lo) sb.append(", ");
      sb.append(a[i]);
    }
    return sb.append("]").toString();
  }

  public int getDivisiones() { return divisiones; }
  public int getMezclas() { return mezclas; }
  public int getComparaciones() { return comparaciones; }
  public int getMovimientos() { return movimientos; }
  public int getLlamadas() { return llamadas; }
  public int getProfundidadMaxima() { return profundidadMaxima; }
}
