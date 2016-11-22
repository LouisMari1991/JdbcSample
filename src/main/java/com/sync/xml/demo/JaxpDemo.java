package com.sync.xml.demo;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * Created by Administrator on 2016/11/21 0021.
 */
public class JaxpDemo {

  public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {

    // 1.创建工厂
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

    // 2.得到dom解析器
    DocumentBuilder builder = factory.newDocumentBuilder();

    // 3.解析xml文档,得到代表文档的document
    Document document = builder.parse("src/main/resources/xml/book.xml");
  }
}
