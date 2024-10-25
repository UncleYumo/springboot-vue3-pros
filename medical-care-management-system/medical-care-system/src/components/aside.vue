<template>
    <el-menu active-text-color="#ffd04b" background-color="#545c64" class="aside-container" :default-active="2"
        text-color="#fff" :collapse="isCollapse" @open="handleOpen" @close="handleClose">
        <p class="logo-lg">{{ isCollapse ? 'YUMO' : 'YUMO 陪诊' }}</p>
        <TreeMenu :menuData="menuData" />
    </el-menu>
</template>

<script setup>
import { reactive, ref } from 'vue'
import { useRouter } from 'vue-router';
import TreeMenu from './tree-menu.vue'

const router = useRouter();
const menuData = reactive(router.options.routes[0].children)
// console.log(router, 'router');
// console.log(menuData, 'menuData');

const isCollapse = ref(false)
const handleOpen = () => { }
const handleClose = () => { }

const dataToAside = reactive(
    [
        {
            'name': 'Navigator One',
            'password': '123456'
        },
        {
            'name': 'Navigator Two',
            'password': '123456'
        }
    ])

</script>

<style lang="less" scoped>
.aside-container {
    height: 100%;
    width: 21.5%;

    .logo-lg {
        font-size: 20px;
        text-align: center;
        /* 文字居中 */
        height: 50px;
        line-height: 50px;
        /* 行高 */
        color: #fff;
    }
}
</style>