package com.itheima.pojo

import java.time.LocalDateTime

class Article (
    val id: Int? = null, //主键ID

    val title: String? = null, //文章标题
    val content: String? = null, //文章内容

    val coverImg: String? = null, //封面图像
    val state: String? = null, //发布状态 已发布|草稿

    val categoryId: Int? = null, //文章分类id
    val createUser: Int? = null, //创建人ID

    val createTime: LocalDateTime? = null, //创建时间
    val updateTime: LocalDateTime? = null //更新时间
)
