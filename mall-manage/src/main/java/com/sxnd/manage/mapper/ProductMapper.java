package com.sxnd.manage.mapper;

import com.sxnd.mall.pojo.Product;
import com.sxnd.mall.vo.ProductVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {

    List<Product> queryList(ProductVO productVO);

    Product queryProductInfo(@Param("id") int id);

    void deleteProductById(@Param("id") int id);

    void updateProductStatus(@Param("id") int id, @Param("status") int status);

    void insertProduct(Product product);

    void updateProduct(Product product);
}
