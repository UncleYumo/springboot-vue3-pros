package com.itheima

import com.uncleyumo.utils.Color_Print_Utils
import org.junit.jupiter.api.Test

/**
 * @author uncle_yumo
 * @fileName ThreadLocalTest
 * @createDate 2024/10/30 October
 * @school 无锡学院
 * @studentID 22344131
 * @description
 */

class ThreadLocalTest {
//    @Test
    fun testThreadLocal_SetAndGet() {
        // 提供一个ThreadLocal对象
        val tl = ThreadLocal<Any>()

        // 开启两个线程
        Thread {
            tl.set("萧炎")
            Color_Print_Utils.getInstance().printlnYellow("Current Thread: ${Thread.currentThread().name} | ${tl.get() as String}")
            Color_Print_Utils.getInstance().printlnYellow("Current Thread: ${Thread.currentThread().name} | ${tl.get()}")
            Color_Print_Utils.getInstance().printlnYellow("Current Thread: ${Thread.currentThread().name} | ${tl.get()}")
        }.run {
            name = "黄色"
            start()
        }

        Thread {
            tl.set("药尘")
            Color_Print_Utils.getInstance().printlnPurple("Current Thread: ${Thread.currentThread().name} | ${tl.get()}")
            Color_Print_Utils.getInstance().printlnPurple("Current Thread: ${Thread.currentThread().name} | ${tl.get()}")
            Color_Print_Utils.getInstance().printlnPurple("Current Thread: ${Thread.currentThread().name} | ${tl.get()}")
        }.run {
            name = "紫色"
            start()
        }
    }
}