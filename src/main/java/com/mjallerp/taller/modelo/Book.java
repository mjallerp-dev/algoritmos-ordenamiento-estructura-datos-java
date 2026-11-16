package com.mjallerp.taller.modelo;

import java.util.Objects;

public class Book {
  private final String isbn;
  private final String title;
  private final String author;
  private final int pages;

  public Book(String isbn, String title, String author, int pages) {
    this.isbn = isbn;
    this.title = title;
    this.author = author;
    this.pages = pages;
  }

  public String getIsbn() { return isbn; }
  public String getTitle() { return title; }
  public String getAuthor() { return author; }
  public int getPages() { return pages; }

  @Override
  public String toString() {
    return "[" + title + " - " + pages + "p, " + author + "]";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Book)) return false;
    Book book = (Book) o;
    return Objects.equals(isbn, book.isbn);
  }

  @Override
  public int hashCode() {
    return Objects.hash(isbn);
  }
}
