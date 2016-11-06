package com.sync.jdbc.dao;

import com.sync.jdbc.JdbcUtils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

/**
 * JDBC事物处理 保存点(SavePoint) 撤销一部分事物
 * Created by Administrator on 2016/11/6 0006.
 */
public class SavePointTest {

  public static void main(String[] args) throws SQLException {
    test();
  }

  static void test() throws SQLException {
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;
    Savepoint sp = null;
    try {
      conn = JdbcUtils.getConnection();
      conn.setAutoCommit(false);
      st = conn.createStatement();
      String sql = "UPDATE user SET money=money-10 WHERE id=1";
      st.executeUpdate(sql);
      sp = conn.setSavepoint();

      sql = "UPDATE user SET money=money-10 WHERE id=3";
      st.executeUpdate(sql);

      sql = "SELECT money FROM user WHERE id =2";
      rs = st.executeQuery(sql);
      float money = 0.0f;
      if (rs.next()) {
        money = rs.getFloat("money");
      }
      if (money > 300) {
        throw new RuntimeException("已经超过最大值!");
      }
      sql = "UPDATE user SET money=money+10 WHERE id=2";
      st.executeUpdate(sql);

      conn.commit();
    } catch (RuntimeException e) {
      if (conn != null && sp != null) {
        conn.rollback(sp);
        conn.commit();
      }
      throw e;
    } catch (SQLException e) {
      if (conn != null) {
        conn.rollback();
      }
      throw e;
    } finally {
      JdbcUtils.free(rs, st, conn);
    }
  }
}
