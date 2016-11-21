package com.sync.xml;

import java.io.FileOutputStream;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 使用dom方式对xml文档进行crud
 * Created by Administrator on 2016/11/21 0021.
 */
public class DomDemo {

  @Test
  public void read1() throws ParserConfigurationException, IOException, SAXException {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document document = builder.parse("src/main/resources/xml/book.xml");

    NodeList list = document.getElementsByTagName("书名");
    Node node = list.item(1);
    String content = node.getTextContent();
    System.out.println(content);
  }

  /**
   * 打印xml文档中所有的标签
   *
   * @throws ParserConfigurationException
   * @throws IOException
   * @throws SAXException
   */
  @Test
  public void read2() throws ParserConfigurationException, IOException, SAXException {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document document = builder.parse("src/main/resources/xml/book.xml");

    // 得到根节点
    Node root = document.getElementsByTagName("书架").item(0);
    // 便利所有节点
    list(root);
  }

  private void list(Node node) {
    if (node instanceof Element) {
      // 只打印标签
      System.out.println(node.getNodeName());
    }
    NodeList list = node.getChildNodes();
    for (int i = 0; i < list.getLength(); i++) {
      Node child = list.item(i);
      list(child);
    }
  }

  /**
   * 得到xml文档中标签属性的值
   *
   * @throws ParserConfigurationException
   * @throws IOException
   * @throws SAXException
   */
  @Test
  public void read3() throws ParserConfigurationException, IOException, SAXException {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document document = builder.parse("src/main/resources/xml/book.xml");

    Element bookName = (Element) document.getElementsByTagName("书名").item(0);
    String value = bookName.getAttribute("name");
    System.out.println(value);
  }

  /**
   * 向xml文档中添加节点：<售价>29.9</售价>
   *
   * @throws ParserConfigurationException
   * @throws IOException
   * @throws SAXException
   */
  @Test
  public void add() throws ParserConfigurationException, IOException, SAXException, TransformerException {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document document = builder.parse("src/main/resources/xml/book.xml");

    // 创建节点
    Element price = document.createElement("售价");
    price.setTextContent("29.9");

    // 把创建的节点挂到第一本书上
    Element book = (Element) document.getElementsByTagName("书").item(0);
    book.appendChild(price);

    // 把更新后的内存写回到xml文档
    TransformerFactory tfFactory = TransformerFactory.newInstance();
    Transformer tf = tfFactory.newTransformer();
    tf.transform(new DOMSource(document), new StreamResult(new FileOutputStream("src/main/resources/xml/book.xml")));
  }

  /**
   * 向xml文档中指定位置上添加节点:<售价>59.9</售价>
   *
   * @throws ParserConfigurationException
   * @throws IOException
   * @throws SAXException
   */
  @Test
  public void add2() throws ParserConfigurationException, IOException, SAXException, TransformerException {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document document = builder.parse("src/main/resources/xml/book.xml");

    // 创建节点
    Element price = document.createElement("售价");
    price.setTextContent("29.9");

    // 得到参考节点
    Element refNode = (Element) document.getElementsByTagName("售价").item(0);

    // 的药要挂崽的节点
    Element book = (Element) document.getElementsByTagName("书").item(0);

    // 往book节点的指定位置插崽
    book.insertBefore(price, refNode);

    // 把更新后的内存写回到xml文档
    TransformerFactory tfFactory = TransformerFactory.newInstance();
    Transformer tf = tfFactory.newTransformer();
    tf.transform(new DOMSource(document), new StreamResult(new FileOutputStream("src/main/resources/xml/book.xml")));
  }

  /**
   * 向xml文档节点上添加属性
   *
   * @throws ParserConfigurationException
   * @throws IOException
   * @throws SAXException
   */
  @Test
  public void add3() throws ParserConfigurationException, IOException, SAXException, TransformerException {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document document = builder.parse("src/main/resources/xml/book.xml");

    Element bookName = (Element) document.getElementsByTagName("书名").item(0);
    // 创建属性
    bookName.setAttribute("name","zzz");

    // 把更新后的内存写回到xml文档
    TransformerFactory tfFactory = TransformerFactory.newInstance();
    Transformer tf = tfFactory.newTransformer();
    tf.transform(new DOMSource(document), new StreamResult(new FileOutputStream("src/main/resources/xml/book.xml")));
  }

  @Test
  public void delete1() throws ParserConfigurationException, IOException, SAXException, TransformerException {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document document = builder.parse("src/main/resources/xml/book.xml");

    // 得到要删除的节点
    Element e = (Element) document.getElementsByTagName("售价").item(0);

    // 得到要删除的节点的爸爸
    Element book = (Element) document.getElementsByTagName("书").item(0);

    // 爸爸再删崽
    book.removeChild(e);

    // 把更新后的内存写回到xml文档
    TransformerFactory tfFactory = TransformerFactory.newInstance();
    Transformer tf = tfFactory.newTransformer();
    tf.transform(new DOMSource(document), new StreamResult(new FileOutputStream("src/main/resources/xml/book.xml")));
  }

  @Test
  public void delete2() throws ParserConfigurationException, IOException, SAXException, TransformerException {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document document = builder.parse("src/main/resources/xml/book.xml");

    // 得到要删除的节点
    Element e = (Element) document.getElementsByTagName("售价").item(0);

    // 得到爸爸再删除自己
    e.getParentNode().removeChild(e);

    // 把更新后的内存写回到xml文档
    TransformerFactory tfFactory = TransformerFactory.newInstance();
    Transformer tf = tfFactory.newTransformer();
    tf.transform(new DOMSource(document), new StreamResult(new FileOutputStream("src/main/resources/xml/book.xml")));
  }

  /**
   * 更新售价
   * @throws ParserConfigurationException
   * @throws IOException
   * @throws SAXException
   * @throws TransformerException
   */
  @Test
  public void update() throws ParserConfigurationException, IOException, SAXException, TransformerException {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document document = builder.parse("src/main/resources/xml/book.xml");

    Element e = (Element) document.getElementsByTagName("售价").item(0);
    e.setTextContent("109元");

    // 把更新后的内存写回到xml文档
    TransformerFactory tfFactory = TransformerFactory.newInstance();
    Transformer tf = tfFactory.newTransformer();
    tf.transform(new DOMSource(document), new StreamResult(new FileOutputStream("src/main/resources/xml/book.xml")));
  }

}
