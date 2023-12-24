package com.sky.controller.admin;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import com.sky.vo.SetmealVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/setmeal")
@Slf4j
@Api(tags = "套餐相关接口")
public class SetmealController {


    @Autowired
    private SetmealService setmealService;

    /**
     * 分页查询
     * @param setmealPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("分页查询接口")
    public Result<PageResult> page(SetmealPageQueryDTO setmealPageQueryDTO){
        log.info("分页查询：{}",setmealPageQueryDTO);
        PageResult pageResult =  setmealService.page(setmealPageQueryDTO);

        return Result.success(pageResult);
    }

    /**
     * 新增套餐
     * @param setmealDTO
     * @return
     */
    @PostMapping
    @ApiOperation("新增套餐")
    public Result save(@RequestBody SetmealDTO setmealDTO){
        log.info("新增套餐：{}",setmealDTO);
        setmealService.save(setmealDTO);

        return Result.success();
    }


    /**
     * 根据id查找
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据id查找")
    public Result<SetmealVO> getById(@PathVariable Long id){
        log.info("根据id查找");
        SetmealVO setmealVO = setmealService.getById(id);

        return Result.success(setmealVO);
    }

    /**
     * 套餐启动关闭
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("套餐启动关闭")
    public Result status(@PathVariable Integer status,Long id){
        log.info("套餐启动关闭：{}，{}",status,id);
        setmealService.startOrStop(status,id);
        return Result.success();
    }

    /**
     * 套餐修改
     * @param setmealDTO
     * @return
     */
    @PutMapping
    @ApiOperation("套餐修改")
    public Result update(@RequestBody SetmealDTO setmealDTO){
        log.info("套餐修改：{}",setmealDTO);
        setmealService.update(setmealDTO);
        return Result.success();
    }

    @DeleteMapping
    @ApiOperation("批量删除")
    public Result delect(@RequestParam List<Long> ids){

        log.info("批量删除：{}",ids);
        setmealService.delect(ids);
        return Result.success();
    }




}
