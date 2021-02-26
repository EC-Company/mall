package com.sxnd.manage.controller;

import com.github.pagehelper.PageInfo;
import com.sxnd.mall.dto.OrderDTO;
import com.sxnd.mall.result.ResultInfo;
import com.sxnd.mall.vo.OrderVO;
import com.sxnd.manage.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;


    @RequestMapping("queryOrderList")
    public ResultInfo queryList(OrderVO orderVO) {

        PageInfo<OrderDTO> pageInfo = orderService.queryList(orderVO);

        return ResultInfo.success(pageInfo.getTotal(), pageInfo.getList());
    }
    @RequestMapping("updateOrderStatus")
    public ResultInfo updateOrderStatus(int orderId){
        orderService.updateOrderStatus(orderId);
        return ResultInfo.ok();
    }


}
