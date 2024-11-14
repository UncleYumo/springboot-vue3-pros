package com.itheima.controller

import com.itheima.pojo.Article
import com.itheima.pojo.PageBean
import com.itheima.pojo.ResultInfo
import com.itheima.service.ArticleService
import com.itheima.utils.JwtUtil
import com.uncleyumo.utils.Color_Print_Utils
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.*

/**
 * @author uncle_yumo
 * @fileName ArticleController
 * @createDate 2024/10/29 October
 * @school 无锡学院
 * @studentID 22344131
 * @description
 */

@RestController
//@CrossOrigin(origins = ["http://localhost:63342"], allowCredentials = "true")
class ArticleController {

//    @Value("\${uncleyumo.privacy-symbol}")
//    lateinit var privacySymbol: String

    @Autowired
    lateinit var articleService: ArticleService

    @PostMapping("/article")
    fun add(@RequestBody article: Article): ResultInfo {
        articleService.add(article.apply {
            if (title.isNullOrEmpty() || content.isNullOrEmpty() || coverImg.isNullOrEmpty() || state.isNullOrEmpty()  || categoryId == null) {
                return ResultInfo.error("必要参数不能为空")
            }
            // title必须为1-10个非空字符
            if (!title!!.matches(Regex("^.{1,10}\$"))) return ResultInfo.error("文章标题必须为1-10个非空字符")
            // coverImg必须为图片url
            if (!coverImg!!.matches(Regex("^https?://.*\\.(jpg|png|jpeg|gif)$"))) return ResultInfo.error("文章封面必须为图片url")
            // state必须为“已发布”或“草稿”这两种状态之一
            if (state!= "已发布" && state!= "草稿") return ResultInfo.error("文章状态必须为“已发布”或“草稿”")
        })
        return ResultInfo.success()
    }

    @GetMapping("/article")
    fun list(
        pageNum: Int,  // 当前页码
        pageSize: Int,  // 每页显示的数量
        categoryId: Int?,  // 文章分类id
        state: String?,  // 文章状态
    ) : ResultInfo {
        val list: PageBean<Article> = articleService.list(pageNum, pageSize, categoryId, state)?: return ResultInfo.error("sorry, 没有找到相关文章")
        return ResultInfo.success(list)
    }

    // 获取文章详情
    @GetMapping("/article/detail")
    fun detail(id: Int) : ResultInfo {
        return ResultInfo.success(articleService.detail(id)?.apply {
            createUser = -1 // 隐藏用户信息
        }?: return ResultInfo.error("sorry, 没有找到相关文章"))
    }

    // 根据文章id删除文章
    @DeleteMapping("/article")
    fun delete(id: Int) : ResultInfo {
        articleService.delete(id)
        return ResultInfo.success()
    }

    @GetMapping("/article/getAll")
    fun getAll(secret: String) : ResultInfo {
        if (secret != "991650") return ResultInfo.error("此接口仅限管理员使用")
        return ResultInfo.success(articleService.listAll())
    }

}