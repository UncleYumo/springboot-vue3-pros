package com.itheima.controller

import com.itheima.pojo.Category
import com.itheima.pojo.ResultInfo
import com.itheima.service.CategoryService
import com.uncleyumo.utils.Color_Print_Utils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.time.format.DateTimeFormatter

/**
 * @author uncle_yumo
 * @fileName CategoryController
 * @createDate 2024/10/31 October
 * @school 无锡学院
 * @studentID 22344131
 * @description
 */

@RestController
@RequestMapping("/category")
class CategoryController {

    @Autowired
    lateinit var categoryService: CategoryService

    @PostMapping
    fun add(@RequestBody category: Category): ResultInfo {
        Color_Print_Utils.getInstance().printlnYellow("\n请求路径: /category | 请求方法: POST\n请求参数: $category\n")
        if (category.categoryName.isNullOrBlank() || category.categoryAlias.isNullOrBlank()) {
            return ResultInfo.error("分类名称或别名不能为空")
        }
        categoryService.add(category)
        return ResultInfo.success()
    }

    @GetMapping
    fun list(): ResultInfo {
        Color_Print_Utils.getInstance().printlnYellow("\n请求路径: /category | 请求方法: GET\n")
        val list: List<Category> = categoryService.list()
        return ResultInfo.success(list.apply {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
//            this.map { category ->
//                category.createTime = category.createTime?.format(formatter)
//                category.updateTime = category.updateTime?.format(formatter)
//            }
            this.map { category ->
                category.copy(
                    createTime = category.createTime?.format(formatter),
                    updateTime = category.updateTime?.format(formatter)
                )
            }
        })
    }

    @GetMapping("detail")
    fun detail(id: Int): ResultInfo {
        Color_Print_Utils.getInstance()
            .printlnYellow("\n请求路径: /category/detail | 请求方法: GET\n请求参数: id=$id\n")
        return ResultInfo.success((categoryService.findById(id) as Category).apply {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            createTime = createTime?.format(formatter)
            updateTime = updateTime?.format(formatter)
        })
    }

    @PutMapping
    fun update(@RequestBody category: Category): ResultInfo {
        Color_Print_Utils.getInstance().printlnYellow("\n请求路径: /category | 请求方法: PUT\n请求参数: $category\n")
        category.apply {
            if (this.categoryName.isNullOrBlank() || this.categoryAlias.isNullOrBlank() || this.id == null) return ResultInfo.error(
                "参数不完整"
            )
            if (this.categoryName?.matches(Regex("^[a-zA-Z0-9_\u4e00-\u9fa5]{2,10}$")) == false || this.categoryAlias?.matches(
                    Regex("^[a-zA-Z0-9_\u4e00-\u9fa5]{2,10}$")
                ) == false
            ) {
                return ResultInfo.error("分类名称或别名不符合规范 | 名称/别名只能包含字母、数字、下划线、中文、英文，长度在2-10之间")
            }
        }
        categoryService.update(category)
        return ResultInfo.success()
    }

    @DeleteMapping
    fun delete(id: Int) : ResultInfo {
        Color_Print_Utils.getInstance().printlnYellow("\n请求路径: /category | 请求方法: DELETE\n请求参数: id=$id\n")
        categoryService.delete(id)
        return ResultInfo.success()
    }
}