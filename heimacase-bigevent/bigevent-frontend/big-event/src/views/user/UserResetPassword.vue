<template>
    <div style="
        width: 94%;
        height: 95%;
        /* background-color: aquamarine; */
    ">
        <div style="
            align-items: center;
            width: 100%;
            height: 100%;
            /* background-color: aqua; */
            ">

            <div style="
                width: 110px;
                height: 40px;
                margin-top: 10px;
                margin-bottom: 10px;
                display: flex;
                justify-content: center;
                align-items: center;
                /* background-color: aquamarine; */
                border-radius: 6px;
                background: linear-gradient(145deg, #e6e6e6, #ffffff);
                box-shadow:  5px 5px 10px #e6e6e6,
                -5px -5px 10px #ffffff;
            ">
                <h3 style="
                    color:slategray;
                    font-weight: bold;
                    text-shadow: 2px 2px 2px #e6e6e6;
                    ">密码变更</h3>
            </div>

            <div style="margin-top: 30px; width: 50%;">
                <el-form :inline="false" :model="formData" class="demo-form-inline" :rules="rules" ref="formRef">

                    <el-form-item label="原始密码" style="margin-bottom: 30px;" prop="old_pwd">
                        <el-input type="password" v-model="formData.old_pwd" :placeholder="formData.old_pwd"
                            clearable />
                    </el-form-item>

                    <el-form-item label="修改密码" style="margin-bottom: 30px;" prop="new_pwd">
                        <el-input type="password" v-model="formData.new_pwd" :placeholder="formData.new_pwd"
                            clearable />
                    </el-form-item>

                    <el-form-item label="重复密码" style="margin-bottom: 30px;" prop="re_pwd">
                        <el-input type="password" v-model="formData.re_pwd" :placeholder="formData.re_pwd" clearable />
                    </el-form-item>

                    <el-form-item>
                        <el-tooltip content="修改密码成功后需要重新登录！" placement="right-start">
                        <el-button type="danger" @click="onSubmit">提交修改</el-button>
                        </el-tooltip>
                        <el-button type="primary" plain @click="onReset">重置信息</el-button>
                    </el-form-item>
                </el-form>
            </div>


        </div>

    </div>
</template>


<script setup>
import { reactive, ref } from 'vue'
import { userPasswordUpdateService } from '@/api/user';
import { ElMessage, ElMessageBox } from 'element-plus'

const formData = ref({
    old_pwd: '',
    new_pwd: '',
    re_pwd: ''
})
const formRef = ref(null)
const rules = reactive({
    old_pwd: [
        { required: true, message: '请输入原始密码', trigger: 'change' },
        { min: 5, max: 16, message: '密码长度在5到16个字符', trigger: 'change' }
    ],
    new_pwd: [
        { required: true, message: '请输入新密码', trigger: 'change' },
        { min: 5, max: 16, message: '密码长度在5到16个字符', trigger: 'change' }
    ],
    re_pwd: [
        { required: true, message: '请再次输入密码', trigger: 'change' },
        { min: 5, max: 16, message: '密码长度在6到16个字符', trigger: 'change' },
        {
            validator: (rule, value, callback) => {
                if (value !== formData.value.new_pwd) {
                    callback(new Error('两次输入的密码不一致!'))
                } else {
                    callback()
                }
            }, trigger: 'change'
        }
    ]
})

const onSubmit = () => {

    ElMessageBox.confirm(
        '您确定要修改密码吗？',
        'Warning',
        {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning',
        }
    )
        .then(() => {
            // console.log(formData.value)
            formRef.value.validate(async (valid) => {
                if (valid) {
                    // console.log('submit')
                    let res = await userPasswordUpdateService(formData.value)
                    // console.log(res)
                    if (res.message === '预设消息: 操作成功') {
                        ElMessage({
                            type: 'success',
                            message: '密码修改成功',
                        })
                        formRef.value.resetFields()
                        window.location.href = '/login'
                    } else {
                        ElMessage({
                            type: 'error',
                            message: '密码修改失败',
                        })
                    }
                } else {
                    // console.log('error submit')
                    ElMessage({
                        type: 'error',
                        message: '请检查输入项',
                    })
                    return
                }
            })
        })
        .catch(() => {
            ElMessage({
                type: 'info',
                message: '已取消',
            })
        })
}
const onReset = () => {
    formRef.value.resetFields()
}
</script>