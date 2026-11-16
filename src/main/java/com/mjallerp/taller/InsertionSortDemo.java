package com.mjallerp.taller;

import java.util.Arrays;

public class InsertionSortDemo {

  public static void insertionSort(char[] a) {
    if (a == null) return;
    for (int i = 1; i < a.length; i++) {
      char key = a[i];
      int j = i - 1;
      while (j >= 0 && a[j] > key) {
        a[j + 1] = a[j];
        j--;
      }
      a[j + 1] = key;
    }
  }

  public static boolean isSortedAsc(char[] a) {
    if (a == null) return true;
    for (int i = 1; i < a.length; i++) {
      if (a[i - 1] > a[i]) return false;
    }
    return true;
  }

  public static void ejecutar() {
    char[] data = { 'z', 'b', 'a', 'm', 'c', 'a' };
    System.out.println("Original : " + Arrays.toString(data));
    insertionSort(data);
    System.out.println("Ordenado : " + Arrays.toString(data));
    System.out.println("¿Sorted asc? : " + isSortedAsc(data));
  }

  public static void main(String[] args) {
    ejecutar();
  }
}
