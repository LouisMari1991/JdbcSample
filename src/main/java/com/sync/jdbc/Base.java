package com.sync.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Administrator on 2016/11/1 0001.
 */
public class Base {

  public static void main(String[] args) throws Exception {
    //test();
    //template();

    //for (int i = 0; i < 15; i++) {
    //  Connection conn = JdbcUtils.getConnection();
    //  System.out.println(conn);
    //  JdbcUtils.free(null, null, conn);
    //}
    template();
  }

  static void template() throws Exception {
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;
    try {
      // 2.建立链接
      conn = JdbcUtils.getConnection();

      // 3.创建语句
      st = conn.createStatement();

      // 4.执行语句
      rs = st.executeQuery("SELECT * FROM USER ");

      // 5.处理结果
      while (rs.next()) {
        System.out.println(rs.getObject(1) + "\t" + rs.getObject(2) + "\t" + rs.getObject(3) + "\t" + rs.getObject(4));
      }
    } finally {
      JdbcUtils.free(rs, st, conn);
    }
  }

  static void test() throws SQLException, ClassNotFoundException {
    // 1.注册驱动,三种注册方式
    //DriverManager.registerDriver(new com.mysql.jdbc.Driver());
    //System.setProperty("jdbc.drivers", "com.mysql.jdbc.Driver");
    Class.forName("com.mysql.jdbc.Driver"); // 推荐方式

    // 2.建立链接
    String url = "jdbc:mysql://localhost:3306/jdbc";
    String user = "root";
    String password = "root";
    Connection conn = DriverManager.getConnection(url, user, password);

    // 3.创建语句
    Statement st = conn.createStatement();

    // 4.执行语句
    ResultSet rs = st.executeQuery("SELECT * FROM USER ");

    // 5.处理结果
    while (rs.next()) {
      System.out.println(rs.getObject(1) + "\t" + rs.getObject(2) + "\t" + rs.getObject(3) + "\t" + rs.getObject(4));
    }

    // 6.释放资源
    rs.close();
    st.close();
    conn.close();
  }
}
