package com.mjallerp.taller;

import java.util.Arrays;

public class CountingSortDemo {

  public static void countingSort(int[] a, int maxValue) {
    if (a == null || a.length == 0) return;
    int[] count = new int[maxValue + 1];
    for (int v : a) {
      count[v]++;
    }
    int index = 0;
    for (int v = 0; v <= maxValue; v++) {
      int c = count[v];
      while (c-- > 0) {
        a[index++] = v;
      }
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
    int[] data = { 3, 1, 0, 3, 2, 2, 1, 0, 5, 7, 3, 9 };
    System.out.println("Original : " + Arrays.toString(data));
    countingSort(data, 9);
    System.out.println("Ordenado : " + Arrays.toString(data));
    System.out.println("¿Sorted asc? : " + isSortedAsc(data));
  }

  public static void main(String[] args) {
    ejecutar();
  }
}
