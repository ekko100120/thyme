package com.thyme.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @param :
 * @author: kenny
 * @Date: 2018/3/16-9:10
 * @Description:
 * @return:
 */
@Controller
public class HomeController {

  @RequestMapping(value="/home")
  public String home(){
    return "index";
  }
}
