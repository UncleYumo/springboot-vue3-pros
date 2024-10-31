package com.itheima.utils

/**
 * @author uncle_yumo
 * @fileName ThreadLocalUtil
 * @createDate 2024/10/30 October
 * @school 无锡学院
 * @studentID 22344131
 * @description
 */

class ThreadLocalUtil {
    companion object {
        private val THREAD_LOCAL = ThreadLocal<Any>()

        fun set(obj: Any) {
            THREAD_LOCAL.set(obj)
        }

        fun get(): Any? {
            return THREAD_LOCAL.get()
        }

        fun remove() {
            THREAD_LOCAL.remove()
        }
    }
}