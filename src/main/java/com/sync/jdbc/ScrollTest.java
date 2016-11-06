package com.sync.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 可滚动的结果集
 *
 * Created by Administrator on 2016/11/6 0006.
 */
public class ScrollTest {

  public static void main(String[] args) throws SQLException {
    scroll();
  }

  static void scroll() throws SQLException {
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;
    try {
      conn = JdbcUtils.getConnection();
      st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
      //rs = st.executeQuery("SELECT id,name,birthday,money FROM USER WHERE id < 20");
      rs = st.executeQuery("SELECT id,name,birthday,money FROM user LIMIT 150,10"); // MySql分页，从150条开始，查询10条 (SQLSERVER:TOP,ORACLE:ROWNUMBER)
      while (rs.next()) {
        System.out.println(rs.getObject("id") + "\t"
            + rs.getObject("name") + "\t"
            + rs.getObject("birthday") + "\t"
            + rs.getObject("money"));
      }
      System.out.println("-----------------------------------");
      rs.absolute(5);
       if (rs.previous()) {
        System.out.println(rs.getObject("id") + "\t"
            + rs.getObject("name") + "\t"
            + rs.getObject("birthday") + "\t"
            + rs.getObject("money"));
      }
      //if (rs.absolute(10))

    } finally {
      JdbcUtils.free(rs, st, conn);
    }
  }
}
