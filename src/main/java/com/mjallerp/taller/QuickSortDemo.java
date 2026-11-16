package com.mjallerp.taller;

import java.util.Arrays;

public class QuickSortDemo {

  public static void quickSort(int[] a) {
    if (a == null || a.length < 2) return;
    quickSortRecursive(a, 0, a.length - 1);
  }

  private static void quickSortRecursive(int[] a, int lo, int hi) {
    int i = lo, j = hi;
    int pivot = a[(lo + hi) / 2];
    while (i <= j) {
      while (a[i] < pivot) i++;
      while (a[j] > pivot) j--;
      if (i <= j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
        i++;
        j--;
      }
    }
    if (lo < j) quickSortRecursive(a, lo, j);
    if (i < hi) quickSortRecursive(a, i, hi);
  }

  public static boolean isSortedAsc(int[] a) {
    if (a == null) return true;
    for (int i = 1; i < a.length; i++) {
      if (a[i - 1] > a[i]) return false;
    }
    return true;
  }

  public static void ejecutar() {
    int[] data = { 9, 1, 7, 3, 5, 8, 2, 4, 6 };
    System.out.println("Original : " + Arrays.toString(data));
    quickSort(data);
    System.out.println("Ordenado : " + Arrays.toString(data));
    System.out.println("¿Sorted asc? : " + isSortedAsc(data));
  }

  public static void main(String[] args) {
    ejecutar();
  }
}
