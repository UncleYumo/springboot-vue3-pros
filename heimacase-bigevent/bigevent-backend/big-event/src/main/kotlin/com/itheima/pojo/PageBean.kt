package com.itheima.pojo

/**
 * @author uncle_yumo
 * @fileName PageBean
 * @createDate 2024/11/1 November
 * @school 无锡学院
 * @studentID 22344131
 * @description
 */

data class PageBean<T>(
    var total: Long? = null,  // 总记录数
    var items: List<T>? = null  // 当前页数据
)