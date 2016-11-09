package com.sync.jdbc.spring;

import com.sync.jdbc.JdbcUtils;
import com.sync.jdbc.domain.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 * Created by Administrator on 2016/11/9 0009.
 */
public class JdbcTemplateTest {

  public static void main(String[] args) {
    User u = findUser("zhangsan");
    System.out.println(u);
  }

  static User findUser(String name) {
    JdbcTemplate jdbc = new JdbcTemplate(JdbcUtils.getDataSource());
    String sql = "select id,name,money,birthday from user where name=?";
    //Object[] args = new Object[]{name};
    User user = jdbc.queryForObject(sql, new RowMapper<User>() {
      public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User u = new User();
        u.setId(resultSet.getInt("id"));
        u.setName(resultSet.getString("name"));
        u.setMoney(resultSet.getFloat("money"));
        u.setBirthday(resultSet.getDate("birthday"));
        return u;
      }
    }, name);
    return user;
  }
}
