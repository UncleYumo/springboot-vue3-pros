package com.itheima.mapper

import com.github.pagehelper.Page
import com.itheima.pojo.Article
import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

/**
 * @author uncle_yumo
 * @fileName ArticleMapper
 * @createDate 2024/11/1 November
 * @school 无锡学院
 * @studentID 22344131
 * @description
 */

@Mapper
interface ArticleMapper {


    // 新增文章
    @Insert("insert into article" +
            "(title, content, cover_img, state, category_id, create_user, create_time, update_time) values " +
            "(#{title}, #{content}, #{coverImg}, #{state}, #{categoryId}, #{createUser}, now(), now())")
    fun add(article: Article)

    // 条件查询
    fun list(categoryId: Int?, state: String?, userId: Int): List<Article>

    // 根据文章id查询文章详情
    @Select("select * from article where id = #{id}")
    fun detail(id: Int): Article?

    // 根据id删除文章
    @Delete("delete from article where id = #{id}")
    fun delete(id: Int)

    @Select("select * from article")
    fun listAll() : List<Article>?
}