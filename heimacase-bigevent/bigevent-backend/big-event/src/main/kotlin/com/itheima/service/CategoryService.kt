package com.itheima.service

import com.itheima.pojo.Category
import org.apache.ibatis.annotations.Insert
import org.springframework.stereotype.Service

/**
 * @author uncle_yumo
 * @fileName CategoryService
 * @createDate 2024/10/31 October
 * @school 无锡学院
 * @studentID 22344131
 * @description
 */


interface CategoryService {

    // 新增文章分类
    fun add(category: Category)

    // 查询所有文章分类
    fun list(): List<Category>

    // 根据id查询分类
    fun findById(id: Int): Any

    // 根据id更新分类
    fun update(category: Category)

    // 根据id删除文章分类
    fun delete(id: Int)
}