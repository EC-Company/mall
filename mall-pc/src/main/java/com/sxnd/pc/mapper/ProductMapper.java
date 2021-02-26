package com.sxnd.pc.mapper;

import com.sxnd.mall.pojo.Order;
import com.sxnd.mall.pojo.Product;
import com.sxnd.mall.vo.ProductVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<Product> queryProductList(ProductVO productVO);


    Product queryProductInfoById(@Param("productId") int productId);

    void updateProductStock(@Param("id")int id);

    void insertOrder(Order order);


}
