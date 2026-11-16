package com.mjallerp.taller;

import java.util.Arrays;

public class BubbleSortDemo {

  public static void bubbleSort(int[] a) {
    if (a == null) return;
    boolean swapped;
    for (int n = a.length; n > 1; n--) {
      swapped = false;
      for (int i = 1; i < n; i++) {
        if (a[i - 1] > a[i]) {
          int tmp = a[i];
          a[i] = a[i - 1];
          a[i - 1] = tmp;
          swapped = true;
        }
      }
      if (!swapped) break;
    }
  }

  public static boolean isSortedAsc(int[] a) {
    if (a == null) return true;
    for (int i = 1; i < a.length; i++) {
      if (a[i - 1] > a[i]) return false;
    }
    return true;
  }

  public static void ejecutar() {
    int[] data = { 5, 1, 4, 2, 8, 0, 2, 9, 3 };
    System.out.println("Original : " + Arrays.toString(data));
    bubbleSort(data);
    System.out.println("Ordenado : " + Arrays.toString(data));
    System.out.println("¿Sorted? : " + isSortedAsc(data));

    int[] casiOrdenado = { 1, 2, 3, 4, 6, 5, 7, 8 };
    System.out.println("\nCasi ordenado (antes): " + Arrays.toString(casiOrdenado));
    bubbleSort(casiOrdenado);
    System.out.println("Casi ordenado (después): " + Arrays.toString(casiOrdenado));
  }

  public static void main(String[] args) {
    ejecutar();
  }
}
