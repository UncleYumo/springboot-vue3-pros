<script setup>
import {
    Document,
    Management,
    UserFilled,
    WarningFilled,
    User,
    Edit,
    Crop
} from '@element-plus/icons-vue'

</script>

<template>
    <div class="common-layout">
        <el-container>
            <el-aside>
                <div class="menu-container-div">

                    <div class="menu-title">

                        <div style="height: 25px;"></div>

                        <div class="menu-title-content">

                            <div style="height: 25px;"></div>

                            <span style="
                                /* color: white; */
                                font-size: 40px;
                                font-weight: bold;
                                text-shadow: 2px 2px 2px #000000;
                                ">
                                    <span style="color: #fff;">羽沫</span>
                                    <span style="color: tomato;">大事件</span>
                            </span><br>
                            <span style="
                                color: white;
                                margin-left: 130px;
                                font-size: 14px;
                                font-weight: bold;
                                text-shadow: 2px 2px 2px #000000;
                                ">Yumo News</span>
                        </div>

                    </div>

                    <!-- <el-divider /> -->

                    <div class="menu-content-div">
                        <el-menu active-text-color="#ffd04b" background-color="#636466" class="el-menu-vertical"
                            default-active="2" text-color="#fff" @open="" @close="" router>

                            <el-menu-item index="/article/category">
                                <el-icon>
                                    <Document />
                                </el-icon>
                                <span style="font-size:larger; font-weight: 600;
                                text-shadow: 1px 2px 2px #000000">文章分类</span>
                            </el-menu-item>

                            <el-menu-item index="/article/manage">
                                <el-icon>
                                    <Management />
                                </el-icon>
                                <span style="font-size:larger; font-weight: 600;
                                text-shadow: 2px 2px 2px #000000">文章管理</span>
                            </el-menu-item>

                            <el-sub-menu index="/user-center">
                                <template #title>
                                    <el-icon>
                                        <UserFilled />
                                    </el-icon>
                                    <span style="font-size:larger; font-weight: 600;
                                    text-shadow: 1px 2px 2px #000000">个人中心</span>
                                </template>
                                <el-menu-item index="/user/avatar">
                                    <el-icon>
                                        <User></User>
                                    </el-icon>
                                    <span style="font-size:medium; font-weight: 500;
                                        text-shadow: 1px 2px 2px #000000">基本资料</span>
                                </el-menu-item>

                                <el-menu-item index="/user/info">
                                    <el-icon>
                                        <Crop></Crop>
                                    </el-icon>
                                    <span style="font-size:medium; font-weight: 500;
                                        text-shadow: 1px 2px 2px #000000">更换头像</span>
                                </el-menu-item>

                                <el-menu-item index="/user/reset-password">
                                    <el-icon>
                                        <Edit></Edit>
                                    </el-icon>
                                    <span style="font-size:medium; font-weight: 500;
                                        text-shadow: 1px 2px 2px #000000">重置密码</span>
                                </el-menu-item>

                            </el-sub-menu>

                            <el-menu-item index="/login">
                                <el-icon>
                                    <WarningFilled />
                                </el-icon>
                                <span style="font-size:larger; font-weight: 600;
                                text-shadow: 1px 2px 2px #000000">切换账号</span>

                            </el-menu-item>

                            <!-- <el-divider /> -->

                        </el-menu>
                    </div>
                </div>

            </el-aside>

            <el-container>

                <el-header style="
                    /* border: 2px solid black; */
                    background-color: #636466;
                    height: 80px;
                    padding-top: 25px;
                ">
                    <div style="
                        width: 100%;
                        height: 100%;
                        display: flex;
                        justify-content: center;
                        align-items: center;
                        background-color: white;
                        border-radius: 10px;
                        box-shadow: inset 6px 6px 6px #464748;
                    ">

                    </div>
                </el-header>


                <el-main style="
                    background-color: #636466;
                    /* border: #000000 2px solid; */
                ">

                    <div style="height: 100%;
                    width: 100%;
                    display: flex;
                    justify-content: center;
                    align-items: center;
                    border-radius: 7px;
                    background: #636466;    
                    box-shadow:  7px 7px 10px #464748,
                    -7px -7px 10px #808184;">

                        <div style="
                            height: 94%;
                            width:96%;
                            border-radius: 12px;
                            background-color: #fff;
                            display: flex;  /* 子元素水平排列 */
                            flex-direction: column;  /* 子元素垂直排列 */
                            align-items: center;
                            justify-content: center;
                            box-shadow: inset 6px 6px 6px #464748,
                                inset -4px -4px 4px #808184;
                            /* box-shadow:  6px 6px 6px #3d4e62, */
                                /* -6px -6px 6px #7b9ec8; */
                            ">
                            <!-- 在这里放置具体的页面内容，通过路由控制 -->
                            <RouterView></RouterView>
                        </div>

                    </div>
                </el-main>

                <el-footer>
                    <div style="width: 100%;
                        display: flex;
                        justify-content: center;
                        /* background-color: aqua; */
                        margin-bottom: 12px;
                        ">
                        <span style="
                            font-weight: normal;
                            color: white;
                            text-shadow: 1px 1px 1px #000000;">
                            羽沫大事件&nbsp;·&nbsp;Yumo&nbsp;News&nbsp;&nbsp;©2024&nbsp;&nbsp;Created&nbsp;by&nbsp;Uncle&nbsp;Yumo&nbsp;·&nbsp;All&nbsp;Rights&nbsp;Reserved
                        </span>
                    </div>
                </el-footer>

            </el-container>
        </el-container>
    </div>
</template>

<style scoped>
:root {
    --theme-color: #636466;
}

.common-layout {
    height: 100vh;
    width: 100vw;
}

.el-container {
    height: 100%;
    width: 100%;

}

.menu-container-div {

    height: 100%;
    background-color: #636466;

    .menu-title {
        padding: 10px, 10px;
        margin-bottom: 20px;
        /* background-color: aqua; */
        width: 100%;
        height: 150px;
        text-align: center;
    }
}

.el-aside {
    /* border: 2px solid black; */
    width: 310px;
}

.el-footer {
    /* border: 2px solid black; */
    background-color: #636466;
    height: 30px;
    display: flex;
    justify-content: center;
    align-items: center;
}

.menu-content-div {
    display: flex;
    flex-direction: column;
    align-items: center;
    padding-top: 5%;
    padding-bottom: 5%;
    /* background-color: aqua */
}

.el-menu-vertical {
    width: 280px;
    height: 80%;
    border-right: none;
    border-radius: 7px;
    background: #636466;
    box-shadow: 7px 7px 10px #464748,
        -7px -7px 10px #808184;
}

.menu-title-content {

    width: 280px;
    height: 120px;
    margin: 0 auto;
    border-radius: 7px;
    background: #636466;
    box-shadow: 7px 7px 10px #464748,
        -7px -7px 10px #808184;
}
</style>