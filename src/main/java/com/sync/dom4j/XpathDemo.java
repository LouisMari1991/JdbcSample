package com.sync.dom4j;

import java.io.File;
import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.junit.Test;

/**
 * 应用xpath提取xml文档的数据
 *
 * Created by Administrator on 2016/11/26 0026.
 */
public class XpathDemo {

  @Test
  public void find() throws Exception {
    SAXReader reader = new SAXReader();
    Document document = reader.read(new File("src/main/resources/xml/book.xml"));

    // 默认获取第一个
    String value = document.selectSingleNode("//作者").getText();

    System.out.println(value);
  }

  @Test
  public void findUser() throws Exception {

    String username = "aaa";
    String password = "123";

    SAXReader reader = new SAXReader();
    Document document = reader.read(new File("src/main/resources/xml/users.xml"));

    // 根据帐号和密码找到用户
    Node node = document.selectSingleNode("//user[@username='" + username + "' and @password='" + password + "']");
    System.out.println(node);
  }
}
