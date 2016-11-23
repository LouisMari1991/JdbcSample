package com.sync.dom4j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

/**
 * Created by Administrator on 2016/11/23 0023.
 */
public class Demo1 {

  @Test
  public void read() throws Exception {
    SAXReader reader = new SAXReader();
    Document document = reader.read(new File("src/main/resources/xml/book.xml"));
    Element root = document.getRootElement();
    Element book = (Element) root.elements("书").get(1);
    String value = book.element("书名").getText();
    System.out.println(value);
  }

  /**
   * 读取属性
   *
   * @throws Exception
   */
  @Test
  public void readAttr() throws Exception {
    SAXReader reader = new SAXReader();
    Document document = reader.read(new File("src/main/resources/xml/book.xml"));
    Element root = document.getRootElement();
    Element book = (Element) root.elements("书").get(1);
    //String value = book.element("书名").attribute("name").getValue();
    String value = book.element("书名").attributeValue("name");
    System.out.println(value);
  }

  /**
   * 添加售价
   *
   * @throws Exception
   */
  @Test
  public void add() throws Exception {
    SAXReader reader = new SAXReader();
    Document document = reader.read(new File("src/main/resources/xml/book.xml"));

    Element book = document.getRootElement().element("书");
    book.addElement("售价").setText("209元");

    OutputFormat format = OutputFormat.createPrettyPrint();
    format.setEncoding("UTF-8");

    XMLWriter writer = new XMLWriter(new FileOutputStream("src/main/resources/xml/book.xml"),format);
    writer.write(document);
    writer.close();
  }
}
