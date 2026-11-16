package com.mjallerp.consola;

import com.mjallerp.archivos.consola.MenuArchivos;
import com.mjallerp.taller.BookSortDemo;
import com.mjallerp.taller.BubbleSortDemo;
import com.mjallerp.taller.CountingSortDemo;
import com.mjallerp.taller.InsertionSortDemo;
import com.mjallerp.taller.MergeSortDemo;
import com.mjallerp.taller.ProductoSortDemo;
import com.mjallerp.taller.QuickSortDemo;
import com.mjallerp.taller.SelectionSortDescDemo;
import com.mjallerp.taller.VentaSortDemo;

public class MenuPrincipal {
  private final LectorConsola lector;
  private final MenuArchivos menuArchivos;

  public MenuPrincipal(LectorConsola lector) {
    this.lector = lector;
    this.menuArchivos = new MenuArchivos(lector);
  }

  public void mostrar() {
    int opcion;
    do {
      System.out.println("\n===== ALGORITMOS DE ORDENAMIENTO =====");
      System.out.println("1. Bubble Sort (int[], ascendente)");
      System.out.println("2. Selection Sort (double[], descendente)");
      System.out.println("3. Insertion Sort (char[], ascendente)");
      System.out.println("4. Quick Sort (int[], pivote medio)");
      System.out.println("5. Merge Sort (long[], ascendente)");
      System.out.println("6. Counting Sort (int[], rango conocido)");
      System.out.println("7. Ordenamiento de objetos Book[]");
      System.out.println("8. Ordenamiento de objetos Producto[]");
      System.out.println("9. Ventas por fecha e items por precio");
      System.out.println("10. Ejercicio 10: Ordenamiento de archivos numéricos");
      System.out.println("0. Salir");
      opcion = lector.leerEntero("Seleccione una opción: ");
      System.out.println();
      switch (opcion) {
        case 1: BubbleSortDemo.ejecutar(); break;
        case 2: SelectionSortDescDemo.ejecutar(); break;
        case 3: InsertionSortDemo.ejecutar(); break;
        case 4: QuickSortDemo.ejecutar(); break;
        case 5: MergeSortDemo.ejecutar(); break;
        case 6: CountingSortDemo.ejecutar(); break;
        case 7: BookSortDemo.ejecutar(); break;
        case 8: ProductoSortDemo.ejecutar(); break;
        case 9: VentaSortDemo.ejecutar(); break;
        case 10: menuArchivos.mostrar(); break;
        case 0: System.out.println("Hasta luego."); break;
        default: System.out.println("Opción inválida.");
      }
      if (opcion >= 1 && opcion <= 9) lector.pausar();
    } while (opcion != 0);
  }
}
