package com.example.quickstart.entity;

import lombok.*;

import java.io.Serializable;

/**
 * @author uncle_yumo
 * @fileName User
 * @createDate 2025/3/6 March
 * @school 无锡学院
 * @studentID 22344131
 * @description
 */

@Data
@Builder(access = AccessLevel.PUBLIC) // 将Builder的访问权限设置为public
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String username;
    private String password;
}