package com.sync.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Administrator on 2016/11/1 0001.
 */
public class CRUD {

  public static void main(String[] args) throws SQLException {
    //create();
    //read();
    //update();
    delete();
  }

  static void create() throws SQLException {
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;
    try {
      // 2.建立链接
      conn = JdbcUtils.getConnection();

      // 3.创建语句
      st = conn.createStatement();

      String sql = "insert into user(name,birthday,money) values('name1','1987-01-01','400')";

      // 4.执行语句
      int i = st.executeUpdate(sql);

      // 5.处理结果
      System.out.println("i=" + i);
    } finally {
      JdbcUtils.free(rs, st, conn);
    }
  }

  static void read() throws SQLException {
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;
    try {
      // 2.建立链接
      conn = JdbcUtils.getConnection();

      // 3.创建语句
      st = conn.createStatement();

      // 4.执行语句
      rs = st.executeQuery("SELECT id,name,birthday,money FROM USER ");

      // 5.处理结果
      while (rs.next()) {
        System.out.println(rs.getObject("id") + "\t"
            + rs.getObject("name") + "\t"
            + rs.getObject("birthday") + "\t"
            + rs.getObject("money"));
      }
    } finally {
      JdbcUtils.free(rs, st, conn);
    }
  }

  static void update() throws SQLException {
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;
    try {
      // 2.建立链接
      conn = JdbcUtils.getConnection();

      // 3.创建语句
      st = conn.createStatement();

      String sql = "UPDATE user SET money=money+10";

      // 4.执行语句
      int i = st.executeUpdate(sql);

      // 5.处理结果
      System.out.println("i=" + i);
    } finally {
      JdbcUtils.free(rs, st, conn);
    }
  }

  static void delete() throws SQLException {
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;
    try {
      // 2.建立链接
      conn = JdbcUtils.getConnection();

      // 3.创建语句
      st = conn.createStatement();

      String sql = "DELETE FROM user WHERE id > 4";

      // 4.执行语句
      int i = st.executeUpdate(sql);

      // 5.处理结果
      System.out.println("i=" + i);
    } finally {
      JdbcUtils.free(rs, st, conn);
    }
  }
}
