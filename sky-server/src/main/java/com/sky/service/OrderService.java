package com.sky.service;

import com.sky.dto.*;
import com.sky.entity.Orders;
import com.sky.result.PageResult;
import com.sky.vo.OrderPaymentVO;
import com.sky.vo.OrderStatisticsVO;
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

    /**
     * 订单查询
     * @param ordersPageQueryDTO
     * @return
     */
    PageResult conditionSearch(OrdersPageQueryDTO ordersPageQueryDTO);

    /**
     * 订单数据统计
     * @return
     */
    OrderStatisticsVO statistice();

    /**
     * 商家端接单,派送
     * @param orders
     */
    void confirm(Orders orders);


    /**
     * 商家端拒单
     * @param ordersRejectionDTO
     */
    void ordersRejection(OrdersRejectionDTO ordersRejectionDTO);

    /**
     * 商家取消订单
     * @param ordersCancelDTO
     */
    void cancel(OrdersCancelDTO ordersCancelDTO);

    /**
     * 客户催单
     * @param id
     */
    void reminder(Long id);
}
