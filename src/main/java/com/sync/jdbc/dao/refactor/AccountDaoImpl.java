package com.sync.jdbc.dao.refactor;

import com.sync.jdbc.domain.Account;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Administrator on 2016/11/8 0008.
 */
public class AccountDaoImpl extends AbstractDao<Account> {

  public Account findAccount(int id) {
    String sql = "select id,name,money from account where id=?";
    Account account = super.find(sql, id);
    return account;
  }

  protected Account rowMapper(ResultSet rs) throws SQLException {
    Account account = new Account();
    account.setId(rs.getInt("id"));
    // ... other operation
    return account;
  }
}
