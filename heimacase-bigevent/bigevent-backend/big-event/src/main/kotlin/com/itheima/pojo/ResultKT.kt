package com.itheima.pojo

/**
 * 用于封装统一的响应结果
 * @property code 响应码，1 代表成功; 0 代表失败
 * @property msg 响应信息，描述字符串
 * @property data 返回的数据，可以是任意类型
 * @constructor 创建一个 Result_KT 实例
 */

data class ResultKT(
    var code: Int = 0,             // 响应码，默认值为0
    var msg: String = "",          // 响应信息，默认值为空字符串
    var data: Any? = null          // 返回的数据，默认值为null
) {
    companion object {
        fun success(): ResultKT = ResultKT(code = 1, msg = "success")
        fun success(data: Any?): ResultKT = ResultKT(code = 1, msg = "success", data = data)
        fun error(msg: String): ResultKT = ResultKT(code = 0, msg = msg)
    }
}
