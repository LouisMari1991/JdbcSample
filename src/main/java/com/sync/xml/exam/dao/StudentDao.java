package com.sync.xml.exam.dao;

import com.sync.xml.exam.domain.Student;
import com.sync.xml.exam.exception.StudentNotExistException;
import com.sync.xml.exam.utils.XmlUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Created by Administrator on 2016/11/22 0022.
 */
public class StudentDao {

  public void add(Student student) {
    try {
      Document document = XmlUtils.getDocument();

      Element student_tag = document.createElement("student");

      // 创建出封装学生的标签
      student_tag.setAttribute("idcard", student.getIdcard());
      student_tag.setAttribute("examid", student.getExamid());

      // 创建用于封装学生姓名，所在地和成绩的标签
      Element name = document.createElement("name");
      Element location = document.createElement("location");
      Element grade = document.createElement("grade");

      name.setTextContent(student.getName());
      location.setTextContent(student.getLocation());
      grade.setTextContent(String.valueOf(student.getGrade()));

      student_tag.appendChild(name);
      student_tag.appendChild(location);
      student_tag.appendChild(grade);

      // 把封装的信息的标签挂到文档上
      document.getElementsByTagName("exam").item(0).appendChild(student_tag);

      XmlUtils.write2Xml(document);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public Student find(String examid) {
    try {
      Document document = XmlUtils.getDocument();
      NodeList list = document.getElementsByTagName("student");
      for (int i = 0; i < list.getLength(); i++) {
        Element student_tag = (Element) list.item(i);
        if (student_tag.getAttribute("examid").equals(examid)) {
          Student s = new Student();
          s.setExamid(examid);
          s.setIdcard(student_tag.getAttribute("idcard"));
          s.setName(student_tag.getElementsByTagName("name").item(0).getTextContent());
          s.setLocation(student_tag.getElementsByTagName("location").item(0).getTextContent());
          s.setGrade(Double.valueOf(student_tag.getElementsByTagName("grade").item(0).getTextContent()));
          return s;
        }
      }
      return null;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public void delete(String name) throws StudentNotExistException {
    try {
      Document document = XmlUtils.getDocument();
      NodeList list = document.getElementsByTagName("name");
      for (int i = 0; i < list.getLength(); i++) {
        if (list.item(i).getTextContent().equals(name)) {
          list.item(i).getParentNode().getParentNode().removeChild(list.item(i).getParentNode());
          XmlUtils.write2Xml(document);
          return;
        }
      }
      throw new StudentNotExistException(name + "不存在!");
    } catch (StudentNotExistException e) {
      throw e;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
