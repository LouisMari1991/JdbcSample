package com.sync.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

/**
 * 数据库的元数据信息
 *
 * Created by Administrator on 2016/11/6 0006.
 */
public class DBMD {

  public static void main(String[] args) throws SQLException {
    Connection conn = JdbcUtils.getConnection();
    DatabaseMetaData dbmd = conn.getMetaData();
    System.out.println("db name:" + dbmd.getDatabaseProductName());
    System.out.println("tx:"+dbmd.supportsTransactions());
    conn.close();
  }
}
