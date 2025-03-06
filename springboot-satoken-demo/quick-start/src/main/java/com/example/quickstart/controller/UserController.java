package com.example.quickstart.controller;

import cn.dev33.satoken.annotation.SaCheckSafe;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.example.quickstart.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Objects;

/**
 * @author uncle_yumo
 * @fileName UserController
 * @createDate 2025/3/5 March
 * @school 无锡学院
 * @studentID 22344131
 * @description
 */

//@SaCheckLogin
@RestController
@RequestMapping("/acc")
public class UserController {

    // http://localhost:8081/acc/doLogin?username=admin&password=admin
    @GetMapping ("/doLogin")
//    @SaIgnore
    public SaResult doLogin(String username, String password) {
        if (!Objects.equals(username, password)) {
            return SaResult.data("用户名或密码错误");
        }
        StpUtil.login(10001);
        return SaResult.data(StpUtil.getTokenInfo());
    }

    // http://localhost:8081/acc/userInfo
    @GetMapping("userInfo")
    public SaResult userInfo() {
        return SaResult.data(new User(StpUtil.getLoginIdAsLong(), "username", "password"));
    }

    // http://localhost:8081/acc/isLogin
    @GetMapping("/isLogin")
    public SaResult isLogin() {
        return SaResult.ok("是否登录：" + StpUtil.isLogin());
    }

    // http://localhost:8081/acc/tokenInfo
    @GetMapping("/tokenInfo")
    public SaResult tokenInfo() {
        return SaResult.data(StpUtil.getTokenInfo());
    }

    // http://localhost:8081/acc/logout
    @GetMapping("/logout")
    public SaResult logout() {
        StpUtil.logout();
        return SaResult.ok();
    }

    // http://localhost:8081/acc/test
    @GetMapping("/test")
    public SaResult test() {
        // 获取：当前账号所拥有的权限集合
        StpUtil.getPermissionList();
        System.out.println("getPermissionList: " + StpUtil.getPermissionList());

        // 判断：当前账号是否含有指定权限, 返回 true 或 false
        StpUtil.hasPermission("user.add");
        System.out.println("hasPermission: " + StpUtil.hasPermission("user.add"));

        // 校验：当前账号是否含有指定权限, 如果验证未通过，则抛出异常: NotPermissionException
        StpUtil.checkPermission("art.add");

        // 校验：当前账号是否含有指定权限 [指定多个，必须全部验证通过]
        StpUtil.checkPermissionAnd("user.add", "user.delete", "user.get");

        // 校验：当前账号是否含有指定权限 [指定多个，只要其一验证通过即可]
        StpUtil.checkPermissionOr("user.add", "user.delete", "user.get");

        return SaResult.ok();
    }

    // http://localhost:8081/acc/testSaCheckLogin
    @GetMapping("/testSaCheckLogin")
    public SaResult testSaCheckLogin() {
        return SaResult.ok("测试SaCheckLogin注解");
    }

    // http://localhost:8081/acc/testSession
    @GetMapping("/testSession")
    public SaResult testSession() {
        String name = (String) StpUtil.getSession().get("name");
        if (name == null) {
            StpUtil.getSession().set("name", "session_name");
            return SaResult.data("第一次访问，session为null，请刷新页面");
        }
        return SaResult.data("欢迎回来，" + name);
    }

    @SaCheckSafe
    @GetMapping("/testSecondLevelCertification")
    public SaResult testSecondLevelCertification() {
//        if (!StpUtil.isSafe()) {
//            return SaResult.error("请先完成二级认证");
//        }
        return SaResult.ok("已完成二级认证，并执行了相关操作");
    }

    @GetMapping("/doOpenSafe")
    public SaResult doOpenSafe(String password) {
        if (!"password".equals(password)) {
            return SaResult.error("密码错误，二级认证失败");
        }
        StpUtil.openSafe(120);  // 有效期120s
        return SaResult.ok("已开启二级认证");
    }

}
