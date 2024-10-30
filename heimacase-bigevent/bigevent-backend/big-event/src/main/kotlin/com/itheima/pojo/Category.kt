package com.itheima.pojo

import java.time.LocalDateTime

data class Category(
    val id: Int? = null, //主键ID
    val categoryName: String? = null, //分类名称
    val categoryAlias: String? = null, //分类别名
    val createUser: Int? = null, //创建人ID
    val createTime: LocalDateTime? = null, //创建时间
    val updateTime: LocalDateTime? = null //更新时间
)
