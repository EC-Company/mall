package com.sxnd.manage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sxnd.mall.dto.OrderDTO;
import com.sxnd.mall.vo.OrderVO;
import com.sxnd.manage.mapper.OrderMapper;
import com.sxnd.manage.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderMapper orderMapper;

    @Override
    public PageInfo<OrderDTO> queryList(OrderVO orderVO) {
        //设置分页参数
        PageHelper.startPage(orderVO.getPage(), orderVO.getLimit());

        return new PageInfo<OrderDTO>(orderMapper.queryList(orderVO));
    }

    @Override
    public void updateOrderStatus(int orderId) {
        orderMapper.updateOrderStatus(orderId);
    }
}
