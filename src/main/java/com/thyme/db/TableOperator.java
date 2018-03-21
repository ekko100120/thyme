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
    String sql = "DROP TABLE  'tasks' ";
    Connection connection = dataSource.getConnection();
    Statement statement = connection.createStatement();
    statement.execute(sql);
    statement.close();
    connection.close();
  }
  public void createTable() throws SQLException {
    StringBuffer ddl = new StringBuffer();
    String sql ="CREATE TABLE IF NOT EXISTS tasks ( task_id INT(11) NOT NULL AUTO_INCREMENT, subject VARCHAR(45) DEFAULT NULL, start_date DATE DEFAULT NULL, end_date DATE DEFAULT NULL, description VARCHAR(200) DEFAULT NULL, PRIMARY KEY (task_id)) ENGINE=InnoDB;";
    ddl.append(sql);
    Connection connection = dataSource.getConnection();
    Statement statement = connection.createStatement();
    statement.execute(ddl.toString());
    statement.close();
    connection.close();
  }

  public void insert() throws  Exception{
    StringBuffer dll = new StringBuffer();
    String sql="INSERT INTO tasks(subject,start_date,end_date,description) VALUES " ;
    dll.append(sql);
    System.out.println(sql.toString());
    for (int i = 0; i <COUNT ; i++) {
      dll.append("('任务-"+i+"', now(), now(),'Description "+i+"'),");
    }
    dll.append("('任务-"+COUNT+"', now(),now(),'Description "+COUNT+"')");
    Connection connection = dataSource.getConnection();
    System.err.println(dll.toString());
    PreparedStatement statement = connection.prepareStatement(dll.toString());
//    for (int i = 0; i < COUNT; ++i) {
//      statement.setInt(i + 1, i);
//    }
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
