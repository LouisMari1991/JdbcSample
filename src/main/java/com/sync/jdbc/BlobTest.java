package com.sync.jdbc;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Administrator on 2016/11/2 0002.
 */
public class BlobTest {

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
      String sql = "INSERT INTO blob_test(big_bit) values(?)";
      ps = conn.prepareStatement(sql);
      File file = new File("codestyle.png");
      InputStream in = new BufferedInputStream(new FileInputStream(file));

      ps.setBinaryStream(1, in, file.length());

      // 4.执行语句
      int i = ps.executeUpdate();
      in.close();

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
      rs = st.executeQuery("SELECT big_bit FROM blob_test");

      // 5.处理结果
      while (rs.next()) {
        Blob blob = rs.getBlob(1);
        InputStream in = blob.getBinaryStream();
        File file = new File("codestyle_bak.png");
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
        byte[] buff = new byte[1024];
        int len = -1;
        while ((len = in.read(buff)) != -1) {
          System.out.println(len);
          out.write(buff, 0, len);
        }
        out.close();
        in.close();
      }

    } finally {
      JdbcUtils.free(rs, st, conn);
    }
  }

}
