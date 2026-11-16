package com.mjallerp.taller;

import java.util.Arrays;

public class MergeSortDemo {

  public static void mergeSort(long[] a) {
    if (a == null || a.length < 2) return;
    long[] buffer = a.clone();
    mergeSortRecursive(a, buffer, 0, a.length);
  }

  private static void mergeSortRecursive(long[] a, long[] buffer, int lo, int hi) {
    if (hi - lo < 2) return;
    int mid = (lo + hi) / 2;
    mergeSortRecursive(a, buffer, lo, mid);
    mergeSortRecursive(a, buffer, mid, hi);
    int i = lo, j = mid, k = lo;
    while (i < mid && j < hi) {
      if (a[i] <= a[j]) buffer[k++] = a[i++];
      else buffer[k++] = a[j++];
    }
    while (i < mid) buffer[k++] = a[i++];
    while (j < hi) buffer[k++] = a[j++];
    System.arraycopy(buffer, lo, a, lo, hi - lo);
  }

  public static boolean isSortedAsc(long[] a) {
    if (a == null) return true;
    for (int i = 1; i < a.length; i++) {
      if (a[i - 1] > a[i]) return false;
    }
    return true;
  }

  public static void ejecutar() {
    long[] data = { 7, 2, 9, 1, 6, 4, 3, 8, 5 };
    System.out.println("Original : " + Arrays.toString(data));
    mergeSort(data);
    System.out.println("Ordenado : " + Arrays.toString(data));
    System.out.println("¿Sorted asc? : " + isSortedAsc(data));
  }

  public static void main(String[] args) {
    ejecutar();
  }
}
