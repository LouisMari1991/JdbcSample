package com.sync.dao.impl;

import com.sync.dao.DaoException;
import com.sync.dao.UserDao;
import com.sync.domain.User;
import com.sync.jdbc.JdbcUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Administrator on 2016/11/2 0002.
 */
public class UserDaoJdbcImpl implements UserDao {

  public void addUser(User user) {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
      conn = JdbcUtils.getConnection();
      String sql = "insert into user(name,birthday,money) values(?,?,?)";
      ps = conn.prepareStatement(sql);
      ps.setString(1, user.getName());
      ps.setDate(2, new java.sql.Date(user.getBirthday().getTime()));
      ps.setFloat(3, user.getMoney());
      ps.executeUpdate();
    } catch (SQLException e) {
      throw new DaoException(e.getMessage(), e);
    } finally {
      JdbcUtils.free(rs, ps, conn);
    }
  }

  public User getUser(int userId) {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    User user = null;
    try {
      conn = JdbcUtils.getConnection();
      String sql = "SELECT id,name,birthday,money from user where id=?";
      ps = conn.prepareStatement(sql);
      ps.setInt(1, userId);
      rs = ps.executeQuery();
      while (rs.next()) {
        user = mappingUser(rs);
      }
    } catch (SQLException e) {
      throw new DaoException(e.getMessage(), e);
    } finally {
      JdbcUtils.free(rs, ps, conn);
    }
    return user;
  }

  public void update(User user) {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
      conn = JdbcUtils.getConnection();
      String sql = "UPDATE user SET birthday=?,money=? WHERE id=? AND name=?";
      ps = conn.prepareStatement(sql);
      ps.setDate(1,new java.sql.Date(user.getBirthday().getTime()));
      ps.setFloat(2, user.getMoney());
      ps.setInt(3, user.getId());
      ps.setString(4, user.getName());
      ps.executeUpdate();
      while (rs.next()) {
        user = mappingUser(rs);
      }
    } catch (SQLException e) {
      throw new DaoException(e.getMessage(), e);
    } finally {
      JdbcUtils.free(rs, ps, conn);
    }
  }

  public void delete(User user) {
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;
    try {
      conn = JdbcUtils.getConnection();
      st = conn.createStatement();
      String sql = "DELETE FROM user WHERE id=" + user.getId();
      st.executeUpdate(sql);
    } catch (SQLException e) {
      throw new DaoException(e.getMessage(), e);
    } finally {
      JdbcUtils.free(rs, st, conn);
    }
  }

  public User findUser(String loginName, String password) {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    User user = null;
    try {
      conn = JdbcUtils.getConnection();
      String sql = "SELECT id,name,birthday,money from user where name=?";
      ps = conn.prepareStatement(sql);
      ps.setString(1, loginName);
      rs = ps.executeQuery();
      while (rs.next()) {
        user = mappingUser(rs);
      }
    } catch (SQLException e) {
      throw new DaoException(e.getMessage(), e);
    } finally {
      JdbcUtils.free(rs, ps, conn);
    }
    return user;
  }

  private User mappingUser(ResultSet rs) throws SQLException {
    User user = new User();
    user.setId(rs.getInt("id"));
    user.setName(rs.getString("name"));
    user.setMoney(rs.getFloat("money"));
    user.setBirthday(rs.getDate("birthday"));
    return user;
  }

}
