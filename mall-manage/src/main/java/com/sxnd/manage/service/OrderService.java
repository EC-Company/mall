package com.sxnd.manage.service;

import com.github.pagehelper.PageInfo;
import com.sxnd.mall.dto.OrderDTO;
import com.sxnd.mall.vo.OrderVO;

public interface OrderService {
    PageInfo<OrderDTO> queryList(OrderVO orderVO);

    void updateOrderStatus(int orderId);
}
