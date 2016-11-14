package com.sync.jdbc.dao.refactor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Administrator on 2016/11/8 0008.
 */
public interface RowMapper<T> {
  T mapRow(ResultSet rs) throws SQLException;
}
