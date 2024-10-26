package com.uncleyumo.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

/**
 * @author uncle_yumo
 * @fileName YumoController
 * @createDate 2024/10/26 October
 * @school 无锡学院
 * @studentID 22344131
 * @description
 */

//@RestController  // This annotation indicates that this class is a controller
class YumoController {

    @PostMapping("/uncleyumo")
    fun postYumo(@RequestBody yumo: Yumo): String {
        return "Hello, ${yumo.name?: "unknown"}! You are ${yumo.age?: "unknown"} years old."
    }

    class Yumo(
        var name: String? = null,
        var age: Int? = null
    ) {  }

    @GetMapping("/uncleyumo/{color}")
    fun getYumo(@PathVariable color: String): String {
        val args = arrayOf(color)
//        Color_Print_Utils.main(args)
        return "Hello, $color!"
    }
}