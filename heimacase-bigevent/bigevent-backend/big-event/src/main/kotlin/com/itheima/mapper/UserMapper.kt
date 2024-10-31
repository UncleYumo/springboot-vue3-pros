package com.itheima.mapper

import com.itheima.pojo.User
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update

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


    // 更新用户信息
    @Update("update user set nickname = #{nickname}, email = #{email}, update_time = #{updateTime} where id = #{id}")
    fun update(user: User)

    // 更新用户头像
    @Update("update user set user_pic = #{avatarUrl}, update_time = now() where id = #{id}")
    fun updateAvatar(avatarUrl: String, id: Int)

    // 更新密码
    @Update("update user set password = #{mD5String}, update_time = now() where id = #{id}")
    fun updatePwd(mD5String: String, id: Int)

}