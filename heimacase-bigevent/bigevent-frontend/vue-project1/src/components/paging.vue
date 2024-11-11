<script lang="ts" setup>
import { ref } from 'vue'
import { ComponentSize } from 'element-plus'
import {
    Delete,
    Edit
} from '@element-plus/icons-vue'

import { reactive } from 'vue'

const formInline = reactive({
    title: '',
    state: '',
})

const onSubmit = () => {
    console.log('submit!')
}

const currentPage = ref(1)
const pageSize = ref(5)
const size = ref<ComponentSize>('default')
const background = ref(false)
const disabled = ref(false)

const tableData = [
    {
        title: "Vue3.0 新特性",
        category: "前端",
        time: '2016-05-02',
        state: '已发布',
    }, {
        title: "Vue3.0 新特性",
        category: "前端",
        time: '2016-05-02',
        state: '已发布',
    }, {
        title: "Vue3.0 新特性",
        category: "前端",
        time: '2016-05-02',
        state: '已发布',
    }, {
        title: "Vue3.0 新特性",
        category: "前端",
        time: '2016-05-02',
        state: '已发布',
    }, {
        title: "Vue3.0 新特性",
        category: "前端",
        time: '2016-05-02',
        state: '已发布',
    }, {
        title: "Vue3.0 新特性",
        category: "前端",
        time: '2016-05-02',
        state: '已发布',
    }, {
        title: "Vue3.0 新特性",
        category: "前端",
        time: '2016-05-02',
        state: '已发布',
    }, {
        title: "Vue3.0 新特性",
        category: "前端",
        time: '2016-05-02',
        state: '已发布',
    }, {
        title: "Vue3.0 新特性",
        category: "前端",
        time: '2016-05-02',
        state: '已发布',
    }, {
        title: "Vue3.0 新特性",
        category: "前端",
        time: '2016-05-02',
        state: '已发布',
    }, {
        title: "Vue3.0 新特性",
        category: "前端",
        time: '2016-05-02',
        state: '已发布',
    }, {
        title: "Vue3.0 新特性",
        category: "前端",
        time: '2016-05-02',
        state: '已发布',
    }, {
        title: "Vue3.0 新特性",
        category: "前端",
        time: '2016-05-02',
        state: '已发布',
    }, {
        title: "Vue3.0 新特性",
        category: "前端",
        time: '2016-05-02',
        state: '已发布',
    }, {
        title: "Vue3.0 新特性",
        category: "前端",
        time: '2016-05-02',
        state: '已发布',
    }, {
        title: "Vue3.0 新特性",
        category: "前端",
        time: '2016-05-02',
        state: '已发布',
    }, {
        title: "Vue3.0 新特性",
        category: "前端",
        time: '2016-05-02',
        state: '已发布',
    },
]

const handleSizeChange = (val: number) => {
    console.log(`${val} items per page`)
}
const handleCurrentChange = (val: number) => {
    console.log(`current page: ${val}`)
}
</script>

<template>
    <div>

        <el-card style="max-width: 80%; margin-top: 20px; margin-left: 10%; margin-right: 10%;">
            <div class="card-header" style="display: flex; justify-content: space-between; align-items: center;">
                <span>文章管理</span>
                <el-button type="primary" @click="">发布文章</el-button>
            </div>
        </el-card>
        <div style="margin-top: 20px;">
            <el-form :inline="true" :model="formInline" class="demo-form-inline"
                style="width: 100%; display: flex; justify-content: center;">
                <el-form-item label="文章分类">
                    <el-select v-model="formInline.title" placeholder="文章分类" clearable>
                        <el-option label="时事" value="时事" />
                        <el-option label="篮球" value="篮球" />
                    </el-select>
                </el-form-item>

                <el-form-item label="发布状态">
                    <el-select v-model="formInline.state" placeholder="发布状态" clearable>
                        <el-option label="已发布" value="已发布" />
                        <el-option label="草稿" value="草稿" />
                    </el-select>
                </el-form-item>

                <el-form-item>
                    <el-button type="primary" @click="onSubmit">查询</el-button>
                    <el-button type="default" @click="onSubmit">重置</el-button>
                </el-form-item>
            </el-form>
        </div>

        <el-table :data="tableData" style="width: 80%; margin-left: 10%; margin-right: 10%;">
            <el-table-column prop="title" label="文章标题" />
            <el-table-column prop="category" label="分类" />
            <el-table-column prop="time" label="发表时间" />
            <el-table-column prop="state" label="状态" />
            <el-table-column label="操作" width="100">
                <el-button type="primary" :icon="Edit" circle />
                <el-button type="danger" :icon="Delete" circle />
            </el-table-column>
        </el-table>
    </div>

    <div>
        <div class="demo-pagination-block">
            <el-pagination class="demo-pagination" v-model:current-page="currentPage" v-model:page-size="pageSize"
                :page-sizes="[5, 10, 20]" :size="size" :disabled="disabled" :background="background"
                layout="jumper, total, sizes, prev, pager, next" :total="100" @size-change="handleSizeChange"
                @current-change="handleCurrentChange" />
        </div>
    </div>
</template>

<style scoped>
.demo-pagination {
    margin-top: 20px;
    display: flex;
    justify-content: end;
}

.demo-pagination-block {
    margin-top: 20px;
}

.demo-form-inline .el-input {
    --el-input-width: 220px;
}

.demo-form-inline .el-select {
    --el-select-width: 220px;
}
</style>