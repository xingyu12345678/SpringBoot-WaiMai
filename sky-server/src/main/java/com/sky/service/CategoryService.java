package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;

import java.lang.reflect.Type;
import java.util.List;

public interface CategoryService {


    /**
     * 菜品分页查询
     * @param categoryPageQueryDTO
     * @return
     */
    PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     * 新增分类
     * @param categoryDTO
     */
    void save(CategoryDTO categoryDTO);

    /**
     * 启用禁用
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);

    /**
     * 菜品删除
     * @param id
     */
    void delectById(Long id);

    /**
     * id分类修改
     * @param categoryDTO
     */
    void update(CategoryDTO categoryDTO);


    /**
     * 根据分类查询
     * @param type
     * @return
     */
    List<Category> list(Integer type);
}
