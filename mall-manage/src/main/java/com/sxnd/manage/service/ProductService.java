package com.sxnd.manage.service;

import com.github.pagehelper.PageInfo;
import com.sxnd.mall.pojo.Product;
import com.sxnd.mall.vo.ProductVO;

public interface ProductService {
    PageInfo<Product> queryList(ProductVO productVO);

    Product queryProductInfo(int id);

    void deleteProductById(int id);

    void updateProductStatus(int id, int status);

    void insertProduct(Product product);

    void updateProduct(Product product);
}
