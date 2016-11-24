package com.sync.sax;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by Administrator on 2016/11/23 0023.
 */
public class Demo2 {

  public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
    // 1.创建解析工厂
    SAXParserFactory factory = SAXParserFactory.newInstance();
    // 2.得到解析器
    SAXParser sp = factory.newSAXParser();
    // 3.得到读取器
    XMLReader reader = sp.getXMLReader();
    // 4.设置内容处理器
    reader.setContentHandler(new TagValueHandler());
    // 5.读取xml文档内容
    reader.parse("src/main/resources/xml/book.xml");
  }
}

/**
 * 获取指定的标签的值
 */
class TagValueHandler extends DefaultHandler {

  private String currentTag; // 记住当前解析到的是什么标签
  private int needNumber = 1; // 记住想获取第几个作者标签的值
  private int currentNumber; // 记住当前获取到的作者的值

  @Override public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    currentTag = qName;
    if (currentTag.equals("作者")) {
      currentNumber++;
    }
  }

  @Override public void endElement(String uri, String localName, String qName) throws SAXException {
    currentTag = null;
  }

  @Override public void characters(char[] ch, int start, int length) throws SAXException {
    if ("作者".equals(currentTag) && currentNumber == needNumber) {
      System.out.println(new String(ch, start, length));
    }
  }
}

