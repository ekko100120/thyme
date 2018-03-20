package com.thyme.Controller;

import com.thyme.Entity.Shop;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @param :
 * @author: 62083 【makai@casco.com.cn】
 * @Date: 2018/3/19-17:37
 * @Description:
 * @return:
 */
@Controller
@RequestMapping("/shop")
public class JasonController {

  @RequestMapping(value = "/view")
  public String testJason(){
    return "ViewBean";
  }

  @RequestMapping(value = "{name}",method = RequestMethod.GET)
  public  @ResponseBody Shop getShopJason(@PathVariable String name){
    System.err.println("------请求jason数据--------");
    Shop shop = new Shop();
    shop.setName(name);
    shop.setStaffName(new String[]{"shanghai","beijing","shenzhen"});
    return shop;
  }
}
