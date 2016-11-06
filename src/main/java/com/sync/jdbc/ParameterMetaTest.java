package com.sync.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 参数元数据信息
 *
 * Created by Administrator on 2016/11/6 0006.
 */
public class ParameterMetaTest {

  public static void main(String[] args) throws SQLException {
    String sql = "select * from user where name=? and birthday<? and money>?";
    Object[] params = new Object[] {"lisi", new Date(System.currentTimeMillis()), 100.0f};
    read(sql, params);
  }

  static void read(String sql, Object[] params) throws SQLException {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
      conn = JdbcUtils.getConnection();
      ps = conn.prepareStatement(sql);
      ParameterMetaData pmd = ps.getParameterMetaData();
      int count = pmd.getParameterCount();
      for (int i = 1; i <= count; i++) {
        //System.out.print(pmd.getParameterClassName(i) + "\t");
        //System.out.print(pmd.getParameterType(i) + "\t");
        //System.out.println(pmd.getParameterTypeName(i) + "\t");
        ps.setObject(i, params[i - 1]);
      }
      rs = ps.executeQuery();
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
