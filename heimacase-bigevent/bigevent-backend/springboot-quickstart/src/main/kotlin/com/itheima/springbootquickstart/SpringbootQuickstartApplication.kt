package com.itheima.springbootquickstart

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

// Activation class
@SpringBootApplication
class SpringbootQuickstartApplication

fun main(args: Array<String>) {
	runApplication<SpringbootQuickstartApplication>(*args)
}
