package com.sync.jdbc.spring;

import com.sync.jdbc.JdbcUtils;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * Created by Administrator on 2016/11/11 0011.
 */
public class JdbcNameTemplateTest {

  static NamedParameterJdbcTemplate maned = new NamedParameterJdbcTemplate(JdbcUtils.getDataSource());

  public static void main(String[] args) {

  }



}
