package com.mjallerp.taller;

import com.mjallerp.taller.modelo.ItemVenta;
import com.mjallerp.taller.modelo.Venta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;

public class VentaSortDemo {

  public static void ordenarVentasPorFechaYItems(List<Venta> ventas) {
    ventas.sort(Comparator.comparing(Venta::getFecha));
    for (Venta v : ventas) {
      v.ordenarItemsPorPrecio();
    }
  }

  public static void ejecutar() {
    ItemVenta i1 = new ItemVenta("Teclado", 30.0);
    ItemVenta i2 = new ItemVenta("Mouse", 15.0);
    ItemVenta i3 = new ItemVenta("Monitor", 200.0);
    ItemVenta i4 = new ItemVenta("USB", 10.0);
    ItemVenta i5 = new ItemVenta("Silla", 120.0);

    Venta v1 = new Venta(1, new GregorianCalendar(2024, Calendar.MARCH, 10).getTime(),
        Arrays.asList(i1, i2, i3));
    Venta v2 = new Venta(2, new GregorianCalendar(2024, Calendar.JANUARY, 5).getTime(),
        Arrays.asList(i5, i4));
    Venta v3 = new Venta(3, new GregorianCalendar(2024, Calendar.FEBRUARY, 20).getTime(),
        Arrays.asList(i2, i5, i4));

    List<Venta> ventas = new ArrayList<>(Arrays.asList(v1, v2, v3));
    System.out.println("=== Ventas Originales ===");
    ventas.forEach(System.out::println);
    ordenarVentasPorFechaYItems(ventas);
    System.out.println("\n=== Ventas Ordenadas por Fecha y Items por Precio ===");
    ventas.forEach(System.out::println);
  }

  public static void main(String[] args) {
    ejecutar();
  }
}
