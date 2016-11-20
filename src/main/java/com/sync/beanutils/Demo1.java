package com.sync.beanutils;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
import org.junit.Test;
import org.springframework.util.StringUtils;

/**
 * 使用BeanUtils操纵bean的属性
 * Created by Administrator on 2016/11/20 0020.
 */
public class Demo1 {

  @Test
  public void test1() throws InvocationTargetException, IllegalAccessException {
    Person p = new Person();
    BeanUtils.setProperty(p, "name", "zhangsan");
    System.out.println(p.getName());
  }

  /**
   * BeanUtils默认只支持基本数据类型转换
   *
   * @throws InvocationTargetException
   * @throws IllegalAccessException
   */
  @Test
  public void test2() throws InvocationTargetException, IllegalAccessException {
    String name = "lisi";
    String password = "123";
    String age = "20";
    String birthday = "";

    Person p = new Person();
    BeanUtils.setProperty(p, "name", name);
    BeanUtils.setProperty(p, "password", password);
    BeanUtils.setProperty(p, "age", age);// 默认只支持8中基本数据类型
    //BeanUtils.setProperty(p,"birthday",birthday); // org.apache.commons.beanutils.converters.DateConverter toDate

    System.out.println(p);
  }

  @Test
  public void test3() throws InvocationTargetException, IllegalAccessException {
    String name = "lisi";
    String password = "123";
    String age = "20";
    String birthday = "1980-11-11";

    // 为了让日期能赋到bean的属性上，给BeanUtils注册一个日期转换器
    ConvertUtils.register(new Converter() {
      public Date convert(Class type, Object value) {
        if (StringUtils.isEmpty(value)) {
          return null;
        }
        if (!(value instanceof String)) {
          throw new ConversionException("只支持String类型的转换");
        }
        String str = (String) value;
        if (str.trim().equals("")) {
          return null;
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
          return df.parse(str);
        } catch (ParseException e) {
          throw new ConversionException("日期转换错误", e); // 异常链不能断
        }
      }
    }, Date.class);

    Person p = new Person();
    BeanUtils.setProperty(p, "name", name);
    BeanUtils.setProperty(p, "password", password);
    BeanUtils.setProperty(p, "age", age);// 默认只支持8中基本数据类型
    BeanUtils.setProperty(p, "birthday", birthday); // org.apache.commons.beanutils.converters.DateConverter toDate

    System.out.println(p);
  }

  @Test
  public void test4() throws InvocationTargetException, IllegalAccessException {
    String name = "lisi";
    String password = "123";
    String age = "20";
    String birthday = "";

    ConvertUtils.register(new DateLocaleConverter(), Date.class);
    Person p = new Person();

    BeanUtils.setProperty(p, "name", name);
    BeanUtils.setProperty(p, "password", password);
    BeanUtils.setProperty(p, "age", age);// 默认只支持8中基本数据类型
    BeanUtils.setProperty(p, "birthday", birthday); // org.apache.commons.beanutils.converters.DateConverter toDate

    System.out.println(p);
  }

  @Test
  public void test5() throws InvocationTargetException, IllegalAccessException {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("name", "wangwu");
    map.put("password", "321");
    map.put("age",15);
    map.put("birthday","1980-11-11");

    ConvertUtils.register(new DateLocaleConverter(), Date.class);
    Person p = new Person();
    BeanUtils.populate(p, map);

    System.out.println(p);
  }

}
