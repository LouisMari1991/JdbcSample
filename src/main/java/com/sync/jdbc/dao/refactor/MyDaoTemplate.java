package com.sync.jdbc.dao.refactor;

import com.sync.jdbc.JdbcUtils;
import com.sync.jdbc.dao.DaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Administrator on 2016/11/8 0008.
 */
public class MyDaoTemplate {

  protected <T> T find(String sql, RowMapper<T> rowMapper, Object... args) {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
      conn = JdbcUtils.getConnection();
      ps = conn.prepareStatement(sql);
      for (int i = 0; i < args.length; i++) {
        ps.setObject(i + 1, args[i]);
      }
      rs = ps.executeQuery();
      T t = null;
      if (rs.next()) {
        t = rowMapper.mapRow(rs);
      }
      return t;
    } catch (SQLException e) {
      throw new DaoException(e.getMessage(), e);
    } finally {
      JdbcUtils.free(rs, ps, conn);
    }
  }

  protected int update(String sql, Object... args) {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
      conn = JdbcUtils.getConnection();
      ps = conn.prepareStatement(sql);
      for (int i = 0; i < args.length; i++) {
        ps.setObject(i + 1, args[i]);
      }
      return ps.executeUpdate();
    } catch (SQLException e) {
      throw new DaoException(e.getMessage(), e);
    } finally {
      JdbcUtils.free(rs, ps, conn);
    }
  }
}
