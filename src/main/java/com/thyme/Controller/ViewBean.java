package com.thyme.Controller;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/**
 * @param :
 * @author: 62083 【makai@casco.com.cn】
 * @Date: 2018/3/19-15:48
 * @Description:
 * @return:
 */

@Controller
public class ViewBean implements View{
  @Nullable
  @Override
  public String getContentType() {
    return "text/html";
  }

  @Override
  public void render(@Nullable Map<String, ?> model, HttpServletRequest request,
                     HttpServletResponse response) throws Exception {
    response.getWriter().print("welcome to View: "+ new Date());

  }
}
