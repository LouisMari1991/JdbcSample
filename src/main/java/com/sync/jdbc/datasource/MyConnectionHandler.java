package com.sync.jdbc.datasource;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;

/**
 * Java的动态代理及使用,该技术完善连接代理
 *
 * Created by Administrator on 2016/11/7 0007.
 */
public class MyConnectionHandler implements InvocationHandler {

  private Connection realConnection;
  private Connection warpedConnection;
  private MyDataSource2 dataSource;
  private int maxUseCount = 10;
  private int currentUserCount = 0;

  MyConnectionHandler(MyDataSource2 dataSource) {
    this.dataSource = dataSource;
  }

  Connection bind(java.sql.Connection realConn) {
    this.realConnection = realConn;
    this.warpedConnection =
        (Connection) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[] {Connection.class}, this);
    return warpedConnection;
  }

  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    if ("close".equals(method.getName())) {
      this.currentUserCount++;
      if (this.currentUserCount < maxUseCount) {
        this.dataSource.free(this.warpedConnection);
      } else {
        this.realConnection.close();
        this.dataSource.currentCount--;
      }
      this.dataSource.free(warpedConnection);
    }
    return method.invoke(this.realConnection, args);
  }
}
