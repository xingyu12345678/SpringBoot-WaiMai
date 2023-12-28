package com.sky.controller.user;

import com.sky.dto.OrdersPageQueryDTO;
import com.sky.dto.OrdersPaymentDTO;
import com.sky.dto.OrdersSubmitDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.OrderService;
import com.sky.vo.OrderPaymentVO;
import com.sky.vo.OrderSubmitVO;
import com.sky.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("userOrderController")
@RequestMapping("/user/order")
@Api(tags = "用户端订单相关接口")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 用户下单
     * @param ordersSubmitDTO
     * @return
     */
    @PostMapping("/submit")
    @ApiOperation("用户下单")
    public Result<OrderSubmitVO> submit(@RequestBody OrdersSubmitDTO ordersSubmitDTO){
        log.info("用户下单：{}",ordersSubmitDTO);
        OrderSubmitVO orderSubmitVO =  orderService.submitOrder(ordersSubmitDTO);

        return Result.success(orderSubmitVO);
    }
    /**
     * 订单支付
     *
     * @param ordersPaymentDTO
     * @return
     */
    @PutMapping("/payment")
    @ApiOperation("订单支付")
    public Result<OrderPaymentVO> payment(@RequestBody OrdersPaymentDTO ordersPaymentDTO) throws Exception {
        log.info("订单支付：{}", ordersPaymentDTO);
        OrderPaymentVO orderPaymentVO = orderService.payment(ordersPaymentDTO);
        log.info("生成预支付交易单：{}", orderPaymentVO);
        return Result.success(orderPaymentVO);
    }

    /**
     * 订单查询
     * @param page
     * @param pageSize
     * @param status
     * @return
     */
    @GetMapping("/historyOrders")
    @ApiOperation("订单查询")
    public Result<PageResult> historyOrders(int page, int pageSize, Integer status){
        log.info("订单分页查询:{},{},{}",page,pageSize,status);
        PageResult pageResult = orderService.ordersPage(page,pageSize,status);
        return Result.success(pageResult);
    }

    /**
     * 根据id查找订单
     * @param id
     * @return
     */
    @GetMapping("/orderDetail/{id}")
    @ApiOperation("根据id查找订单")
    public Result<OrderVO> orderDetail(@PathVariable Long id){
        log.info("详细订单查询id:{}",id);
        OrderVO orderVO = orderService.ordergetById(id);
        return Result.success(orderVO);
    }

    /**
     * 根据id取消订单
     * @param id
     * @return
     */
    @PutMapping("/cancel/{id}")
    @ApiOperation("根据id取消订单")
    public Result cancel(@PathVariable Long id){
        log.info("根据id取消订单：{}",id);
        orderService.cancelById(id);
        return Result.success();
    }

    /**
     * 根据id再来一单
     * @param id
     * @return
     */
    @PostMapping("/repetition/{id}")
    @ApiOperation("根据id再来一单")
    public Result repetition(@PathVariable Long id){
        log.info("根据id再来一单:{}",id);
        orderService.repeOrderById(id);
        return Result.success();
    }






}
