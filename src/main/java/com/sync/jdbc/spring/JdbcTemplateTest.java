package com.sync.jdbc.spring;

import com.sync.jdbc.JdbcUtils;
import com.sync.jdbc.domain.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 * Created by Administrator on 2016/11/9 0009.
 */
public class JdbcTemplateTest {

  static JdbcTemplate jdbc = new JdbcTemplate(JdbcUtils.getDataSource());

  public static void main(String[] args) {

    //User u = findUser("zhangsan");
    //System.out.println(u);

    //List<User> users = findUsers(10);
    //System.out.println(users);

    //int count = getUserCount();
    //System.out.println(count);

    //String name = getUserName();
    //System.out.println(name);

    //System.out.println(getData(1));

    //System.out.println(getDataList(12));

    User user =new User();
    user.setName("luo");
    user.setBirthday(new Date());
    user.setMoney(20.0f);
    System.out.println(addUser(user));

  }

  static int addUser(final User user) {
    int id = jdbc.execute(new ConnectionCallback<Integer>() {
      public Integer doInConnection(Connection connection) throws SQLException, DataAccessException {
        String sql = "insert into user(name,birthday,money) values(?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, user.getName());
        ps.setDate(2, new java.sql.Date(user.getBirthday().getTime()));
        ps.setFloat(3, user.getMoney());
        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next())
          user.setId(rs.getInt(1));
        return user.getId();
      }
    });
    return id;
  }

  static List<Map<String, Object>> getDataList(int id) {
    String sql = "select id,name,birthday,money FROM user WHERE id<?";
    List<Map<String, Object>> dataList = jdbc.queryForList(sql, id);
    return dataList;
  }

  static Map<String, Object> getData(int id) {
    String sql = "SELECT  id,name,birthday,money FROM  user where id = ?";
    Map<String, Object> datas = jdbc.queryForMap(sql, id);
    return datas;
  }

  static String getUserName() {
    String sql = "SELECT name FROM user  WHERE id=?";
    String name = jdbc.queryForObject(sql, String.class, 1);
    return name;
  }

  static int getUserCount() {
    String sql = "select count(*) from user";
    int count = jdbc.queryForObject(sql, Integer.class);
    return count;
  }

  static List<User> findUsers(int id) {
    String sql = "select id,name,money,birthday from user where id<?";
    Object[] args = new Object[] {id};
    List<User> users = jdbc.query(sql, args, new BeanPropertyRowMapper<User>(User.class));
    return users;
  }

  static User findUser(String name) {
    String sql = "select id,name,money,birthday from user where name=?";
    Object[] args = new Object[] {name};
    User user = jdbc.queryForObject(sql, args, new BeanPropertyRowMapper<User>(User.class));
    return user;
  }

  static User findUser1(String name) {
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
