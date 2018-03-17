package com.thyme.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
    return "index";
  }
}
