package com.mjallerp.taller.modelo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Venta {
  private final int id;
  private final Date fecha;
  private final List<ItemVenta> items;

  public Venta(int id, Date fecha, List<ItemVenta> items) {
    this.id = id;
    this.fecha = fecha;
    this.items = new ArrayList<>(items);
  }

  public int getId() { return id; }
  public Date getFecha() { return fecha; }
  public List<ItemVenta> getItems() { return items; }

  public void ordenarItemsPorPrecio() {
    items.sort(Comparator.comparingDouble(ItemVenta::getPrecio));
  }

  @Override
  public String toString() {
    return "Venta " + id + " (" + fecha + ") -> " + items;
  }
}
