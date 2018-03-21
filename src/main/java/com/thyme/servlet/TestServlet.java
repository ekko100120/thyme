package com.thyme.servlet;

import com.thyme.db.TableOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * @param :
 * @author: 62083 【makai@casco.com.cn】
 * @Date: 2018/3/20-11:10
 * @Description:
 * @return:
 */
public class TestServlet extends HttpServlet {

  @Autowired
  private TableOperator operator;

  public TestServlet() {

    System.err.println("Create TestServleting +++++++");
  }

  @Override
  public void init() throws ServletException{
    System.err.println("Init TestServleting +++++++");
    super.init();
    ServletContext servletContext =this.getServletContext();
    WebApplicationContext context = WebApplicationContextUtils.
          getWebApplicationContext(servletContext);
    operator = (TableOperator) context.getBean("SpringTableOperatorBean");
    System.err.println("init Table Opereate bean sucess");
  }


  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    System.err.println("doing TestServlet doGet service");
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    boolean createResult = false;
    boolean insertResult = false;
    boolean dropResult = false;

    try {
      operator.createTable();
      createResult = true;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    if (createResult) {
      try {
        operator.insert();
        insertResult = true;
      } catch (Exception e) {
        e.printStackTrace();
      }
//      try {
//        operator.tearDown();
//        dropResult = true;
//      } catch (Exception e) {
//        e.printStackTrace();
//      }
    }
    out.println("{'createResult':"+createResult+",'insertResult':"
        +insertResult+",'dropResult':"+dropResult+"}");

    out.flush();
    out.close();
  }
}
