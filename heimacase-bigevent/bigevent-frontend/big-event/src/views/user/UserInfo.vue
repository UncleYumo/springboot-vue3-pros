<template>
    <div style="
        width: 94%;
        height: 95%;
        /* background-color: aquamarine; */
    ">
        <div style="
            align-items: center;
            width: 100%;
            height:100%;
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
                    ">用户信息</h3>
            </div>

            <div style="margin-top: 30px;">
                <el-form :inline="false" :model="formData" class="demo-form-inline" :rules="rules" ref="formRef">

                    <el-form-item label="登录名称" style="margin-bottom: 30px;">
                        <el-input :placeholder="formData.username" disabled clearable />
                    </el-form-item>

                    <el-form-item label="用户昵称" style="margin-bottom: 30px;" prop="nickname">
                        <el-input v-model="formData.nickname" :placeholder="formData.nickname" clearable />
                    </el-form-item>

                    <el-form-item label="用户邮箱" style="margin-bottom: 30px;" prop="email">
                        <el-input v-model="formData.email" :placeholder="formData.email" clearable />
                    </el-form-item>

                    <el-form-item>
                        <el-button type="danger" @click="onSubmit">提交修改</el-button>
                        <el-button type="primary" plain @click="onReset">重置信息</el-button>
                    </el-form-item>
                </el-form>
            </div>


        </div>

    </div>
</template>


<script setup>
import { onMounted, reactive, ref } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import useUserInfoStore from '@/store/userInfo';
import { userInfoUpdateService, userInfoService } from '@/api/user';

const userInfoStore = useUserInfoStore()
const userInfo = userInfoStore.info
const formRef = ref(null)

const formData = ref({
    id: userInfo.id,
    username: userInfo.username,
    nickname: userInfo.nickname,
    email: userInfo.email
})

const onSubmit = () => {

    ElMessageBox.confirm(
        '即将提交修改，是否继续？',
        'Warning',
        {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning',
        }
    )
        .then(async () => {
            // console.log(formData.value)

            formRef.value.validate(async (valid) => {
                if (!valid) {
                    ElMessage.error('请检查输入项')
                    return
                } else {
                    let res = await userInfoUpdateService(formData.value)
                    if (res.message === '预设消息: 操作成功') {
                        ElMessage({
                            type: 'success',
                            message: '修改成功',
                        })
                        let result = await userInfoService()
                        userInfoStore.setInfo(result.data)
                        onReset()

                    } else {
                        ElMessage({
                            type: 'error',
                            message: '修改失败',
                        })
                    }
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
    let Info = userInfoStore.info
    formData.value = {
        id: Info.id,
        username: Info.username,
        nickname: Info.nickname,
        email: Info.email
    }
}

const rules = reactive({
    nickname: [
        { required: true, message: '请输入用户昵称', trigger: 'change' },
        { min: 5, max: 16, message: '长度在 2 到 10 个字符', trigger: 'change' }
    ],
    email: [
        { required: true, message: '请输入用户邮箱', trigger: 'change' },
        { type: 'email', message: '邮箱格式不正确', trigger: 'change' }
    ]
})

</script>

<style scoped>
.demo-form-inline {
    width: 50%;
}
</style>