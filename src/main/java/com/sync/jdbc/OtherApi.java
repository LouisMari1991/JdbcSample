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

  public static void main(String[] args) throws SQLException {
    int id = create();
    System.out.printf("id=" + id);
  }

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
}
