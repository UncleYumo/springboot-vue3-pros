package com.itheima

import com.alibaba.fastjson2.JSON
import com.alibaba.fastjson2.JSONArray
import com.alibaba.fastjson2.parseObject
import com.alibaba.fastjson2.toJSONString
import com.itheima.pojo.ResultKT
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.core.StringRedisTemplate
import java.util.concurrent.TimeUnit

/**
 * @author uncle_yumo
 * @fileName RedisTest
 * @createDate 2024/11/6 November
 * @school 无锡学院
 * @studentID 22344131
 * @description
 */

@SpringBootTest
class RedisTest {

    @Autowired
    lateinit var stringRedisTemplate : StringRedisTemplate

//    @Test
    fun testSet() {
        val operations = stringRedisTemplate.opsForValue()
        operations.set(
            "name",
            ResultKT.success("After 15s the Data will be deleted").toJSONString(),
            15,
            TimeUnit.SECONDS  // 设置过期时间的单位为秒（枚举类TimeUnit）
        )
        // 将序列化后的对象取出，并反序列化
        val resultKT = operations.get("name")
        val result = resultKT.parseObject<ResultKT>() as ResultKT
        println(result.toJSONString())
    }
}