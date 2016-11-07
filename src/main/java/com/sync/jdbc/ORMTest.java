package com.sync.jdbc;

import com.sync.jdbc.domain.User;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * 利用Java反射技术将查询结果封装为对象
 * 对象关系映射:ORM
 *
 * Created by Administrator on 2016/11/7 0007.
 */
public class ORMTest {

  public static void main(String[] args) throws Exception {
    String sql = "select id as Id,name as Name, birthday as Birthday, money as Money  from user where id=1";
    User user = getBean(sql, User.class);
    Bean bean = getBean(sql, Bean.class);
    System.out.println(user);
    System.out.println(bean);

    sql = "select id as Id,name as Name, birthday as Birthday, money as Money  from user";
    List<User> users = getBeans(sql, User.class);
    System.out.println(users);
  }

  static <T> T getBean(String sql, Class<T> clazz) throws Exception {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
      conn = JdbcUtils.getConnection();
      ps = conn.prepareStatement(sql);
      rs = ps.executeQuery();
      ResultSetMetaData rsmd = rs.getMetaData();
      int count = rsmd.getColumnCount();
      String[] colNames = new String[count];
      for (int i = 1; i <= count; i++) {
        colNames[i - 1] = rsmd.getColumnLabel(i);
      }

      T t = null;
      Method[] ms = clazz.getMethods();
      if (rs.next()) {
        t = clazz.newInstance();
        for (int i = 0; i < colNames.length; i++) {
          String colName = colNames[i];
          String methodName = "set" + colName;
          for (Method m : ms) {
            if (methodName.equals(m.getName())) {
              m.invoke(t, rs.getObject(colName));
            }
          }
        }
      }
      return t;
    } finally {
      JdbcUtils.free(rs, ps, conn);
    }
  }

  static <T> List<T> getBeans(String sql, Class<T> clazz) throws Exception {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
      conn = JdbcUtils.getConnection();
      ps = conn.prepareStatement(sql);
      rs = ps.executeQuery();
      ResultSetMetaData rsmd = rs.getMetaData();
      int count = rsmd.getColumnCount();
      String[] colNames = new String[count];
      for (int i = 1; i <= count; i++) {
        colNames[i - 1] = rsmd.getColumnLabel(i);
      }
      List<T> datas = new ArrayList<T>();
      T t;
      Method[] ms = clazz.getMethods();
      while (rs.next()) {
        t = clazz.newInstance();
        for (int i = 0; i < colNames.length; i++) {
          String colName = colNames[i];
          String methodName = "set" + colName;
          for (Method m : ms) {
            if (methodName.equals(m.getName())) {
              m.invoke(t, rs.getObject(colName));
            }
          }
        }
        datas.add(t);
      }
      return datas;
    } finally {
      JdbcUtils.free(rs, ps, conn);
    }
  }

}
