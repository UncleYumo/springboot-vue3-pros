package com.itheima.mapper

import com.itheima.pojo.User
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

/**
 * @author uncle_yumo
 * @fileName UserMapper
 * @createDate 2024/10/25 October
 * @school 无锡学院
 * @studentID 22344131
 * @description
 */

@Mapper
interface UserMapper {
    @Select("select * from mybatis.user where id = #{id}")
    fun findById(id: Int): User?
}