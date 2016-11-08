package com.sync.jdbc.dao.refactor;

import com.sync.jdbc.domain.User;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Administrator on 2016/11/8 0008.
 */
public class UserDaoImpl2 {

  MyDaoTemplate template = new MyDaoTemplate();

  public User findUser(String loginName, String password) {
    String sql = "SELECT id,name,birthday,money from user where name=?";
    User user = this.template.find(sql, new RowMapper<User>() {
      public User mapRow(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setMoney(rs.getFloat("money"));
        user.setBirthday(rs.getDate("birthday"));
        return user;
      }
    }, loginName);
    return user;
  }

  public String findUserName(int id){
    String sql = "select name from user where id=?";
    String name = this.template.find(sql, new RowMapper<String>() {
      public String mapRow(ResultSet rs) throws SQLException {
        return rs.getString("name");
      }
    }, id);
    return name;
  }

  //public void update(User user) {
  //  String sql = "update user set name=?,birthday=?,money=? where id=?";
  //  super.update(sql, user.getName(), user.getBirthday(), user.getMoney(), user.getId());
  //}
  //
  //public void delete(User user) {
  //  String sql = "DELETE FROM user WHERE id=?";
  //  Object[] args = new Object[] {user.getId()};
  //  super.update(sql, args);
  //}
  //
  //protected User rowMapper(ResultSet rs) throws SQLException {
  //  User user = new User();
  //  user.setId(rs.getInt("id"));
  //  user.setName(rs.getString("name"));
  //  user.setMoney(rs.getFloat("money"));
  //  user.setBirthday(rs.getDate("birthday"));
  //  return user;
  //}
}
