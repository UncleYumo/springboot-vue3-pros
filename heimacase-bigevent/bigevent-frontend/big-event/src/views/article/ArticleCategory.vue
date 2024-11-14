<template>
    <div style="
        width: 94%;
        height: 95%;
        display: flex;
        /* background-color: aquamarine; */
    ">
        <div style="
            align-items: center;
            width: 100%;
            height:100%;
            /* background-color: aqua; */
            ">

            <div style="display: flex; justify-content: space-between; margin-bottom: 20px;">
                
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

                <button class="button2" @click="titleModel = '新增分类'; dialogVisible = true;"><span>新增分类</span></button>

            </div>

            <el-table :data="tableData" style="
                width: 100%;
                height: 450px;
                /* border: 2px solid #ccc; */
                " v-loading="loading" element-loading-text="正在加载...">
                <el-table-column prop="id" label="序号" type="index" width="100px" />
                <el-table-column prop="categoryName" label="分类名称" width="150px" />
                <el-table-column prop="categoryAlias" label="分类别名" width="150px" />
                <el-table-column prop="createTime" label="创建时间" width="220px" />
                <el-table-column prop="updateTime" label="更新时间" width="220px" />
                <el-table-column align="right" label="操作">
                    <template #default="{ row }">
                        <el-button :icon="Edit" plain circle type="primary" @click="editCategory(row)"></el-button>
                        <el-button :icon="Delete" plain circle type="danger"
                            @click="deleteCategory(row.id)"></el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>

    </div>

    <div>
        <el-dialog v-model="dialogVisible" :title="titleModel" width="30%">
            <!-- <span>This is a message</span> -->

            <el-form :model="categoryModel" :rules="rules" label-width="auto" label-position="left">
                <el-form-item label="分类名称" prop="categoryName">
                    <el-input v-model="categoryModel.categoryName" />
                </el-form-item>
                <el-form-item label="分类别名" prop="categoryAlias">
                    <el-input v-model="categoryModel.categoryAlias" />
                </el-form-item>
            </el-form>
            <template #footer>
                <span>
                    <el-button type="danger" @click="titleModel == '新增分类' ? addCategory() : updateCategory()">提交</el-button>
                    <el-button type="primary" plain @click="dialogVisible = false">取消</el-button>
                </span>
            </template>
        </el-dialog>
    </div>

</template>

<script setup>
import { onMounted, ref } from 'vue'
import { Edit, Delete } from '@element-plus/icons-vue'

import {
    articleCategoryListService,
    articleCategoryAddService,
    articlCategoryEditService,
    articleCategoryDeleteService
} from '@/api/article';

import { ElMessage, ElMessageBox } from 'element-plus';

const FOREIGN_KEY_CONSTRAINTS = "CONSTRAINT `fk_article_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`))"
const dialogVisible = ref(false)  // 弹窗是否可见

const loading = ref(false)

const categoryModel = ref({
    categoryName: '',
    categoryAlias: '',
    id: ''
})

const editCategory = (row) => {
    categoryModel.value.categoryName = row.categoryName
    categoryModel.value.categoryAlias = row.categoryAlias
    categoryModel.value.id = row.id
    dialogVisible.value = true
    titleModel.value = '编辑分类'
}

const titleModel = ref('')

// 分类的表单校验规则
const rules = {
    categoryName: [
        { required: true, message: '请输入分类名称', trigger: 'blur' }
    ],
    categoryAlias: [
        { required: true, message: '请输入分类别名', trigger: 'blur' }
    ]
}

const tableData = ref([
    {
        "id": null,
        "categoryName": "",
        "categoryAlias": "",
        "createTime": "",
        "updateTime": ""
    }
])

// 调用接口新增分类
const addCategory = async () => {
    // alert("addCategory + " + categoryModel.value)
    loading.value = true
    let res = await articleCategoryAddService(categoryModel.value)
    loading.value = false
    ElMessage({
        type: 'success',
        message: '新增分类成功'
    })
    dialogVisible.value = false
    categoryModel.value.categoryAlias = ''
    categoryModel.value.categoryName = ''
    articleCategoryList()
}

const updateCategory = async () => {
    // console.log(categoryModel.value)
    // alert("updateCategory + " + categoryModel.value)
    loading.value = true
    let res = await articlCategoryEditService(categoryModel.value)
    loading.value = false
    ElMessage({
        type: 'success',
        message: '编辑分类成功'
    })
    dialogVisible.value = false
    categoryModel.value.categoryAlias = ''
    categoryModel.value.categoryName = ''
    articleCategoryList()
}

const deleteCategory = (id) => {
    ElMessageBox.confirm(
        '此操作将永久删除该分类, 是否继续?',
        '提示',
        {
            confirmButtonText: '确认',
            cancelButtonText: '取消',
            type: 'warning',
        }
    )
        .then(async () => {
            loading.value = true
            let res = await articleCategoryDeleteService(id)
            loading.value = false
            articleCategoryList()
            ElMessage({
                type: 'success',
                message: '删除成功',
            })
        })
        .catch((err) => {
            if (err === 'FOREIGN_KEY_CONSTRAINTS') {
                ElMessage.error('所删除的分类下存有文章，请先删除文章');
                return
            }
            ElMessage({
                type: 'info',
                message: '已取消',
            })
        })
}

onMounted(() => {
    articleCategoryList()
})

const articleCategoryList = async () => {
    loading.value = true
    let res = await articleCategoryListService()
    tableData.value = res.data
    loading.value = false
}

</script>

<style scope>
.button2 {
    margin-top: 10px;
    margin-bottom: 10px;
    width: 150px;
    height: 40px;
    font-weight: bold;
    display: flex;
    justify-content: center;
    align-items: center;
    /* display: inline-block; */
    transition: all 0.2s ease-in;
    position: relative;
    overflow: hidden;
    z-index: 1;
    color: #f46a6a;
    padding: 0.7em 1.7em;
    cursor: pointer;
    font-size: 18px;
    border-radius: 0.5em;
    background: #e8e8e8;
    border: 1px solid #e8e8e8;
    box-shadow: 6px 6px 12px #c5c5c5, -6px -6px 12px #ffffff;
}

.button2:active {
    color: #666;
    box-shadow: inset 4px 4px 12px #c5c5c5, inset -4px -4px 12px #ffffff;
}

.button2:before {
    content: "";
    position: absolute;
    left: 50%;
    transform: translateX(-50%) scaleY(1) scaleX(1.25);
    top: 100%;
    width: 140%;
    height: 180%;
    background-color: rgba(247, 2, 2, 0.05);
    border-radius: 50%;
    display: block;
    transition: all 0.5s 0.1s cubic-bezier(0.55, 0, 0.1, 1);
    z-index: -1;
}

.button2:after {
    content: "";
    position: absolute;
    left: 55%;
    transform: translateX(-50%) scaleY(1) scaleX(1.45);
    top: 180%;
    width: 160%;
    height: 190%;
    background-color: tomato;
    border-radius: 50%;
    display: block;
    transition: all 0.5s 0.1s cubic-bezier(0.55, 0, 0.1, 1);
    z-index: -1;
}

.button2:hover {
    color: #ffffff;
    border: 1px solid tomato;
}

.button2:hover:before {
    top: -35%;
    background-color: tomato;
    transform: translateX(-50%) scaleY(1.3) scaleX(0.8);
}

.button2:hover:after {
    top: -45%;
    background-color: tomato;
    transform: translateX(-50%) scaleY(1.3) scaleX(0.8);
}
</style>