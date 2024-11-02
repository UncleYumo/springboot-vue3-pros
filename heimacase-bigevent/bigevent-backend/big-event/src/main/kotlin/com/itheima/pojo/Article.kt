package com.itheima.pojo

import java.time.LocalDateTime

data class Article (
    var id: Int?, //主键ID
    var title: String? = null, //文章标题
    var content: String? = null, //文章内容
    var coverImg: String? = null, //封面图像
    var state: String? = null, //发布状态 已发布|草稿
    var categoryId: Int? = null, //文章分类id
    var createUser: Int? = null, //创建人ID
    var createTime: String? = null, //创建时间
    var updateTime: String? = null //更新时间
)
