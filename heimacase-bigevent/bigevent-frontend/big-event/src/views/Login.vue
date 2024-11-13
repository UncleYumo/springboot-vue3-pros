<script setup>
import { useRouter } from 'vue-router'
import { User, Lock, Warning } from '@element-plus/icons-vue'
import { onMounted, ref } from 'vue'
import { ElNotification, ElMessage } from 'element-plus'
import { userRegisterService, userLoginService } from '@/api/user';
//控制注册与登录表单的显示， 默认显示
const isRegister = ref(false)
const router = useRouter()

const form = ref(null)

const registerData = ref({
    username: '',
    password: '',
    rePassword: ''
})

const loginData = ref({
    username: '',
    password: ''
})

const login = async () => {
    try {
        // 触发表单校验
        try {
            await form.value.validate();
        } catch (error) {
            ElMessage({
                message: '登录失败! ' + "请检查输入信息是否正确",
                type: 'warning',
            })
            return;
        }
        let result = await userLoginService(loginData.value);
        // alert('登录成功! ');
        ElNotification.success({
            title: '登录成功',
            offset: 250,
        })
        // 跳转到首页
        router.push('/')
    } catch (error) {
        ElMessage({
            message: '登录失败! ' + error.message,
            type: 'warning',
        })
    }
}

const register = async () => {
    try {
        // 触发表单校验
        try {
            await form.value.validate();
        } catch (error) {
            ElMessage({
                message: '注册失败! ' + "请检查输入信息是否正确",
                type: 'warning',
            })
            return;
        }
        let result = await userRegisterService(registerData.value);
        // alert('注册成功! ');
        ElNotification.success({
            title: '注册成功',
            message: '您现在可以登录羽沫大事件！',
            offset: 100,
        })
        // 跳转到登录页面
        isRegister.value = true
    } catch (error) {
        ElMessage({
            message: '注册失败! '+ error.message,
            type: 'warning',
        })
    }
}

const checkRePassword = (rule, value, callback) => {
    if (value === '') {
        callback(new Error('请再次确认密码'))
    } else if (value !== registerData.value.password) {
        callback(new Error('两次输入的密码不一致!'))
    } else {
        callback()  // 验证通过
    }
}

// 定义表单校验规则
const rules_register = ref({
    username: [
        { required: true, message: '请输入用户名', trigger: 'change' },
        { min: 5, max: 16, message: '用户名长度在 5 到 16 个字符', trigger: 'change' }
    ],
    password: [
        { required: true, message: '请输入密码', trigger: 'change' },
        { min: 5, max: 16, message: '密码长度在 5 到 16 个字符', trigger: 'change' }
    ],
    rePassword: [
        { validator: checkRePassword, trigger: 'change' }  // 自定义验证规则
    ]
})

// 表单校验
const rules_login = ref({
    username: [
        { required: true, message: '请输入用户名', trigger: 'change' },
        { min: 5, max: 16, message: '用户名长度在 5 到 16 个字符', trigger: 'change' }
    ],
    password: [
        { required: true, message: '请输入密码', trigger: 'change' },
        { min: 5, max: 16, message: '密码长度在 5 到 16 个字符', trigger: 'change' }
    ]
})

</script>

<template>
    <el-row class="login-page">
        <el-col :span="12" class="bg"></el-col>
        <el-col :span="6" :offset="3" class="form">

            <div>
                <div>
                    <span style="font-size: 66px;font-weight: bold;
                display: flex;justify-content: center;
                font-style:italic;color: #5D71BA;">
                        羽沫<span style="color:#213D5B;">大事件</span>
                    </span>
                    <br>
                    <span style="display: flex;justify-content: right;
                font-size: 25px; color: #6886C9; font-style: italic;
                font-weight: bold;margin-top: 10px;
                margin-bottom: 60px;">—— <span style="color:#213D5B;">&nbsp;<span>Yumo
                                News</span></span>&nbsp;前端展示</span>
                </div>
            </div>

            <!-- 注册表单 -->
            <el-form ref="form" size="large" autocomplete="off" v-if="!isRegister" :model="registerData"
                :rules="rules_register">
                <el-form-item>
                    <h1>注册</h1>
                </el-form-item>
                <el-form-item prop="username">
                    <el-input :prefix-icon="User" placeholder="请输入用户名" v-model="registerData.username">
                    </el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input :prefix-icon="Lock" type="password" placeholder="请输入密码" v-model="registerData.password">
                    </el-input>
                </el-form-item>
                <el-form-item prop="rePassword">
                    <el-input :prefix-icon="Lock" type="password" placeholder="请输入再次密码"
                        v-model="registerData.rePassword">
                    </el-input>
                </el-form-item>
                <!-- 注册按钮 -->
                <el-form-item>
                    <el-button class="button" type="primary" auto-insert-space @click="register()">注册</el-button>
                </el-form-item>
                <el-form-item class="flex">
                    <el-link type="info" :underline="false" @click="isRegister = true">
                        ← 返回
                    </el-link>
                </el-form-item>
            </el-form>

            <!-- 登录表单 -->
            <el-form ref="form" size="large" autocomplete="off" v-else :model="loginData" :rules="rules_login">
                <el-form-item>
                    <h1>登录</h1>
                </el-form-item>
                <el-form-item prop="username">
                    <el-input :prefix-icon="User" placeholder="请输入用户名" v-model="loginData.username">
                    </el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input name="password" :prefix-icon="Lock" type="password" placeholder="请输入密码"
                        v-model="loginData.password">
                    </el-input>
                </el-form-item>
                <el-form-item class="flex">
                    <div class="flex">
                        <el-checkbox>记住我</el-checkbox>
                        <el-link type="primary" :underline="false">忘记密码？</el-link>
                    </div>
                </el-form-item>
                <!-- 登录按钮 -->
                <el-form-item>
                    <el-button class="button" type="primary" auto-insert-space @click="login()">登录</el-button>
                </el-form-item>
                <el-form-item class="flex">
                    <el-link type="info" :underline="false" @click="isRegister = false">
                        注册 →
                    </el-link>
                </el-form-item>
            </el-form>
        </el-col>
    </el-row>
</template>

<style lang="scss" scoped>
/* 样式 */
.login-page {
    height: 100vh;
    background-color: #fff;

    .bg {
        background: url('@/assets/yumo-news_logo.png') no-repeat 60% calc(50% + 160px) / 600px auto,
            url('@/assets/login_bg3.png') no-repeat center / cover;
        border-radius: 0 20px 20px 0;
    }

    .form {
        display: flex;
        flex-direction: column;
        justify-content: center;
        user-select: none;

        .title {
            margin: 0 auto;
        }

        .button {
            width: 100%;
        }

        .flex {
            width: 100%;
            display: flex;
            justify-content: space-between;
        }
    }
}

/* 实现typing的打字动画效果，不换行 */
// .typing {
//     display: inline-block;
//     white-space: nowrap;
//     overflow: hidden;
//     animation: typing 3s steps(40, end), step-end infinite;
// }

// @keyframes typing {
//     from {
//         width: 0;
//     }
//     to {
//         width: 100%;
//     }
// }

// @keyframes blink {
//     50% {
//         opacity: 0;
//     }
// }
</style>