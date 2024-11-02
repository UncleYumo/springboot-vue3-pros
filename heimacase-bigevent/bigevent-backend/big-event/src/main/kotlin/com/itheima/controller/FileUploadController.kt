package com.itheima.controller

import com.itheima.pojo.ResultInfo
import com.itheima.utils.AliyunOssUtil
import com.uncleyumo.utils.Color_Print_Utils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.util.UUID

/**
 * @author uncle_yumo
 * @fileName FileController
 * @createDate 2024/11/1 November
 * @school 无锡学院
 * @studentID 22344131
 * @description
 */

@RestController
class FileUploadController {

    @Value("\${uncleyumo.upload-path}")
    lateinit var uploadPath: String

    @Autowired
    lateinit var ossUtil: AliyunOssUtil

    @PostMapping("/upload")
    fun upload(file: MultipartFile): ResultInfo {
        Color_Print_Utils.getInstance().printlnPurple(">>>>>\t${"POST /UPLOAD".padEnd(40)}\t>>>>>")
        val startTime = System.currentTimeMillis()
        val originalFilename = file.originalFilename ?: return ResultInfo.error("文件名不能为空")

        val fileName = UUID.randomUUID().toString() +
                originalFilename.substring(originalFilename.lastIndexOf("."))

        val filePath = ossUtil.upload(fileName, file.inputStream)
        Color_Print_Utils.getInstance()
            .printlnGreen(">>>>>\t${"POST [/UPLOAD] USING TIME : ${System.currentTimeMillis() - startTime}ms".padEnd(40)}\t>>>>>")
        return ResultInfo.success(data = filePath)
    }
}