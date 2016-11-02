package com.sync.dao.impl;

import com.sync.dao.DaoException;
import com.sync.dao.UserDao;
import com.sync.domain.User;
import com.sync.jdbc.JdbcUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    return null;
  }

  public void update(User user) {

  }

  public void delete(User user) {

  }

  public User findUser(String loginName, String password) {
    return null;
  }
}
