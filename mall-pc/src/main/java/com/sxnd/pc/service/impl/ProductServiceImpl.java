package com.sxnd.pc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sxnd.mall.exception.ProductException;
import com.sxnd.mall.pojo.Order;
import com.sxnd.mall.pojo.Product;
import com.sxnd.mall.pojo.User;
import com.sxnd.mall.utils.UUIDUtil;
import com.sxnd.mall.vo.ProductVO;
import com.sxnd.pc.mapper.ProductMapper;
import com.sxnd.pc.mapper.UserMapper;
import com.sxnd.pc.service.ProductService;
import com.sxnd.pc.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductMapper productMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserService userService;

    @Override
    public PageInfo<Product> queryProductList(ProductVO productVO) {

        //设置分页参数
        PageHelper.startPage(productVO.getPage(), productVO.getLimit());


        return new PageInfo<Product>(productMapper.queryProductList(productVO));
    }

    @Override
    public Product queryProductInfoById(int productId) {
        return productMapper.queryProductInfoById(productId);
    }

    @Override
    @Transactional
    public void insertOrder(Order order) {
        //查询商品信息
        Product product = this.queryProductInfoById(order.getProductId());
        //库存是否足够
        if (product.getStock() < 1) {
            throw new ProductException("库存不足");
        }
        //查询用户信息
        User user = userMapper.queryUserById(order.getUserId());
        //判断用户积分是否足够
        if (user.getBalance() < product.getScore()) {
            throw new ProductException("用户积分不足");
        }
        //扣减商品库存
        productMapper.updateProductStock(product.getId());
        //扣减用户积分
        userService.updateUserBalance(order.getUserId(), product.getScore(), -1);
        //生成订单
        order.setOrderNo(UUIDUtil.uuid());
        order.setOrderscore(product.getScore());
        //将订单数据保存到数据库
        productMapper.insertOrder(order);
    }
}
