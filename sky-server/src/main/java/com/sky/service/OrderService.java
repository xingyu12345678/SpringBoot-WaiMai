package com.sky.service;

import com.sky.dto.OrdersPageQueryDTO;
import com.sky.dto.OrdersPaymentDTO;
import com.sky.dto.OrdersSubmitDTO;
import com.sky.result.PageResult;
import com.sky.vo.OrderPaymentVO;
import com.sky.vo.OrderSubmitVO;
import com.sky.vo.OrderVO;

public interface OrderService {
    /**
     * 用户下单
     * @param ordersSubmitDTO
     * @return
     */
    OrderSubmitVO submitOrder(OrdersSubmitDTO ordersSubmitDTO);

    /**
     * 订单支付
     * @param ordersPaymentDTO
     * @return
     */
    OrderPaymentVO payment(OrdersPaymentDTO ordersPaymentDTO) throws Exception;

    /**
     * 支付成功，修改订单状态
     * @param outTradeNo
     */
    void paySuccess(String outTradeNo);


    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @param status
     * @return
     */
    PageResult ordersPage(int page, int pageSize, Integer status);

    /**
     * 订单详细
     * @param id
     * @return
     */
    OrderVO ordergetById(Long id);

    /**
     * 根据id取消订单
     * @param id
     */
    void cancelById(Long id);

    /**
     * 根据id再来一单
     * @param id
     */
    void repeOrderById(Long id);
}
