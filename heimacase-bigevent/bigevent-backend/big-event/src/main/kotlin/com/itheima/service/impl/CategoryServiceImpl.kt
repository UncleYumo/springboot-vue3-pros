package com.itheima.service.impl

import com.itheima.mapper.CategoryMapper
import com.itheima.pojo.Category
import com.itheima.service.CategoryService
import com.itheima.utils.ThreadLocalUtil
import com.uncleyumo.utils.Color_Print_Utils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

/**
 * @author uncle_yumo
 * @fileName CategoryServiceImpl
 * @createDate 2024/10/31 October
 * @school 无锡学院
 * @studentID 22344131
 * @description
 */

@Service
class CategoryServiceImpl: CategoryService {

    @Autowired
    lateinit var categoryMapper: CategoryMapper
    override fun add(category: Category) {
        categoryMapper.add(category.apply {
            createTime = LocalDateTime.now().toString()
            updateTime = LocalDateTime.now().toString()
            createUser = ((ThreadLocalUtil.get() as Map<*, *>)["id"] as String).toInt()
        })
    }

    // 列表查询获得所有分类
    override fun list(): List<Category> {
        return categoryMapper.list(((ThreadLocalUtil.get() as Map<*, *>)["id"] as String).toInt())
    }

    override fun findById(id: Int): Category {
        return categoryMapper.findById(id)?: throw Exception("分类不存在")
    }

    override fun update(category: Category) {
//        Color_Print_Utils.getInstance().printlnPurple("更新分类 | CategoryServiceImpl | update()")
        categoryMapper.update(category)
    }

    override fun delete(id: Int) {
        categoryMapper.delete(id)
    }
}