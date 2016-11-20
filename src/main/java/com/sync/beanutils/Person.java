package com.sync.beanutils;

import java.util.Date;

/**
 * Created by Administrator on 2016/11/20 0020.
 */
public class Person {

  private String name;
  private String password;
  private int age;
  private Date birthday;

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  @Override public String toString() {
    return "Person{" +
        "name='" + name + '\'' +
        ", password='" + password + '\'' +
        ", age=" + age +
        ", birthday=" + birthday +
        '}';
  }
}
