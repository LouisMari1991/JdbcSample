package com.sync.jdbc;

import com.sync.jdbc.domain.User;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2016/11/6 0006.
 */
public class ReflectTest {

  public static void main(String[] args) throws Exception {
    Class clazz = User.class;
    //clazz = Bean.class;
    Object obj = create(clazz);
    System.out.println(obj);

    invok1(obj, "showName");
    System.out.println("----------------------------------");
    field(clazz);

  }

  static Object create(Class clazz) throws Exception {
    Constructor con = clazz.getConstructor(String.class);
    Object obj = con.newInstance("test name");
    return obj;
  }

  static void invok1(Object obj, String methodName) throws Exception {
    Method[] ms = obj.getClass().getDeclaredMethods();
    ms = obj.getClass().getMethods();
    for (Method m : ms) {
      //System.out.println(m.getName());
      if (methodName.equals(m.getName())) {
        m.invoke(obj, null);
      }
    }
  }

  static void field(Class clazz) throws Exception {
    Field[] fs = clazz.getDeclaredFields();
    //fs = clazz.getFields();
    for (Field f : fs) {
      System.out.println(f);
    }
  }

  static void anoo(Class clazz) throws Exception {
    Annotation[] as = clazz.getAnnotations();

  }

}
