package com.sync.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Administrator on 2016/11/2 0002.
 */
public class SQLInject {

  public static void main(String[] args) throws SQLException {
    read("name1");
  }

  /**
   * PreparedStatement相对于Statement的优点
   * 1.没有SQL注入的问题.
   * 2.Statement会使数据库频繁编译SQL，可能造成数据库缓冲区溢出.
   * @param name
   * @throws SQLException
   */
  static void read(String name) throws SQLException {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
      // 2.建立链接
      conn = JdbcUtils.getConnection();

      // 3.创建语句
      String sql = "SELECT id,name,birthday,money FROM USER WHERE NAME =?";
      ps = conn.prepareStatement(sql);
      ps.setString(1, name);
      // 4.执行语句
      rs = ps.executeQuery();

      // 5.处理结果
      while (rs.next()) {
        System.out.println(rs.getObject("id") + "\t"
            + rs.getObject("name") + "\t"
            + rs.getObject("birthday") + "\t"
            + rs.getObject("money"));
      }
    } finally {
      JdbcUtils.free(rs, ps, conn);
    }
  }
}
