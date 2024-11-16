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

            <div style="
                display: flex;
                justify-content: space-between;
                /* align-items: center; */
            ">
                <div style="
                width: 110px;
                height: 40px;
                margin-top: 10px;
                margin-bottom: 10px;
                display: flex;
                justify-content: center;
                align-items: center;
                /* background-color: blue; */
                border-radius: 6px;
                background: linear-gradient(145deg, #e6e6e6, #ffffff);
                box-shadow:  5px 5px 10px #e6e6e6,
                -5px -5px 10px #ffffff;
            ">
                    <h3 style="
                    color:slategray;
                    font-weight: bold;
                    text-shadow: 2px 2px 2px #e6e6e6;
                    ">文章管理</h3>
                </div>
                <button class="button2" @click="addArticle"><span>添加文章</span></button>
            </div>

            <div style="
            ">
                <!-- 搜索栏 -->
                <div style="
                    margin-top: 10px;
                    /* background-color: antiquewhite; */
                ">
                    <el-form :inline="true" class="demo-form-inline">

                        <el-form-item label="文章分类">
                            <el-select v-model="formData.categoryId" style="width: 150px;" placeholder="请选择">
                                <el-option v-for="c in categoryData" :label="c.categoryName" :value="c.id" />
                            </el-select>
                        </el-form-item>

                        <el-form-item label="发布状态">
                            <el-select v-model="formData.state" style="width: 150px;" placeholder="请选择">
                                <el-option label="已发布" value="已发布" />
                                <el-option label="草稿" value="草稿" />
                            </el-select>
                        </el-form-item>

                        <el-form-item>
                            <el-button type="danger" @click="searchArticle">搜索</el-button>
                            <el-button type="primary" plain @click="resetForm">重置</el-button>
                        </el-form-item>

                    </el-form>
                </div>

                <!-- <el-divider /> -->

                <!-- 文章列表 -->
                <div>
                    <el-table :data="tableData" style="
                        width: 100%;
                        height: 380px;
                        /* background-color: tomato; */
                    " v-loading="loading" element-loading-text="正在加载...">
                        <el-table-column prop="title" label="文章标题" width="180" />
                        <el-table-column label="分类" width="180">
                            <template #default="{ row }">
                                {{ findCategoryById(row.categoryId) }}
                            </template>
                        </el-table-column>
                        <el-table-column prop="updateTime" label="发表时间" />
                        <el-table-column prop="updateTime" label="更新时间" />]

                        <el-table-column align="right" label="操作">
                            <template #default="{ row }">

                                <el-button :icon="Edit" plain circle type="primary" @click="editArticle(row.id)">
                                </el-button>

                                <el-button :icon="Delete" plain circle type="danger" @click="deleteArticle(row.id)">
                                </el-button>

                            </template>
                        </el-table-column>

                    </el-table>
                </div>

                <!-- 分页栏 -->
                <div style="
                    /* background-color: aquamarine; */
                    margin-top: 10px;
                    display: flex;
                    justify-content: right;
                ">
                    <el-pagination v-model:current-page="pageNum" v-model:page-size="pageSize"
                        :page-sizes="[5, 10, 20, 40]" layout="jumper, total, sizes, prev, pager, next" :total="total"
                        @size-change="onSizeChange" @current-change="onCurrentChange" />
                </div>
            </div>

        </div>

    </div>

    <el-drawer v-model="isDrawerShow" :with-header="false" size="40%">
        <div style="
            width: 90%;
            height: 100%;
            /* border: 1px solid black; */
            margin-left: 5%;
            /* background-color: bisque; */
        ">
            <h2 style="color: slategray;">添加文章</h2>

            <div>

                <el-form :model="form_addArticle" label-width="auto">
                    <el-form-item label="文章标题">
                        <el-input style="width: 60%;" v-model="form_addArticle.title" placeholder="请输入文章标题" />
                    </el-form-item>

                    <el-form-item label="文章分类">
                        <el-select style="width: 60%;" v-model="form_addArticle.categoryId" placeholder="请选择">
                            <el-option v-for="c in categoryData" :label="c.categoryName" :value="c.id" />
                        </el-select>
                    </el-form-item>

                    <el-form-item label="文章封面">

                        <!-- 
                            auto-upload: [boolean] 是否自动上传
                            show-file-list: [boolean] 是否显示已上传文件列表
                            on-success: [function] 上传成功的回调函数
                            on-error: [function] 上传失败的回调函数
                            action: [string] 上传的地址
                            name: [string] 上传的文件字段名
                            headers: [object] 请求头

                        < -->

                        <el-upload class="avatar-uploader" :auto-upload="true"
                            action="https://uncleyumo.cn/big-event/upload" name="file"
                            :headers="{ 'Authorization': tokenStore.token }" :on-success="uploadSuccess"
                            :on-error="uploadError" :on-remove="removeAvatar">
                            <img v-if="form_addArticle.coverImg" :src="form_addArticle.coverImg" class="avatar" style="
                                    width: 80%;
                                    height: auto;
                            " />

                            <el-icon v-else class="avatar-uploader-icon"
                                style="border: 2px solid #DCDFE6; padding: 10px;">
                                <Plus />
                            </el-icon>

                        </el-upload>
                    </el-form-item>

                    <el-form-item label="文章内容">
                        <div class="editor" style="
                            /* border: #DCDFE6 2px solid; padding: 10px; */
                        ">
                            <quill-editor theme="snow" v-model:content="form_addArticle.content" content-type="html"
                                placeholder="请输入文章内容" style="
                                    min-height: 200px;
                                    margin-bottom: 10px;
                            ">
                            </quill-editor>

                            <el-button type="danger" plain @click="addArticleData('已发布')">发布</el-button>
                            <el-button type="primary" plain @click="addArticleData('草稿')">草稿</el-button>

                        </div>
                    </el-form-item>

                </el-form>

            </div>
        </div>
    </el-drawer>

</template>

<script setup>

import { ref, reactive, onMounted } from 'vue'
import { Edit, Delete, Plus } from '@element-plus/icons-vue'
import {
    articleCategoryListService,
    articleListService,
    articleAddService,
    articleDeleteService,
    articleQueryByIdService
} from '@/api/article';

import { QuillEditor } from '@vueup/vue-quill';


// 导入token
import { useTokenStore } from '@/store/token';
import { ElMessageBox, ElMessage } from 'element-plus';
const tokenStore = useTokenStore()

const imageUrl = ref('')

const form_addArticle = reactive({
    title: '',
    categoryId: '',
    coverImg: '',
    content: '',
    state: '草稿'
})

const isDrawerShow = ref(false)

const addArticle = () => {
    isDrawerShow.value = true
}

onMounted(() => {
    getaArticleCategoryList()
    getArticleList()
})

const getaArticleCategoryList = async () => {

    let res = await articleCategoryListService()
    // console.log(res.data)
    categoryData.value = res.data
}

const formData = ref({
    categoryId: '',
    state: ''
})

// 获取文章列表数据
const getArticleList = async () => {
    let params = {
        pageNum: pageNum.value ? pageNum.value : 1,
        pageSize: pageSize.value ? pageSize.value : 10,
        categoryId: formData.value.categoryId,
        state: formData.value.state
    }
    // 正在加载数据
    loading.value = true
    // 发送请求

    let result = await articleListService(params)

    // 加载完成
    loading.value = false


    tableData.value = result.data.items
    total.value = result.data.total
}

const categoryData = ref([])

const pageNum = ref(1)  // 当前页码
const pageSize = ref(10)  // 每页显示条数
const total = ref(50)  // 总条数
const loading = ref(false)  // 正在加载数据

const choicedArticleId = ref(-1)

const uploadSuccess = (res) => {
    form_addArticle.coverImg = res.data
    ElMessage({
        message: '用户头像上传成功',
        type: 'success',
        plain: true,
    })
}

const uploadError = () => {
    drawer_loading.value = false
    ElMessage({
        message: '用户头像上传失败，请稍后再试',
        type: 'error',
        plain: true,
    })
}

const removeAvatar = () => {
    form_addArticle.coverImg = ''
}

const onCurrentChange = (num) => {
    pageNum.value = num
    getArticleList()
}

const onSizeChange = (num) => {
    pageSize.value = num
    getArticleList()
}

const tableData = ref([])

const searchArticle = () => {
    getArticleList()
}

const resetForm = () => {
    formData.value = {
        category: '',
        state: ''
    }
    getArticleList()
}

const addArticleData = async (click_state) => {

    if (choicedArticleId.value !== -1) {
        await articleDeleteService(choicedArticleId.value)
        choicedArticleId.value = -1
    }

    form_addArticle.state = click_state
    let res = await articleAddService(form_addArticle)

    form_addArticle.title = ''
    form_addArticle.content = ''
    form_addArticle.coverImg = ''
    

    if (res.message === '预设消息: 操作成功') {
        ElMessage({
            message: '文章发布成功 - ' + res.title + '(' + click_state + ')',
            type: 'success',
            plain: true,
        })
        isDrawerShow.value = false
        getArticleList()
    } else {
        ElMessage({
            message: '文章发布失败! - ' + res.title + '(' + click_state + ')',
            type: 'error',
            plain: true,
        })
    }
}

const findCategoryById = (category_id) => {
    const result = categoryData.value.find(item => item.id === category_id)
    return result.categoryName ? result.categoryName : '未知分类'
}

const deleteArticle = (article_id) => {
    console.log('删除文章', article_id)
    ElMessageBox.confirm(
        '您确定要删除该文章吗？',
        'Warning',
        {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning',
        }
    )
        .then(async () => {

            loading.value = true

            let res = await articleDeleteService(article_id)
            getArticleList()
            loading.value = false

            ElMessage({
                type: 'success',
                message: res.message,
            })
        })
        .catch(() => {
            ElMessage({
                type: 'info',
                message: '已取消',
            })
        })

}

const editArticle = (article_id) => {
    console.log('编辑文章', article_id)
    ElMessageBox.confirm(
        '您确定要编辑该文章吗？',
        'Warning',
        {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning',
        }
    )
        .then(async () => {

            // 打开编辑页面
            let res = await articleQueryByIdService(article_id)
            console.log(res)
            form_addArticle.title = res.data.title
            form_addArticle.content = res.data.content
            form_addArticle.coverImg = res.data.coverImg
            categoryData.value.categoryName = findCategoryById(res.data.categoryId)
            console.log(categoryData.value.categoryName)
            isDrawerShow.value = true
            choicedArticleId.value = article_id
        })
        .catch(() => {
            ElMessage({
                type: 'info',
                message: '已取消',
            })
        })
}

</script>

<style scoped>
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

.avatar-uploader .el-upload {
    border: 1px dashed var(--el-border-color);
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
    transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
    border-color: var(--el-color-primary);
}


.el-icon.avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    text-align: center;
}

.editor {
    width: 100%;
}
</style>