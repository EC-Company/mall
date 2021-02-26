package com.sxnd.manage.mapper;

import com.sxnd.mall.dto.OrderDTO;
import com.sxnd.mall.vo.OrderVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderMapper {
    List<OrderDTO> queryList(OrderVO orderVO);

    void updateOrderStatus(@Param("id") int orderId);
}
