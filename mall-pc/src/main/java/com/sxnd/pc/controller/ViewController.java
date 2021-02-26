package com.sxnd.pc.controller;

import com.sxnd.mall.pojo.Product;
import com.sxnd.pc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewController {
    @Autowired
    private ProductService productService;

    @RequestMapping("login.html")
    public String toLoginPage() {
        return "login";
    }

    @RequestMapping("index.html")
    public String toIndex() {
        return "index";
    }

    @RequestMapping("details.html")
    public ModelAndView toDetails(ModelAndView mav, int productId) {
        //查询商品详情
        Product product = productService.queryProductInfoById(productId);

        mav.addObject("product", product);

        mav.setViewName("details");
        return mav;
    }

    @RequestMapping("register.html")
    public ModelAndView toRegister(ModelAndView mav, Integer userId) {

        mav.addObject("userId", userId);
        mav.setViewName("register");
        return mav;
    }


}
