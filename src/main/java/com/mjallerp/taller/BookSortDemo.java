package com.mjallerp.taller;

import com.mjallerp.taller.modelo.Book;

import java.util.Arrays;
import java.util.Comparator;

public class BookSortDemo {

  public static void sortBooks(Book[] books) {
    Arrays.sort(books,
        Comparator.comparing(Book::getTitle, String.CASE_INSENSITIVE_ORDER)
                  .thenComparingInt(Book::getPages));
  }

  public static void ejecutar() {
    Book[] books = {
      new Book("1", "Clean Code", "Robert Martin", 464),
      new Book("2", "Domain-Driven Design", "Eric Evans", 560),
      new Book("3", "Clean Code", "Robert Martin", 300),
      new Book("4", "Algorithms", "Sedgewick", 992)
    };
    System.out.println("Original : " + Arrays.toString(books));
    sortBooks(books);
    System.out.println("Ordenado : " + Arrays.toString(books));
  }

  public static void main(String[] args) {
    ejecutar();
  }
}
