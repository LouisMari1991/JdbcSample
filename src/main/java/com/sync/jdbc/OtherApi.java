package com.sync.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Administrator on 2016/11/6 0006.
 */
public class OtherApi {

  public static void main(String[] args) throws SQLException, InterruptedException {
    //int id = create();
    //System.out.printf("id=" + id);
    read();
  }

  /**
   * 插入返回主键 Statement.RETURN_GENERATED_KEYS
   *
   * @return id 主键id
   * @throws SQLException
   */
  static int create() throws SQLException {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
      conn = JdbcUtils.getConnection();
      String sql = "INSERT INTO user(name,birthday,money) VALUE('name gk','1987-01-01',400)";
      ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      ps.executeUpdate();

      rs = ps.getGeneratedKeys();
      int id = 0;
      if (rs.next()) {
        id = rs.getInt(1);
      }
      return id;
    } finally {
      JdbcUtils.free(rs, ps, conn);
    }
  }

  /**
   * 可更新的结果集 ResultSet.CONCUR_UPDATABLE
   * rs.updateFloat("money", 300.0f);
   * rs.updateRow();
   *
   * @throws SQLException
   */
  static void read() throws SQLException, InterruptedException {
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;
    try {
      conn = JdbcUtils.getConnection();
      st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
      rs = st.executeQuery("SELECT id,name,birthday,money FROM USER WHERE id<5");

      while (rs.next()) {
        int id = rs.getInt("id");
        System.out.println("show " + id + " ....");
        Thread.sleep(10000);
        System.out.println(rs.getObject("id") + "\t"
            + rs.getObject("name") + "\t"
            + rs.getObject("birthday") + "\t"
            + rs.getObject("money"));
        String name = rs.getString("name");
        if ("lisi".equals(name)) {
          rs.updateFloat("money", 300.0f);
          rs.updateRow();
        }
      }
    } finally {
      JdbcUtils.free(rs, st, conn);
    }
  }
}
