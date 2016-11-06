package com.sync.jdbc;

import com.sync.jdbc.domain.User;
import java.lang.reflect.Constructor;

/**
 * Created by Administrator on 2016/11/6 0006.
 */
public class ReflectTest {

  public static void main(String[] args) throws Exception {
    Class clazz = User.class;
    clazz = Bean.class;
    Object obj = create(clazz);
    System.out.println(obj);
  }

  static Object create(Class clazz) throws Exception {
    Constructor con = clazz.getConstructor(String.class);
    Object obj = con.newInstance("test name");
    return obj;
  }
}
