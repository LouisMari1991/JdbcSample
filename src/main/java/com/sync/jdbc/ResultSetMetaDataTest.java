package com.sync.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 利用结果元数据集将查询结果封装为: Map， 多条数据可以返回List
 *
 * Created by Administrator on 2016/11/6 0006.
 */
public class ResultSetMetaDataTest {

  public static void main(String[] args) throws SQLException {
    String sql = "select id,name as n from user";
    //Map<Integer, Map<String, Object>> result = read(sql);
    //System.out.println(result);
    List<Map<String, Object>> datas = read2(sql);
    System.out.println(datas);
  }

  static Map<Integer, Map<String, Object>> read(String sql) throws SQLException {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
      conn = JdbcUtils.getConnection();
      ps = conn.prepareStatement(sql);
      rs = ps.executeQuery();
      ResultSetMetaData rsmd = rs.getMetaData();
      int count = rsmd.getColumnCount();
      String[] colName = new String[count];
      for (int i = 1; i <= count; i++) {
        //System.out.print(rsmd.getColumnClassName(i)+"\t");
        //System.out.print(rsmd.getColumnName(i)+"\t");
        //System.out.println(rsmd.getColumnLabel(i));
        colName[i - 1] = rsmd.getColumnLabel(i);
      }
      int num = 0;
      Map<Integer, Map<String, Object>> result = new HashMap<Integer, Map<String, Object>>();
      Map<String, Object> data = null;
      while (rs.next()) {
        data = new HashMap<String, Object>();
        for (int i = 0; i < colName.length; i++) {
          data.put(colName[i], rs.getObject(colName[i]));
        }
        result.put(num, data);
        num++;
      }
      return result;
    } finally {
      JdbcUtils.free(rs, ps, conn);
    }
  }

  static List<Map<String, Object>> read2(String sql) throws SQLException {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
      conn = JdbcUtils.getConnection();
      ps = conn.prepareStatement(sql);
      rs = ps.executeQuery();
      ResultSetMetaData rsmd = rs.getMetaData();
      int count = rsmd.getColumnCount();
      String[] colName = new String[count];
      for (int i = 1; i <= count; i++) {
        colName[i - 1] = rsmd.getColumnLabel(i);
      }
      List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
      Map<String, Object> data = null;
      while (rs.next()) {
        data = new HashMap<String, Object>();
        for (int i = 0; i < colName.length; i++) {
          data.put(colName[i], rs.getObject(colName[i]));
        }
        datas.add(data);
      }
      return datas;
    } finally {
      JdbcUtils.free(rs, ps, conn);
    }
  }
}
