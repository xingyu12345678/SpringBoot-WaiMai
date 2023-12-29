package com.sky.controller.admin;

import com.sky.dto.*;
import com.sky.entity.Orders;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.OrderService;
import com.sky.vo.OrderStatisticsVO;
import com.sky.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("adminOrder")
@RequestMapping("/admin/order")
@Slf4j
@Api(tags = "订单相关接口")
public class OrderContorller {

        @Autowired
        private OrderService orderService;

    /**
     * 订单查询
     * @param ordersPageQueryDTO
     * @return
     */
        @GetMapping("/conditionSearch")
        @ApiOperation("订单查询")
        public Result<PageResult> conditionSearch(OrdersPageQueryDTO ordersPageQueryDTO){
            log.info("订单查询：{}", ordersPageQueryDTO);
            PageResult pageResult = orderService.conditionSearch(ordersPageQueryDTO);
            return Result.success(pageResult);
        }

    /**
     * 订单数据统计
     * @return
     */
        @GetMapping("/statistics")
        @ApiOperation("订单数据统计")
        public Result<OrderStatisticsVO> statistice(){
            log.info("各个订单数据统计");
            OrderStatisticsVO orderStatisticsVO = orderService.statistice();
            return Result.success(orderStatisticsVO);
        }

    /**
     * 根据id查看详情
     * @param id
     * @return
     */
        @GetMapping("/details/{id}")
        @ApiOperation("根据id查看订单详情")
        public Result<OrderVO> orderGetById(@PathVariable Long id){
            log.info("根据id查询订单详情:{}",id);
            OrderVO orderVO = orderService.ordergetById(id);
            return Result.success(orderVO);
        }

    /**
     * 商家端接单
     * @param ordersConfirmDTO
     * @return
     */
    @PutMapping("/confirm")
        @ApiOperation("商家端接单")
        public Result confirm(@RequestBody OrdersConfirmDTO ordersConfirmDTO){
            log.info("商家端接单：{}",ordersConfirmDTO);
            Orders orders = Orders.builder()
                    .id(ordersConfirmDTO.getId())
                    .status(Orders.CONFIRMED)
                    .build();
            orderService.confirm(orders);
            return Result.success();
        }

        @PutMapping("/delivery/{id}")
        @ApiOperation("商家端派送")
        public Result delivery(@PathVariable Long id){
        log.info("派送订单：{}",id);
        Orders orders = Orders.builder()
                .id(id)
                .status(Orders.DELIVERY_IN_PROGRESS)
                .build();
        orderService.confirm(orders);
        return Result.success();
        }

        @PutMapping("/complete/{id}")
        @ApiOperation("商家端完成订单")
        public Result complete(@PathVariable Long id){
        log.info("完成订单:{}",id);
            Orders orders = Orders.builder()
                    .id(id)
                    .status(Orders.COMPLETED)
                    .build();
            orderService.confirm(orders);
            return Result.success();
        }

    /**
     * 商家端拒单
     * @param ordersRejectionDTO
     * @return
     */
    @PutMapping("/rejection")
        @ApiOperation("商家拒单")
        public Result rejection(@RequestBody OrdersRejectionDTO ordersRejectionDTO){
        log.info("商家拒单：{}",ordersRejectionDTO);
        orderService.ordersRejection(ordersRejectionDTO);
        return Result.success();
        }

        @PutMapping("/cancel")
        @ApiOperation("商家取消订单")
        public Result cancel(@RequestBody OrdersCancelDTO ordersCancelDTO){
        log.info("商家端取消订单：{}",ordersCancelDTO);
        orderService.cancel(ordersCancelDTO);
        return Result.success();
        }



}
