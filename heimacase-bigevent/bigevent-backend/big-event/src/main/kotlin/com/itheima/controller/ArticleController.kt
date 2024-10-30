package com.itheima.controller

import com.itheima.pojo.ResultInfo
import com.itheima.utils.JwtUtil
import com.uncleyumo.utils.Color_Print_Utils
import jakarta.servlet.http.HttpServletResponse
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
@RequestMapping("/article")
//@CrossOrigin(origins = ["http://localhost:63342"], allowCredentials = "true")
class ArticleController {

    @GetMapping("/list")
    fun list(): ResultInfo {
        return ResultInfo.success("文章列表 获取成功")
    }
}