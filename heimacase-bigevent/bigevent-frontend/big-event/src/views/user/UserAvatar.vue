<template>
  <div style="
        width: 94%;
        height: 95%;
        /* background-color: aquamarine; */
    ">
    <div style="
            align-items: center;
            width: 50%;
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
                    ">头像变更</h3>
      </div>

      <div>
        <el-image style="width: 70%; height: auto;margin-bottom: 10px;" :src="avatar_url"/>

        <div>
          <el-upload
              ref="upload"
              class="upload-demo"
              :limit="1"
              action="https://uncleyumo.cn/big-event/upload"
              :auto-upload="true"
              :on-success="uploadSuccess"
              :on-error="uploadFailed"
              :on-remove="reflashUrl"
              :headers="{ 'Authorization': useToken.token }"
              name="file"
          >
            <template #trigger>
              <el-button type="primary" plain>上传头像</el-button>
            </template>
            <span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
            <el-button class="ml-3" type="danger" @click="submitUpload">
              提交修改
            </el-button>
            <template #tip>
              <div class="el-upload__tip text-red">
                limit 1 file, new file will cover the old file
              </div>
            </template>
          </el-upload>
        </div>

      </div>
    </div>

  </div>
</template>


<script setup>
import {onMounted, ref} from "vue";
import useUserInfoStore from "@/store/userInfo.js";
import {useTokenStore} from "@/store/token.js";
import {ElMessage} from "element-plus";

import {userAvatarUpdateService} from "@/api/user.js";

const avatar_url = ref();

const useToken = useTokenStore()
const useUserInfo = useUserInfoStore();

const uploadSuccess = (res) => {
  avatar_url.value = res.data
  ElMessage({
    type: 'success',
    message: '上传成功',
  })
}

const uploadFailed = (res) => {
  ElMessage({
    type: 'error',
    message: '上传失败',
  })
}

const submitUpload = async () => {

  if (avatar_url.value === useUserInfo.info.userPic) {
    ElMessage({
      type: 'warning',
      message: '请上传新的头像',
    })
    return
  }

  if (avatar_url.value) {
    let res = await userAvatarUpdateService(avatar_url.value)
    ElMessage({
      type: 'success',
      message: '修改成功',
    })
  }
}

const reflashUrl = () => {
  avatar_url.value = useUserInfo.info.userPic
}

onMounted(() => {
  avatar_url.value = useUserInfo.info.userPic
})
</script>