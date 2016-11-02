package com.sync.dao;

import com.sync.domain.User;

/**
 * Created by Administrator on 2016/11/2 0002.
 */
public interface UserDao {

  void addUser(User user);

  User getUser(int userId);

  void update(User user);

  void delete(User user);

  User findUser(String loginName, String password);

}
