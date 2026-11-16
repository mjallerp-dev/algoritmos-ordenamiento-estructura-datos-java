package com.mjallerp.taller;

import java.util.Arrays;

public class SelectionSortDescDemo {

  public static void selectionSortDesc(double[] a) {
    if (a == null) return;
    for (int i = 0; i < a.length - 1; i++) {
      int maxIndex = i;
      for (int j = i + 1; j < a.length; j++) {
        if (a[j] > a[maxIndex]) {
          maxIndex = j;
        }
      }
      if (maxIndex != i) {
        double tmp = a[i];
        a[i] = a[maxIndex];
        a[maxIndex] = tmp;
      }
    }
  }

  public static boolean isSortedDesc(double[] a) {
    if (a == null) return true;
    for (int i = 1; i < a.length; i++) {
      if (a[i - 1] < a[i]) return false;
    }
    return true;
  }

  public static void ejecutar() {
    double[] data = { 3.1, 5.2, 4.2, 7.5, 1.0, 9.3 };
    System.out.println("Original : " + Arrays.toString(data));
    selectionSortDesc(data);
    System.out.println("Ordenado : " + Arrays.toString(data));
    System.out.println("¿Sorted desc? : " + isSortedDesc(data));
  }

  public static void main(String[] args) {
    ejecutar();
  }
}
