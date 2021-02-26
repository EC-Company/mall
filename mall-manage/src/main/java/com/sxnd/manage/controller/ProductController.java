package com.sxnd.manage.controller;

import com.github.pagehelper.PageInfo;
import com.sxnd.mall.pojo.Product;
import com.sxnd.mall.result.ResultInfo;
import com.sxnd.mall.vo.ProductVO;
import com.sxnd.manage.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping("queryProductList")
    public ResultInfo queryList(ProductVO productVO) {
        PageInfo<Product> pageInfo = productService.queryList(productVO);
        return ResultInfo.success(pageInfo.getTotal(), pageInfo.getList());
    }

    @RequestMapping("insertProduct")
    public ResultInfo insertProduct(Product product) {
        productService.insertProduct(product);
        return ResultInfo.ok();
    }

    @RequestMapping("deleteProduct")
    public ResultInfo deleteProductById(int id) {
        //通过id删除product
        productService.deleteProductById(id);
        return ResultInfo.ok();
    }

    @RequestMapping("updateProductStatus")
    public ResultInfo updateProductStatus(int id, int status) {
        //修改商品状态
        productService.updateProductStatus(id, status);
        return ResultInfo.ok();
    }
    @RequestMapping("updateProduct")
    public ResultInfo updateProduct(Product product){
        productService.updateProduct(product);
        return ResultInfo.ok();
    }


}
