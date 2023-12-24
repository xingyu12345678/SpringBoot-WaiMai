package com.sky.controller.admin;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/admin/shop")
@RestController("shopContorller")
@Slf4j
@Api(tags = "店铺相关接口")
public class ShopContorller {

    @Autowired
    private RedisTemplate redisTemplate;
    public static final String KEY = "SHOP_STATUS";


    /**
     * 修改营业状态
     * @param status
     * @return
     */
    @PutMapping("/{status}")
    @ApiOperation("修改营业状态")
    public Result status(@PathVariable Integer status){
        log.info("营业状态为：{}",status==1 ? "营业中" : "打样中");
        redisTemplate.opsForValue().set(KEY,status);
        return Result.success();
    }

    /**
     * 查询状态
     * @return
     */
    @GetMapping("/status")
    @ApiOperation("获取营业状态")
    public Result<Integer> getstatus(){

        Integer status = (Integer) redisTemplate.opsForValue().get(KEY);
        log.info("获取店铺的营业状态为：{}",status == 1 ? "营业中" : "打样中");
        return Result.success(status);
    }

}
