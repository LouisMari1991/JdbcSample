package com.sync.sax;

/**
 * Created by Administrator on 2016/11/23 0023.
 */
public class Book {

  private String name;
  private String author;
  private String price;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  @Override public String toString() {
    return "Book{" +
        "name='" + name + '\'' +
        ", author='" + author + '\'' +
        ", price='" + price + '\'' +
        '}';
  }
}
