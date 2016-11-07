package com.sync.jdbc.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by Administrator on 2016/11/7 0007.
 */
public class MyDataSource2 {

  private static String url = "jdbc:mysql://localhost:3306/jdbc?generateSimpleParameterMetadata=true";
  private static String user = "root";
  private static String password = "root";

  private static int initCount = 5;
  private static int maxCount = 10;
  static int currentCount = 0;

  private LinkedList<Connection> connectionsPool = new LinkedList<Connection>();

  public MyDataSource2() {
    try {
      for (int i = 0; i < initCount; i++) {
        this.connectionsPool.addLast(this.createConnection());
        this.currentCount++;
      }
    } catch (SQLException e) {
      throw new ExceptionInInitializerError(e);
    }
  }

  public Connection getConnection() throws SQLException {
    synchronized (connectionsPool) {
      if (this.connectionsPool.size() > 0) {
        return this.connectionsPool.removeFirst();
      }
      if (currentCount < maxCount) {
        currentCount++;
        return this.createConnection();
      }
      throw new SQLException("已没有链接");
    }
  }

  public void free(Connection conn) {
    this.connectionsPool.addLast(conn);
  }

  private Connection createConnection() throws SQLException {
    Connection conn = DriverManager.getConnection(url, user, password);
    //MyConnection myConnection = new MyConnection(conn, this);
    //return myConnection;
    MyConnectionHandler proxy = new MyConnectionHandler(this);
    return  proxy.bind(conn);
  }
}
