package com.sync.introspector;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.junit.Test;

/**
 * Created by Administrator on 2016/11/20 0020.
 */
public class Demo1 {

  /**
   * 得到Bean的所有属性
   *
   * @throws IntrospectionException
   */
  @Test
  public void test1() throws IntrospectionException {
    BeanInfo info = Introspector.getBeanInfo(Person.class, Object.class);// 得到Bean自己的属性
    PropertyDescriptor[] pds = info.getPropertyDescriptors();
    for (PropertyDescriptor pd : pds) {
      System.out.println(pd.getName());
    }
  }

  /**
   * 操纵Bean的指定属性
   *
   * @throws IntrospectionException
   */
  @Test
  public void test2() throws IntrospectionException, InvocationTargetException, IllegalAccessException {

    Person p = new Person();

    PropertyDescriptor pd = new PropertyDescriptor("age", Person.class);

    // 得到属性的写方法
    Method method = pd.getWriteMethod(); // setAge
    method.invoke(p, 45);

    // 获取属性的值
    method = pd.getReadMethod();

    Object age = method.invoke(p); // getAge

    System.out.println(age.toString());
  }

  /**
   * 获取当前操作的属性类型
   */
  @Test
  public void test3() throws IntrospectionException {

    Person p = new Person();

    PropertyDescriptor pd = new PropertyDescriptor("age",Person.class);

    System.out.println(pd.getPropertyType());

  }

}
