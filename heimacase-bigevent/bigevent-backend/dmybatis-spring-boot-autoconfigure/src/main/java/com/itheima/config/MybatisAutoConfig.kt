package com.itheima.config

import org.apache.ibatis.annotations.Mapper
import org.mybatis.spring.SqlSessionFactoryBean
import org.mybatis.spring.mapper.MapperScannerConfigurer
import org.springframework.beans.factory.BeanFactory
import org.springframework.beans.factory.parsing.BeanEntry
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.AutoConfigurationPackages
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import javax.sql.CommonDataSource
import javax.sql.DataSource

/**
 * @author uncle_yumo
 * @fileName MybatisAutoConfig
 * @createDate 2024/10/26 October
 * @school 无锡学院
 * @studentID 22344131
 * @description
 */

@AutoConfiguration  // 表示这是个自动配置类
open class MybatisAutoConfig {
    // SqlSessionFactoryBean 实例

//    @Bean
//    open fun dataSource(): DataSource {
//        return DataSourceBuilder.create().build()
//    }

    @Bean  // 声明为 Bean
    open fun sqlSessionFactoryBean(dataSource: DataSource): SqlSessionFactoryBean {
        return SqlSessionFactoryBean().apply {
            setDataSource(dataSource)
        }
    }

    @Bean
    // MapperScannerConfigurer 实例
    open fun mapperScannerConfigurer(beanFactory: BeanFactory): MapperScannerConfigurer {
        val packageList = AutoConfigurationPackages.get(beanFactory)
        return MapperScannerConfigurer().apply {
            // 设置扫描的包路径：启动类所在的包及其子包
            setBasePackage(packageList[0])
            // 设置扫描的注解】
            setAnnotationClass(Mapper::class.java)
        }
    }
}