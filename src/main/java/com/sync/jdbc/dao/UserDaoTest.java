package com.sync.jdbc.dao;

import com.sync.jdbc.dao.refactor.UserDaoImpl;
import com.sync.jdbc.dao.refactor.UserDaoImpl2;
import com.sync.jdbc.domain.User;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/3 0003.
 */
public class UserDaoTest {

  public static void main(String[] args) {

    UserDao userDao = DaoFactory.getInstance().getUserDao();

    User user = new User();
    user.setBirthday(new Date());
    user.setName("dao impl test 1");
    user.setMoney(1000.0f);
    userDao.addUser(user);

    System.out.println(user.getId());

    UserDaoImpl2 userDaoimpl2 = new UserDaoImpl2();
    User u = userDaoimpl2.findUser(user.getName(), null);
    System.out.println(u);

    String name = userDaoimpl2.findUserName(user.getId());
    System.out.println("name="+name);


    //
    //User u = userDao.findUser("dao name1", null);
    //System.out.println(u.getId());

    //User u = userDao.getUser(6);
    //u.setMoney(2000.0f);
    //userDao.update(u);

    //User u1 = userDao.getUser(6);
    //userDao.delete(u1);
  }

}
