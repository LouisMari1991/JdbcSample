package com.sync.xml.exam.junit;

import com.sync.xml.exam.dao.StudentDao;
import com.sync.xml.exam.domain.Student;
import com.sync.xml.exam.exception.StudentNotExistException;
import org.junit.Test;

/**
 * Created by Administrator on 2016/11/22 0022.
 */
public class StudentDaoTest {

  @Test
  public void testAdd() {
    StudentDao dao = new StudentDao();
    Student s = new Student();
    s.setExamid("121");
    s.setGrade(89);
    s.setIdcard("132");
    s.setLocation("深圳");
    s.setName("abc");
    dao.add(s);
  }

  @Test
  public void testFind() {
    StudentDao dao = new StudentDao();
    Student s = dao.find("121");
    System.out.println(s);
  }

  @Test
  public void testDelete() throws StudentNotExistException {
    StudentDao dao = new StudentDao();
    dao.delete("abc");
  }

}
