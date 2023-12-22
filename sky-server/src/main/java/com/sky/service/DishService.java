package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

import java.util.List;

public interface DishService {


    /**
     * 新增菜品及口味保存
     * @param dishDTO
     */
    public void saveWithFlavar(DishDTO dishDTO);

    /**
     * 菜品分页查询
     *
     * @param dishPageQueryDTO
     * @return
     */
    PageResult page(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 批量删除
     * @param ids
     */
    void delectBatch(List<Long> ids);

    /**
     * 根据id查询菜品
     * @param id
     * @return
     */
    DishVO getByIdWitchFlavor(Long id);

    /**
     * 修改菜品
     * @param dishDTO
     */
    void updateWitchFlavor(DishDTO dishDTO);

    /**
     * 商品启停
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);
}
