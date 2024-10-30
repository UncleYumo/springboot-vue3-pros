package com.itheima.utils

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

object Md5Util {
    /**
     * 默认的密码字符串组合，用来将字节转换成 16 进制表示的字符,apache校验下载的文件的正确性用的就是默认的这个组合
     */
    private var hexDigits = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f')
    private var messagedigest: MessageDigest? = null

    init {
        try {
            messagedigest = MessageDigest.getInstance("MD5")
        } catch (nsaex: NoSuchAlgorithmException) {
            System.err.println(Md5Util::class.java.getName() + "初始化失败，MessageDigest不支持MD5Util。")
            nsaex.printStackTrace()
        }
    }

    /**
     * 生成字符串的md5校验值
     *
     * @param s
     * @return
     */
    fun getMD5String(s: String): String {
        return getMD5String(s.toByteArray())
    }

    /**
     * 判断字符串的md5校验码是否与一个已知的md5码相匹配
     *
     * @param password  要校验的字符串
     * @param md5PwdStr 已知的md5校验码
     * @return
     */
    fun checkPassword(password: String, md5PwdStr: String): Boolean {
        val s = getMD5String(password)
        return s == md5PwdStr
    }

    fun getMD5String(bytes: ByteArray?): String {
        messagedigest!!.update(bytes)
        return bufferToHex(messagedigest!!.digest())
    }

    private fun bufferToHex(bytes: ByteArray, m: Int = 0, n: Int = bytes.size): String {
        val stringbuffer = StringBuffer(2 * n)
        val k = m + n
        for (l in m until k) {
            appendHexPair(bytes[l], stringbuffer)
        }
        return stringbuffer.toString()
    }

    private fun appendHexPair(bt: Byte, stringbuffer: StringBuffer) {
        val c0 = hexDigits[bt.toInt() and 0xf0 shr 4] // 取字节中高 4 位的数字转换, >>>
        // 为逻辑右移，将符号位一起右移,此处未发现两种符号有何不同
        val c1 = hexDigits[bt.toInt() and 0xf] // 取字节中低 4 位的数字转换
        stringbuffer.append(c0)
        stringbuffer.append(c1)
    }
}
