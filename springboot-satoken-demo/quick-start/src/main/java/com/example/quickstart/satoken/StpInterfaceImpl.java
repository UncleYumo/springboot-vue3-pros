package com.example.quickstart.satoken;

import cn.dev33.satoken.stp.StpInterface;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author uncle_yumo
 * @fileName StpInterfaceImpl
 * @createDate 2025/3/6 March
 * @school 无锡学院
 * @studentID 22344131
 * @description
 */

@Component
public class StpInterfaceImpl implements StpInterface {
    @Override
    public List<String> getPermissionList(Object loginId, String s) {
        List<String> list = new ArrayList<String>();
        list.add(loginId.toString());
        list.add("user.add");
        list.add("user.delete");
        list.add("user.get");
        if (loginId.toString().equals("1001")) {
            list.add("art.*");
        } else {
            list.add("art.get");
        }
        return list;
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        ArrayList<String> list = new ArrayList<>();
        list.add("admin");
        list.add("super-admin");
        return list;
    }
}
