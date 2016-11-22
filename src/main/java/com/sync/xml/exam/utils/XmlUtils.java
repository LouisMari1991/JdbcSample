package com.sync.xml.exam.utils;


import java.io.FileOutputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;


/**
 * Created by Administrator on 2016/11/22 0022.
 */
public class XmlUtils {

  private static String fileName = "src/main/resources/xml/exam.xml";

  public static Document getDocument() throws Exception {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    return builder.parse(fileName);
  }

  public static void write2Xml(Document document) throws Exception {
    TransformerFactory factory = TransformerFactory.newInstance();
    Transformer tf = factory.newTransformer();
    tf.transform(new DOMSource(document), new StreamResult(new FileOutputStream(fileName)));
  }
}
