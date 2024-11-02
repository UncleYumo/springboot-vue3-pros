package com.itheima.pojo

import java.time.LocalDateTime

data class Category(
    var id: Int? = null, //主键ID
    var categoryName: String? = null, //分类名称
    var categoryAlias: String? = null, //分类别名
    var createUser: Int? = null, //创建人ID
    var createTime: String? = null, //创建时间
    var updateTime: String? = null //更新时间
)
