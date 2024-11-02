package com.itheima

import com.aliyun.oss.OSSClientBuilder
import com.aliyun.oss.model.PutObjectRequest
import com.uncleyumo.utils.Color_Print_Utils
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import java.io.ByteArrayInputStream
import java.io.File
import java.io.InputStream
import java.util.*

/**
 * @author uncle_yumo
 * @fileName AliyunOssTest
 * @createDate 2024/11/2 November
 * @school 无锡学院
 * @studentID 22344131
 * @description
 */

//@SpringBootTest  // 启动测试环境
class AliyunOssTest {
    @Value("\${aliyun.oss.endpoint}")
    lateinit var endpoint: String

    @Value("\${aliyun.oss.bucket-name}")
    lateinit var bucketName: String

    @Value("\${aliyun.oss.file-path}")
    lateinit var filePath: String

    @Value("\${aliyun.oss.accessKeyId}")
    lateinit var accessKeyId: String

    @Value("\${aliyun.oss.accessKeySecret}")
    lateinit var accessKeySecret: String

    @Value("\${uncleyumo.upload-path}")
    lateinit var uploadFilePath: String

    @Test
    fun testOss() {
        val EndTime: Long = System.currentTimeMillis()
        Color_Print_Utils.getInstance().programEnd()
        val width = "Finish creating OSSClient instance".length + 10

        // 创建OSSClient实例
        val ossClient = OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret)
        Color_Print_Utils.getInstance().printlnGreen(">>>>>\t${"Finish creating OSSClient instance".padEnd(width)}\t\t>>>>>")
        val objectName = UUID.randomUUID().toString() + ".jpg"

        val loadFileTime: Long = System.currentTimeMillis()
        Color_Print_Utils.getInstance().printlnGreen(">>>>>\t${"Start loading local file".padEnd(width)}\t\t>>>>>")
        val inputStream = File(uploadFilePath.plus("lock.jpg")).inputStream()
        // 打印文件大小
        Color_Print_Utils.getInstance().printlnCyan(">>>>>\t${"File size: ${(inputStream.available().toDouble() / 1024)} KB".padEnd(width)}\t\t>>>>>")
        Color_Print_Utils.getInstance().printlnCyan(">>>>>\t${"Finish loading local file | Time: ${System.currentTimeMillis() - loadFileTime}ms".padEnd(width)}\t\t>>>>>")

        Color_Print_Utils.getInstance().printlnGreen(">>>>>\t${"Start uploading file".padEnd(width)}\t\t>>>>>")
        ossClient.putObject(bucketName, filePath.plus(objectName), inputStream)

        Color_Print_Utils.getInstance().printlnGreen(">>>>>\t${"Finish uploading file to OSS".padEnd(width)}\t\t>>>>>")
        Color_Print_Utils.getInstance().printlnCyan(">>>>>\t${"Finish all process | Total Time: ${System.currentTimeMillis() - EndTime}ms".padEnd(width)}\t\t>>>>>")

        Color_Print_Utils.getInstance().programEnd()
    }

    @Test
    fun testPrintFormate() {
        val message1 = "Finish creating OSSClient instance"
        val message2 = "End uploading file"
        val timeTaken = 611

        val width = "Finish creating OSSClient instance".length + 10
        println(">>>>>\t${message1.padEnd(width)}\t\t>>>>>")
        println(">>>>>\t${message2.padEnd(width)}\t\t>>>>>")
        println(">>>>>\t${"Finish all process | time: ${timeTaken}ms"}.padEnd(width)\t\t>>>>>")

    }

}