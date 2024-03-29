package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/admin/dish")
@Api(tags = "菜品相关接口")
@Slf4j
public class DishController {

    @Autowired
    private DishService dishService;
    
    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 新增菜品
     * @param dishDTO
     * @return
     */
    @PostMapping
    @ApiOperation("新增菜品")
    @CacheEvict(cacheNames = "setmealCache", key = "#dishDTO.categoryId")
    public Result save(@RequestBody DishDTO dishDTO){
        log.info("新增菜品：{}",dishDTO);
        dishService.saveWithFlavar(dishDTO);

        String key = "dish_" + dishDTO.getCategoryId();

//        cleanCache(key);

        return Result.success();
    }

    /**
     * 菜品分页查询
     * @param dishPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("菜品分页查询")
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO){
        log.info("分页查询：{}",dishPageQueryDTO);
        PageResult pageResult = dishService.page(dishPageQueryDTO);

        return Result.success(pageResult);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @DeleteMapping
    @ApiOperation("批量删除")
    @CacheEvict(cacheNames = "setmealCache",allEntries = true)
    public Result delect(@RequestParam List<Long> ids){
        log.info("批量删除:{}",ids);
        dishService.delectBatch(ids);
//        cleanCache("dish_*");
        return Result.success();
    }

    /**
     * 根据id查询菜品
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据id查找")
    public Result<DishVO> getById(@PathVariable Long id){
        log.info("根据id查找:{}",id);
        DishVO dishVO = dishService.getByIdWitchFlavor(id);
        return Result.success(dishVO);
    }

    /**
     * 修改菜品
     * @param dishDTO
     * @return
     */
    @PutMapping
    @ApiOperation("修改菜品")
    @CacheEvict(cacheNames = "setmealCache",allEntries = true)
    public Result update(@RequestBody DishDTO dishDTO){
        log.info("修改菜品:{}",dishDTO);
        dishService.updateWitchFlavor(dishDTO);

//        cleanCache("dish_*");
        return Result.success();
    }

    /**
     * 商品开关
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("商品开关")
    @CacheEvict(cacheNames = "setmealCache",allEntries = true)
    public Result startOrStop(@PathVariable Integer status,Long id){
        log.info("商品开关:{},{}",status,id);
        dishService.startOrStop(status,id);

        cleanCache("dish_*");

        return Result.success();
    }

    @GetMapping("/list")
    @ApiOperation("根据分类查询菜品")
    public Result<List<Dish>> list(Long categoryId){
        log.info("根据分类查询菜品:{}",categoryId);
        List<Dish> list =  dishService.list(categoryId);
        return Result.success(list);
    }

    /**
     * 统一清理缓存数据
     * @param pattern
     */
    private void cleanCache(String pattern){
        Set keys = redisTemplate.keys(pattern);
        redisTemplate.delete(keys);
    }


}
