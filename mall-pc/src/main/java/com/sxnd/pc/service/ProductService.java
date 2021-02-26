package com.sxnd.pc.service;

import com.github.pagehelper.PageInfo;
import com.sxnd.mall.pojo.Order;
import com.sxnd.mall.pojo.Product;
import com.sxnd.mall.vo.ProductVO;

public interface ProductService {
    PageInfo<Product> queryProductList(ProductVO productVO);

    Product queryProductInfoById(int productId);

    void insertOrder(Order order);
}
