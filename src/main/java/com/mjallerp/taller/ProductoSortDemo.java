package com.mjallerp.taller;

import com.mjallerp.taller.modelo.Producto;

import java.util.Arrays;
import java.util.Comparator;

public class ProductoSortDemo {

  public static void ordenarPorPrecioAsc(Producto[] productos) {
    Arrays.sort(productos, Comparator.comparingDouble(Producto::getPrecio));
  }

  public static void ejecutar() {
    Producto[] productos = {
      new Producto(1, "Mouse", 19.99),
      new Producto(2, "Teclado", 29.99),
      new Producto(3, "Monitor", 120.50),
      new Producto(4, "USB 16GB", 8.75),
      new Producto(5, "Silla Gamer", 250.00)
    };
    System.out.println("Original:");
    System.out.println(Arrays.toString(productos));
    ordenarPorPrecioAsc(productos);
    System.out.println("\nOrdenado por precio (ascendente):");
    System.out.println(Arrays.toString(productos));
  }

  public static void main(String[] args) {
    ejecutar();
  }
}
