package com.mjallerp.taller.modelo;

public class ItemVenta {
  private final String producto;
  private final double precio;

  public ItemVenta(String producto, double precio) {
    this.producto = producto;
    this.precio = precio;
  }

  public String getProducto() { return producto; }
  public double getPrecio() { return precio; }

  @Override
  public String toString() {
    return producto + " ($" + precio + ")";
  }
}
