package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface CategoryMapper {

      /**
       * 菜品分页查询
       * @param categoryPageQueryDTO
       * @return
       */
      Page<Category> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);



      @Insert("insert into category (type, name, sort, status, create_time, update_time, create_user, update_user) values " +
              " ( #{type}, #{name}, #{sort}, #{status}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser})")
      void insert(Category category);


      /**
       * 修改数据
       * @param category
       */
      void update(Category category);

      /**
       * 根据id删除分类
       * @param id
       */
      @Delete("delete from category where id = #{id}")
      void delectById(Long id);


      /**
       * 跟据分类查询
       * @param type
       * @return
       */

      List<Category> list(Integer type);
}
