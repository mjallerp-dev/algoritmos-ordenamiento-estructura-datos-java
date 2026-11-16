package com.mjallerp;

import com.mjallerp.consola.LectorConsola;
import com.mjallerp.consola.MenuPrincipal;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    new MenuPrincipal(new LectorConsola(scanner)).mostrar();
  }
}
