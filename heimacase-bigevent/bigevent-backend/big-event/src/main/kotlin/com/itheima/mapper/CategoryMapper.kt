package com.itheima.mapper

import com.itheima.pojo.Category
import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

/**
 * @author uncle_yumo
 * @fileName CategoryMapper
 * @createDate 2024/10/31 October
 * @school 无锡学院
 * @studentID 22344131
 * @description
 */

@Mapper
interface CategoryMapper {

    // 添加分类
    @Insert("insert into category(category_name, category_alias, create_user, create_time, update_time) " +
            "VALUES (#{categoryName}, #{categoryAlias}, #{createUser}, #{createTime}, #{updateTime})")
    fun add(category: Category)

    // 查询所有分类
    @Select("select * from category where create_user = #{id}")
    fun list(id: Int): List<Category>

    // 根据id查询分类
    @Select("select * from category where id = #{id}")
    fun findById(id: Int): Category

    // 根据id更新分类
    @Select("update category set category_name = #{categoryName}, category_alias = #{categoryAlias}, update_time = now() where id = #{id}")
    fun update(category: Category)

    // 根据id删除分类
    @Delete("delete from category where id = #{id}")
    fun delete(id: Int)

}