package com.sky.controller.admin;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping("/admin/category")
@Api(tags = "分类查询接口")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 新增分类
     * @param categoryDTO
     * @return
     */
    @PostMapping
    @ApiOperation("新增分类")
    public Result<String> save(@RequestBody CategoryDTO categoryDTO){

        log.info("新增分类：{}",categoryDTO);
        categoryService.save(categoryDTO);
        return Result.success();
    }


    /**
     * 菜品分页查询
     * @param categoryPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("菜品分页查询")
    public Result<PageResult> page(CategoryPageQueryDTO categoryPageQueryDTO){
        log.info("菜品分页查询：{} ",categoryPageQueryDTO);
        PageResult pageResult = categoryService.pageQuery(categoryPageQueryDTO);

        return Result.success(pageResult);
    }


    /**
     * 启用禁用
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("菜品禁用")
    public Result startOrStop(@PathVariable Integer status, Long id){
        log.info("菜品禁用启用：{},{}", status,id);
        categoryService.startOrStop(status,id);

        return Result.success();
    }

    /**
     * 分类删除
     * @param id
     * @return
     */
    @DeleteMapping
    @ApiOperation("分类删除")
    public Result delectById(Long id){
        log.info("分类删除：{}",id);
        categoryService.delectById(id);
        return Result.success();
    }

    /**
     * 根据id分类查找
     * @param categoryDTO
     * @return
     */
    @PutMapping
    @ApiOperation("id分类修改")
    public Result update(@RequestBody CategoryDTO categoryDTO){
        log.info("id分类修改：{}",categoryDTO);
        categoryService.update(categoryDTO);
        return Result.success();

    }

    /**
     * 根据分类查找
     * @param type
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("类型查找")
    public Result<List<Category>> list(Integer type){
        log.info("类型查找: {}",type);
        List<Category> list= categoryService.list(type);
        return Result.success(list);
    }







}
