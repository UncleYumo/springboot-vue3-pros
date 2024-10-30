package com.itheima.utils

import com.uncleyumo.utils.Color_Print_Utils
import java.awt.Color
import java.awt.Font
import java.awt.Graphics
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.OutputStream
import java.util.*
import javax.imageio.ImageIO

/**
 * @author uncle_yumo
 * @fileName CreateImageCode
 * @createDate 2024/10/22 October
 * @school 无锡学院
 * @studentID 22344131
 * @description
 */

class CreateImageCode {
    // 图片的宽度
    private var width = 160

    // 图片的高度
    private var height = 40

    // 验证码字符个数
    private var codeCount = 4

    // 验证码干扰线数
    private var lineCount = 20

    // 验证码字符串
    private var code: String? = null

    // 验证码图片Buffer
    private var buffImg: BufferedImage? = null
    private val random = Random()  // 随机数对象，用于生成验证码

    // 默认构造函数，创建一个验证码图片
    constructor() {
        createImage()
    }

    // 构造函数，可以指定宽度和高度
    constructor(width: Int, height: Int) {
        this.width = width
        this.height = height
        createImage()
    }

    // 构造函数，可以指定宽度、高度和验证码字符个数
    constructor(width: Int, height: Int, codeCount: Int) {
        this.width = width
        this.height = height
        this.codeCount = codeCount
        createImage()
    }

    // 构造函数，可以指定宽度、高度、验证码字符个数和干扰线数
    constructor(width: Int, height: Int, codeCount: Int, lineCount: Int) {
        this.width = width
        this.height = height
        this.codeCount = codeCount
        this.lineCount = lineCount
        createImage()
    }

    // 生成验证码图片
    private fun createImage() {
        val fontWidth = width / codeCount // 字体的宽度
        val fontHeight = height - 5 // 字体的高度
        val codeY = height - 8 // 字符的Y坐标

        // 创建图像buffer
        buffImg = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
        val g: Graphics = buffImg!!.graphics
        // 设置背景色
        g.color = getRandColor(200, 250)
        g.fillRect(0, 0, width, height)
        // 设置字体
        val font = Font("Fixedsys", Font.BOLD, fontHeight)
        g.font = font

        // 设置干扰线
        for (i in 0 until lineCount) {
            val xs = random.nextInt(width)
            val ys = random.nextInt(height)
            val xe = xs + random.nextInt(width)
            val ye = ys + random.nextInt(height)
            g.color = getRandColor(1, 255)
            g.drawLine(xs, ys, xe, ye) // 画干扰线
        }

        // 添加噪点
        val yawpRate = 0.01f // 噪声率
        val area = (yawpRate * width * height).toInt()
        for (i in 0 until area) {
            val x = random.nextInt(width)
            val y = random.nextInt(height)
            buffImg!!.setRGB(x, y, random.nextInt(255)) // 添加噪点
        }

        val str1 = randomStr(codeCount) // 得到随机字符
        this.code = str1 // 保存验证码字符串
        for (i in 0 until codeCount) {
            val strRand = str1[i].toString() // 获取单个字符
            g.color = getRandColor(1, 255)
            g.drawString(strRand, i * fontWidth + 3, codeY) // 绘制验证码字符
        }
    }

    // 得到随机字符
    private fun randomStr(n: Int): String {
        val str1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890" // 可能的字符
        var str2 = ""
        val len = str1.length - 1
        for (i in 0 until n) {
            val r = Math.random() * len // 随机索引
            str2 += str1[r.toInt()] // 连接随机字符
        }
        return str2
    }

    // 得到随机颜色
    private fun getRandColor(fc: Int, bc: Int): Color {
        // 给定范围获得随机颜色
        var fc = fc
        var bc = bc
        if (fc > 255) fc = 255
        if (bc > 255) bc = 255
        val r = fc + random.nextInt(bc - fc) // 随机红色值
        val g = fc + random.nextInt(bc - fc) // 随机绿色值
        val b = fc + random.nextInt(bc - fc) // 随机蓝色值
        return Color(r, g, b) // 返回颜色对象
    }

    // 得到验证码的字符串
    fun getCode(): String {
        return code!!.lowercase(Locale.getDefault()) // 返回小写的验证码
    }

    fun write(sos: OutputStream) {
        sos.run {
            ImageIO.write(buffImg, "png", this)
            close()
        }
    }


    fun testFunc() {
        // 创建一个新的验证码对象，使用默认宽度和高度
        val createImageCode = CreateImageCode()

        // 获取验证码的BufferedImage
        val bufferedImage = createImageCode.buffImg

        // 输出验证码图片到文件
        try {
            ImageIO.write(
                bufferedImage,
                "png",
                // E:\Dev_work\Web_Dev\springboot-pros-practise\yumopan\src\main\resources\data\ImageCodes\captcha.png
                File("captcha.png")
            )
            Color_Print_Utils.getInstance().printlnYellow("验证码图片已生成：captcha.png")
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // 获取生成的验证码字符串
        val code = createImageCode.getCode()
        println("生成的验证码是：$code")
    }

    fun getImageAsBase64(): String {
        val outputStream = ByteArrayOutputStream()  // 输出流，用于保存图片
        ImageIO.write(buffImg, "png", outputStream)  // 将图片写到输出流中
        val imageBytes = outputStream.toByteArray()  // 得到图片的字节数组
        outputStream.close()  // 关闭输出流
        return Base64.getEncoder().encodeToString(imageBytes)  // 将字节数组编码为Base64字符串
    }
}
