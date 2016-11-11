package com.sync.jdbc.domain;

import java.util.Date;

/**
 * Created by Administrator on 2016/11/2 0002.
 */
public class User {

  private int id;
  private String name;
  private Date birthday;
  private float money;
  private Date regiestDate;

  public User() {

  }

  public void showName() {
    System.out.println(this.name);
  }

  public User(String name) {
    this.name = name;
  }

  public User(float money) {
    this.money = money;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  public float getMoney() {
    return money;
  }

  public void setMoney(float money) {
    this.money = money;
  }

  public Date getRegiestDate() {
    return regiestDate;
  }

  public void setRegiestDate(Date regiestDate) {
    this.regiestDate = regiestDate;
  }

  @Override public String toString() {
    return "User id=" + this.id + " name =" + this.name + " birthday=" + this.birthday + " money=" + this.money;
  }
}
