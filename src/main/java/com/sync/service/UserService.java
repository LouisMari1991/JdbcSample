package com.sync.service;

import com.sync.dao.DaoException;
import com.sync.dao.UserDao;
import com.sync.domain.User;

/**
 * Created by Administrator on 2016/11/2 0002.
 */
public class UserService {

  private UserDao userDao;

  public void regist(User user) {
    try{
      this.userDao.addUser(user);
    }catch (DaoException e){

    }

    //sendMail(user);
  }
}

