package com.sxnd.manage.controller;

import com.sxnd.mall.pojo.Product;
import com.sxnd.manage.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class ViewController {

    @Autowired
    private ProductService productService;

    @RequestMapping("login.html")
    public String login() {
        return "login";
    }

    @RequestMapping("logout")
    public String logout(HttpSession session) {
        //重置session
        session.invalidate();
        //重定向到登录页
        return "redirect:login.html";
    }


    @RequestMapping("index.html")
    public String index() {
        return "index";
    }

    @RequestMapping("user")
    public ModelAndView toUserPage(ModelAndView mav) {
        mav.setViewName("user/userList");
        return mav;
    }

    @RequestMapping("product")
    public ModelAndView toProductPage(ModelAndView mav) {
        mav.setViewName("product/productList");
        return mav;
    }

    @RequestMapping("addProduct.html")
    public ModelAndView toAddProductPage(ModelAndView mav) {
        mav.setViewName("product/product_add");
        return mav;
    }

    @RequestMapping("editProduct.html")
    public ModelAndView toEditProductPage(ModelAndView mav, int id) {
        //根据商品id查询商品详情
        Product product = productService.queryProductInfo(id);
        //product信息传递到视图
        mav.addObject("product", product);
        mav.setViewName("product/product_edit");
        return mav;
    }

    @RequestMapping("order")
    public ModelAndView toOrderPage(ModelAndView mav) {
        mav.setViewName("order/orderList");
        return mav;
    }


}
