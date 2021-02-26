package com.sxnd.pc.controller;

import com.github.pagehelper.PageInfo;
import com.sxnd.mall.pojo.Order;
import com.sxnd.mall.pojo.Product;
import com.sxnd.mall.pojo.User;
import com.sxnd.mall.result.ResultInfo;
import com.sxnd.mall.vo.ProductVO;
import com.sxnd.pc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping("queryProductList")
    public ResultInfo queryProductList(ProductVO productVO) {
        PageInfo<Product> pageInfo = productService.queryProductList(productVO);
        return ResultInfo.success(pageInfo.getTotal(), pageInfo.getList());
    }

    @RequestMapping("insertOrder")
    public ResultInfo insertOrder(Order order, HttpSession session) {
        //获取当前用户信息
        Object o = session.getAttribute("userInfo");
        if (o == null) {
            return ResultInfo.fail("用户为登录，请登录后在尝试兑换");
        }
        User user = (User) o;
        order.setUserId(user.getId());
        //兑换商品
        productService.insertOrder(order);

        return ResultInfo.ok();
    }


}
