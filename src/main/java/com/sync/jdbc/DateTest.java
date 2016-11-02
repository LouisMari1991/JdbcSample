package com.sync.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/2 0002.
 */
public class DateTest {

  public static void main(String[] args) throws SQLException {
    //create("name2", new Date(), 400.0f);
    Date d = read(4);
    System.out.println(d);
  }

  static void create(String name, Date birthday, float money) throws SQLException {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
      // 2.建立链接
      conn = JdbcUtils.getConnection();

      String sql = "insert into user(name,birthday,money) values(?,?,?)";
      // 3.创建语句
      ps = conn.prepareStatement(sql);
      ps.setString(1, name);
      ps.setDate(2, new java.sql.Date(birthday.getTime()));
      ps.setFloat(3, money);

      // 4.执行语句
      int i = ps.executeUpdate();

      // 5.处理结果
      System.out.println("i=" + i);
    } finally {
      JdbcUtils.free(rs, ps, conn);
    }
  }

  static Date read(int id) throws SQLException {
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;
    Date birthday = null;
    try {
      // 2.建立链接
      conn = JdbcUtils.getConnection();

      // 3.创建语句
      st = conn.createStatement();

      // 4.执行语句
      rs = st.executeQuery("SELECT birthday FROM USER WHERE id="+id);

      // 5.处理结果
      while (rs.next()) {
        birthday = rs.getDate("birthday");
      }
    } finally {
      JdbcUtils.free(rs, st, conn);
    }
    return birthday;
  }
}
