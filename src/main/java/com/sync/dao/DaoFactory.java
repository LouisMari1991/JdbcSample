package com.sync.dao;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Administrator on 2016/11/3 0003.
 */
public class DaoFactory {

  private static UserDao userDao = null;
  private static DaoFactory instance = new DaoFactory();

  private DaoFactory() {
    try {
      Properties prop = new Properties();
      InputStream inputStream = DaoFactory.class.getClassLoader().getResourceAsStream("daoconfig.properties");
      prop.load(inputStream);
      String userDaoClass = prop.getProperty("userDaoClass");
      userDao = (UserDao) Class.forName(userDaoClass).newInstance();
    } catch (Exception e) {
      throw new ExceptionInInitializerError(e);
    }
  }

  public static DaoFactory getInstance() {
    return instance;
  }

  public UserDao getUserDao() {

    return userDao;
  }
}
