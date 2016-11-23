package com.sync.sax;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
public class Demo3 {

  public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
    // 1.创建解析工厂
    SAXParserFactory factory = SAXParserFactory.newInstance();
    // 2.得到解析器
    SAXParser sp = factory.newSAXParser();
    // 3.得到读取器
    XMLReader reader = sp.getXMLReader();
    // 4.设置内容处理器
    BeanListHandler handler = new BeanListHandler();
    reader.setContentHandler(handler);
    // 5.读取xml文档内容
    reader.parse("src/main/resources/xml/book.xml");
    System.out.println(handler.getBooks());
  }
}

class BeanListHandler extends DefaultHandler {

  private List<Book> list = new ArrayList<Book>();
  private Book book;
  private String currentTag;

  @Override public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    currentTag = qName;
    if ("书".equals(qName)) {
      book = new Book();
    }
  }

  @Override public void endElement(String uri, String localName, String qName) throws SAXException {
    if (qName.equals("书")) {
      list.add(book);
      book = null;
    }
    currentTag = null;
  }

  @Override public void characters(char[] ch, int start, int length) throws SAXException {
    String value = new String(ch, start, length);
    if ("书名".equals(currentTag)) {
      book.setName(value);
    } else if ("作者".equals(currentTag)) {
      book.setAuthor(value);
    } else if ("售价".equals(currentTag)) {
      book.setPrice(value);
    }
  }

  public List<Book> getBooks() {
    return list;
  }
}
