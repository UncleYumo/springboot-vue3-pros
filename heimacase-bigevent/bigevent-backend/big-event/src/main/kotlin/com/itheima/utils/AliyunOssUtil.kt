package com.itheima.utils

import com.aliyun.oss.OSSClientBuilder
import com.uncleyumo.utils.Color_Print_Utils
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.io.InputStream

/**
 * @author uncle_yumo
 * @fileName AliyunOssUtil
 * @createDate 2024/11/2 November
 * @school 无锡学院
 * @studentID 22344131
 * @description
 */

@Component
class AliyunOssUtil {
    @Value("\${aliyun.oss.endpoint}")
    private lateinit var endpoint: String

    @Value("\${aliyun.oss.bucket-name}")
    private lateinit var bucketName: String

    @Value("\${aliyun.oss.file-path}")
    private lateinit var filePath: String

    @Value("\${aliyun.oss.accessKeyId}")
    private lateinit var accessKeyId: String

    @Value("\${aliyun.oss.accessKeySecret}")
    private lateinit var accessKeySecret: String

    @Value("\${uncleyumo.upload-path}")
    private lateinit var uploadFilePath: String

    val instance: AliyunOssUtil by lazy { AliyunOssUtil() }  // 单例模式
    fun upload(fileName: String, inputStream: InputStream) : String {
        val startTime = System.currentTimeMillis()

        val ossClient = OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret)
        ossClient.putObject(bucketName, filePath + fileName, inputStream)
        ossClient.shutdown()  // 关闭OSSClient

        Color_Print_Utils.getInstance().printlnGreen(">>>>>\t${"OSS UPLOAD USING TIME: ${System.currentTimeMillis() - startTime}ms".padEnd(40)}\t>>>>>")
        return "https://${bucketName}.${endpoint.replace("https://", "")}/$filePath$fileName"
    }
}