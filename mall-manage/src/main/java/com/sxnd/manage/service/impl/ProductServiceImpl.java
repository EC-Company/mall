package com.sxnd.manage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sxnd.mall.pojo.Product;
import com.sxnd.mall.vo.ProductVO;
import com.sxnd.manage.mapper.ProductMapper;
import com.sxnd.manage.service.ProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductMapper productMapper;

    @Override
    public PageInfo<Product> queryList(ProductVO productVO) {
        //设置分页参数
        PageHelper.startPage(productVO.getPage(), productVO.getLimit());
        return new PageInfo<Product>(productMapper.queryList(productVO));
    }

    @Override
    public Product queryProductInfo(int id) {
        return productMapper.queryProductInfo(id);
    }

    @Override
    public void deleteProductById(int id) {
        productMapper.deleteProductById(id);
    }

    @Override
    public void updateProductStatus(int id, int status) {
        productMapper.updateProductStatus(id, status);
    }

    @Override
    public void insertProduct(Product product) {
        productMapper.insertProduct(product);
    }

    @Override
    public void updateProduct(Product product) {
        productMapper.updateProduct(product);
    }
}
