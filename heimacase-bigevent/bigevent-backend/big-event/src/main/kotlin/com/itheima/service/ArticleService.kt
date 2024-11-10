package com.itheima.service

import com.itheima.pojo.Article
import com.itheima.pojo.PageBean

/**
 * @author uncle_yumo
 * @fileName ArticleService
 * @createDate 2024/11/1 November
 * @school 无锡学院
 * @studentID 22344131
 * @description
 */

interface ArticleService {

    // 新增文章
    fun add(article: Article)

    // 根据id查询文章-条件分页查询
    fun list(pageNum: Int, pageSize: Int, categoryId: Int?, state: String?): PageBean<Article>?

    // 根据id查询文章
    fun detail(id: Int): Article?
    fun delete(id: Int)

    // 查询所有文章（隐藏的测试接口）
    fun listAll(): List<Article>?
}