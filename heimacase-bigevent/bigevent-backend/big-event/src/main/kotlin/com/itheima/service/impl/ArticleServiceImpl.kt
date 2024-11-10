package com.itheima.service.impl

import com.github.pagehelper.Page
import com.github.pagehelper.PageHelper
import com.itheima.mapper.ArticleMapper
import com.itheima.pojo.Article
import com.itheima.pojo.PageBean
import com.itheima.service.ArticleService
import com.itheima.utils.ThreadLocalUtil
import com.uncleyumo.utils.Color_Print_Utils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author uncle_yumo
 * @fileName ArticleServiceImpl
 * @createDate 2024/11/1 November
 * @school 无锡学院
 * @studentID 22344131
 * @description
 */

@Service
class ArticleServiceImpl : ArticleService {

    @Autowired
    lateinit var articleMapper: ArticleMapper
    override fun add(article: Article) {
        val id = ((ThreadLocalUtil.get() as Map<*, *>)["id"] as String).toInt()
        Color_Print_Utils.getInstance().printlnPurple(
            "\nArticleServiceImpl add(article: Article) called\nid: $id | article: ${article.toString()}\n"
        )
        articleMapper.add(article.apply {
            createUser = id
        })
    }

    override fun list(pageNum: Int, pageSize: Int, categoryId: Int?, state: String?): PageBean<Article> {
        Color_Print_Utils.getInstance().printlnPurple("\n" +
                "pageNum: $pageNum | pageSize: $pageSize | categoryId: $categoryId | state: $state\n"
        )
        // 创建PageBean对象
        val pageBean = PageBean<Article>()

        // 开启分页查询 - PageHelper
        PageHelper.startPage<Article>(pageNum, pageSize)  // 自动生成分页查询语句

        // 调用Mapper查询方法
        val userId = ((ThreadLocalUtil.get() as Map<*, *>)["id"] as String).toInt()  // 获取当前登录用户的id

        val list = articleMapper.list(categoryId, state, userId)
        val pg = list as Page<*>  // 强转为Page<Article>

        Color_Print_Utils.getInstance().printlnPurple("\nArticleServiceImpl list(pageNum: Int, pageSize: Int, categoryId: Int?, state: String?) called\nuserId: $userId | pageNum: $pageNum | pageSize: $pageSize | categoryId: $categoryId | state: $state\n")
        return pageBean.copy(
            total = pg.total,
            items = pg.result as List<Article>
        )
    }

    override fun detail(id: Int): Article? {
        return articleMapper.detail(id)
    }

    override fun delete(id: Int) {
        articleMapper.delete(id)
    }

    override fun listAll(): List<Article>? {
        return articleMapper.listAll()
    }
}