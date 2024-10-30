package com.itheima.mapper

import com.itheima.pojo.User
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

/**
 * @author uncle_yumo
 * @fileName UserMapper
 * @createDate 2024/10/27 October
 * @school 无锡学院
 * @studentID 22344131
 * @description
 */


@Mapper  // 标注为mybatis的mapper接口
interface UserMapper {

    // 根据用户名查询用户
    @Select("SELECT * FROM user WHERE username = #{username}")
    fun findByUserName(username: String): User?

    // 添加用户 username,pwd(md5加密)
    @Select("INSERT INTO user(username, password, create_time, update_time) " +
            "VALUES(#{username},#{pwd}, now(), now())")
    fun addUser(username: String, pwd: String)

}