package com.sync.xml;

import java.io.FileOutputStream;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
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
    if (node instanceof Element){
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
    TransformerFactory tfFactory =  TransformerFactory.newInstance();
    Transformer tf = tfFactory.newTransformer();
    tf.transform(new DOMSource(document), new StreamResult(new FileOutputStream("src/main/resources/xml/book.xml")));
  }



}
