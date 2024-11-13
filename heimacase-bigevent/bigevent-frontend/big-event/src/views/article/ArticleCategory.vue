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
                    ">文章分类</h3>
            </div>

            <el-table :data="tableData" style="
                width: 100%;
                /* border: 2px solid #ccc; */
                ">
                <el-table-column prop="id" label="序号" type="index" width="100px" />
                <el-table-column prop="categoryName" label="分类名称" width="200px" />
                <el-table-column prop="categoryAlias" label="分类别名" width="200px" />
                <el-table-column prop="createTime" label="创建时间" width="250px" />
                <el-table-column prop="updateTime" label="更新时间" width="250px" />
                <el-table-column align="right" label="Operations">
                    <template #default="{ row }">
                        <el-button :icon="Edit" plain circle type="primary"></el-button>
                        <el-button :icon="Delete" plain circle type="danger"></el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>

    </div>
</template>

<script setup>
import { onMounted, ref } from 'vue'
import { Edit, Delete } from '@element-plus/icons-vue'

import { articleCategoryListService } from '@/api/article';

const tableData = ref([
    {
        // "id": 1,
        "categoryName": "新闻",
        "categoryAlias": "news",
        "createTime": "2022-01-01 12:00:00",
        "updateTime": "2022-01-01 12:00:00"
    }
])

onMounted(() => {
    articleCategoryList()
})

const articleCategoryList = async () => {
    let res = await articleCategoryListService()
    tableData.value = res.data
}

</script>