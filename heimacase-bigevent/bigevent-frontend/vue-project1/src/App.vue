<template>

  <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
    <el-menu-item index="1">Processing Center</el-menu-item>
    <el-sub-menu index="2">
      <template #title>Workspace</template>
      <el-menu-item index="2-1">item one</el-menu-item>
      <el-menu-item index="2-2">item two</el-menu-item>
      <el-menu-item index="2-3">item three</el-menu-item>
      <el-sub-menu index="2-4">
        <template #title>item four</template>
        <el-menu-item index="2-4-1">item one</el-menu-item>
        <el-menu-item index="2-4-2">item two</el-menu-item>
        <el-menu-item index="2-4-3">item three</el-menu-item>
      </el-sub-menu>
    </el-sub-menu>
    <el-menu-item index="3" disabled>Info</el-menu-item>
    <el-menu-item index="4">Orders</el-menu-item>
    <el-menu-item>
      <el-switch v-model="isDark" class="ml-2" inline-prompt
        style="--el-switch-on-color: #292D3E; --el-switch-off-color: #292D3E" active-text="黑色主题" inactive-text="白色主题"
        size="large" width="90" @change="toggleDark" />
    </el-menu-item>
  </el-menu>
  <div class="h-6" />

  <div>
    <Paging></Paging>
  </div>

</template>

<script lang="ts" setup>
import { ref } from 'vue'
import { useDark, useToggle } from '@vueuse/core'
import { tr } from 'element-plus/es/locale';
import Paging from './components/paging.vue';

const activeIndex = ref('1')
const activeIndex2 = ref('1')
const handleSelect = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}

const isDark = useDark()
const toggleDark = () => useToggle(isDark)

</script>

<style scoped></style>