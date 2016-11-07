package com.sync.jdbc;

/**
 * Created by Administrator on 2016/11/6 0006.
 */
public class Bean {

  private String name;

  public Bean() {

  }

  public Bean(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override public String toString() {
    return "Bean name=" + this.name;
  }
}
