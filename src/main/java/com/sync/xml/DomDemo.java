package com.sync.xml;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
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


}
