package com.uncleyumo.configure

import org.springframework.context.annotation.ImportSelector
import org.springframework.core.type.AnnotationMetadata
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * @author uncle_yumo
 * @fileName CommonImportSelector.kt
 * @createDate 2024/10/26 October
 * @school 无锡学院
 * @studentID 22344131
 * @description
 */

class CommonImportSelector: ImportSelector {
    override fun selectImports(importingClassMetadata: AnnotationMetadata): Array<String> {

        val inputStream = CommonImportSelector().javaClass.classLoader.getResourceAsStream("common.imports")
        val br = (inputStream?: null)?.let { InputStreamReader(it) }?.let { BufferedReader(it) }
//        val br2 = inputStream?.let { InputStreamReader(it) }?.let { BufferedReader(it) }

        var line: String? = null

        val imports = ArrayList<String>()

        if (br == null) { throw IllegalArgumentException("common.imports 文件不存在") }
        while (br.readLine().also { line = it } != null) {
            println("读取到的行：${line?: "内容为空"}")
            line?.let { imports.add(it) }
        }
        br.close()
        return imports.toTypedArray()  // 返回一个数组 TypedArray是kotlin提供的类型别名
    }

}