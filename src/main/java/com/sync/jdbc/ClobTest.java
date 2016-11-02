package com.sync.jdbc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Administrator on 2016/11/2 0002.
 */
public class ClobTest {

  public static void main(String[] args) throws IOException, SQLException {
    //create();
    read();
  }

  static void create() throws SQLException, IOException {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
      // 2.建立链接
      conn = JdbcUtils.getConnection();

      // 3.创建语句
      String sql = "INSERT INTO clob_test(big_text) values(?)";
      ps = conn.prepareStatement(sql);
      File file = new File("src/main/java/com/sync/jdbc/JdbcUtils.java");
      Reader reader = new BufferedReader(new FileReader(file));
      ps.setCharacterStream(1, reader, file.length());

      // 4.执行语句
      int i = ps.executeUpdate();
      reader.close();

      System.out.println("i=" + i);
    } finally {
      JdbcUtils.free(rs, ps, conn);
    }
  }

  static void read() throws SQLException, IOException {
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;
    try {
      // 2.建立链接
      conn = JdbcUtils.getConnection();

      // 3.创建语句
      st = conn.createStatement();

      // 4.执行语句
      rs = st.executeQuery("SELECT big_text FROM clob_test");

      // 5.处理结果
      while (rs.next()) {
        Clob clob = rs.getClob(1);
        File file = new File("JdbcUtils_bak.java");
        Reader reader = clob.getCharacterStream();
        Writer writer = new BufferedWriter(new FileWriter(file));
        char[] buff = new char[1024];
        int len = -1;
        while ((len = reader.read(buff)) != -1) {
          System.out.println(len);
          writer.write(buff, 0, len);
        }
        writer.close();
        reader.close();
      }

    } finally {
      JdbcUtils.free(rs, st, conn);
    }
  }
}
