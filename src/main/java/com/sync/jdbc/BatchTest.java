package com.sync.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Administrator on 2016/11/6 0006.
 */
public class BatchTest {

  public static void main(String[] args) throws SQLException {
    createBatch();
  }

  /**
   * 插入多条数据
   * 批处理  ps.addBatch();  ps.executeBatch();
   *
   * @throws SQLException
   */
  static void createBatch() throws SQLException {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
      conn = JdbcUtils.getConnection();
      String sql = "INSERT INTO user(name,birthday,money) VALUE(?,?,?)";
      ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      for (int i = 0; i < 1000; i++) {
        ps.setString(1, "name " + i);
        ps.setDate(2, new Date(System.currentTimeMillis()));
        ps.setFloat(3, 100f + i);
        ps.addBatch();
      }
      int[] is = ps.executeBatch();
    } finally {
      JdbcUtils.free(rs, ps, conn);
    }
  }
}
