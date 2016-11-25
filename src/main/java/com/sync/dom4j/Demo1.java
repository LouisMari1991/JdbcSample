package com.sync.dom4j;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
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

    XMLWriter writer = new XMLWriter(new FileOutputStream("src/main/resources/xml/book.xml"), format);
    writer.write(document);
    writer.close();
  }

  /**
   * 在指定位置添加, 更改保存了所有孩子的list集合
   *
   * @throws Exception
   */
  @Test
  public void add2() throws Exception {
    SAXReader reader = new SAXReader();
    Document document = reader.read(new File("src/main/resources/xml/book.xml"));

    Element book = document.getRootElement().element("书");
    List list = book.elements(); // [书名, 售价, 作者]

    Element price = DocumentHelper.createElement("售价");
    price.setText("309元");

    list.add(2, price);

    OutputFormat format = OutputFormat.createPrettyPrint();
    format.setEncoding("UTF-8");

    XMLWriter writer = new XMLWriter(new FileOutputStream("src/main/resources/xml/book.xml"), format);
    writer.write(document);
    writer.close();
  }

  /**
   * 删除上面添加的节点
   * @throws Exception
   */
  @Test
  public void delete() throws Exception {
    SAXReader reader = new SAXReader();
    Document document = reader.read(new File("src/main/resources/xml/book.xml"));

    Element price = document.getRootElement().element("书").element("售价");
    price.getParent().remove(price);

    OutputFormat format = OutputFormat.createPrettyPrint();
    format.setEncoding("UTF-8");

    XMLWriter writer = new XMLWriter(new FileOutputStream("src/main/resources/xml/book.xml"), format);
    writer.write(document);
    writer.close();
  }

  @Test
  public void update() throws Exception {
    SAXReader reader = new SAXReader();
    Document document = reader.read(new File("src/main/resources/xml/book.xml"));

    Element book = (Element) document.getRootElement().elements("书").get(1);
    book.element("作者").setText("abc");

    OutputFormat format = OutputFormat.createPrettyPrint();
    format.setEncoding("UTF-8");

    XMLWriter writer = new XMLWriter(new FileOutputStream("src/main/resources/xml/book.xml"), format);
    writer.write(document);
    writer.close();
  }

}
