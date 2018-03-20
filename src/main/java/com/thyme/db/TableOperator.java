package com.thyme.db;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @param :
 * @Date: 2018/3/20-10:22
 * @Description:
 * @return:
 */
public class TableOperator {

  private static final int COUNT =10000;
  private DataSource dataSource;

  public TableOperator() throws SQLException {
  }

  public void setDataSource(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  private  void dropTable() throws SQLException {
    Connection connection = dataSource.getConnection();
    Statement statement = connection.createStatement();
    statement.execute("DROP TABLE  t_big ");
    statement.close();
    connection.close();
  }
  public void createTable() throws SQLException {
    StringBuffer ddl = new StringBuffer();
    ddl.append("CREATE TABLE t_big (FID INT AUTO INCREMENT PRIMARY KEY)");
    for (int i = 0; i <COUNT ; i++) {
      ddl.append(",");
      ddl.append("F"+i);
      ddl.append("BIGINT NULL");
    }
    ddl.append(")");
    Connection connection = dataSource.getConnection();
    Statement statement = connection.createStatement();
    statement.execute(ddl.toString());
    statement.close();
    connection.close();
  }

  public void insert() throws  Exception{
    StringBuffer dll = new StringBuffer();
    dll.append("INSERT INTO t_big (");
    for (int i = 0; i <COUNT ; i++) {
      if (i != 0){
        dll.append(",");
      }
      dll.append("?");
    }
    dll.append(")");
    Connection connection = dataSource.getConnection();
    System.err.println(dll.toString());
    PreparedStatement statement = connection.prepareStatement(dll.toString());
    for (int i = 0; i < COUNT; ++i) {
      statement.setInt(i + 1, i);
    }
    statement.execute();
    statement.close();

    connection.close();
  }

  public void tearDown(){
    try {
      dropTable();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }


}
