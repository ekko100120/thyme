package com.thyme.Controller;

import com.thyme.Entity.Shop;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @param :
 * @author: 62083 【makai@casco.com.cn】
 * @Date: 2018/3/16-9:10
 * @Description:
 * @return:
 */
@Controller

public class HomeController {

  @RequestMapping(value="/home")
  public String home(){
    return "home";
  }

  @RequestMapping(value = "/forward")
  public String testForWord(){
    return "sucess";
  }

  @RequestMapping(value = "/redirect")
  public String testRedirect(){
    return "redirect:/home";
  }

  @RequestMapping(value = "/view")
  public String testViewBean(){
    System.out.println("test viewBean");
    return "viewBean";
  }



}
