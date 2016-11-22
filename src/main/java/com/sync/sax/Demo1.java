package com.sync.sax;

import java.io.IOException;
import java.net.URLConnection;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

/**
 * Created by Administrator on 2016/11/22 0022.
 */
public class Demo1 {

  public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
    // 1.创建解析工厂
    SAXParserFactory factory = SAXParserFactory.newInstance();
    // 2.得到解析器
    SAXParser sp = factory.newSAXParser();
    // 3.得到读取器
    XMLReader reader = sp.getXMLReader();
    // 4.设置内容处理器
    //reader.setContentHandler();
    // 5.读取xml文档内容
    reader.parse("src/main/resources/xml/book.xml");
  }
}

class ListHandler implements ContentHandler {

  public void setDocumentLocator(Locator locator) {

  }

  public void startDocument() throws SAXException {

  }

  public void endDocument() throws SAXException {

  }

  public void startPrefixMapping(String prefix, String uri) throws SAXException {

  }

  public void endPrefixMapping(String prefix) throws SAXException {

  }

  public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
    System.out.println("<" + qName + ">");
  }

  public void endElement(String uri, String localName, String qName) throws SAXException {

  }

  public void characters(char[] ch, int start, int length) throws SAXException {
    System.out.println(new String(ch, start, length));
  }

  public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {

  }

  public void processingInstruction(String target, String data) throws SAXException {

  }

  public void skippedEntity(String name) throws SAXException {

  }
}
