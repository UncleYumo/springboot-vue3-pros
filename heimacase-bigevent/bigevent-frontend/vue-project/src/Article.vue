<script setup>
import axios, { Axios } from 'axios';
import { articleSearchAllService } from '@/api/article'
import { ref } from 'vue';

const loginUrl =   "/user/login"
const articleUrl = "/article/getAll"
const testUrl =    "/user/test"
const baseURL =    "http://localhost:8080"

const instance = axios.create({ baseURL })

const token = ref()
const articleList = ref([])
const user = {
    username: "jimmy",
    password: "jimmy"
}

const searchCondition = ref({
    pageNum: 1,
    pageSize: 10,
    categoryId: "",
    state: ""
})

const buttonTest = () => {
    instance.get(testUrl).then(res => {
        console.log(res.data)
    }).catch(err => {
        console.log(err);
    })
}


const buttonClick = () => {
    instance.post(loginUrl, user).then(res => {
        token.value = res.data.data;
        if (token.value != null && token.value != "") {
            console.log("登录成功");
        }
    }).catch(err => {
        console.log(err);
    })
}

const buttonConditonSearch = () => {
    console.log("搜索条件: " + JSON.stringify(searchCondition.value));
    instance.get("http://localhost:8080/article", {
        params: { ...searchCondition.value },
        headers: {
            "Authorization": token.value
        }
    },)
        .then(res => {
            console.log(res.data.data);
            articleList.value = res.data.data.items;
        }).catch(err => {
            console.log(err);
        })
}


const buttonSearch = async () => {
    // console.log(articleSearchAllService(token));
    // // articleList.value = data.data
    // axios.get(articleUrl, {
    //     headers: {
    //         "Authorization": token.value
    //     },
    //     params: {
    //         secret: "991650"
    //     }
    // },)
    // .then(res => {
    //     console.log(res.data.data);
    //     articleList.value = res.data.data;
    // }).catch(err => {
    //     console.log(err);
    // })
    let data = await articleSearchAllService(token)
    articleList.value = data

}

</script>

<template>
    <div style="text-align: center;">

        文章分类: <input type="text" v-model="searchCondition.categoryId">
        发布状态: <input type="text" v-model="searchCondition.state">

        <button @click="buttonClick">登录</button>&nbsp;&nbsp;
        <button @click="buttonSearch">查询</button>&nbsp;&nbsp;
        <button @click="buttonTest">测试</button>&nbsp;&nbsp;
        <button @click="buttonConditonSearch">条件搜索</button>&nbsp;&nbsp;

        <br />
        <br />
        <table style="width: 80%; margin: auto; border-collapse: collapse;" border="1">
            <tr>
                <th>文章标题</th>
                <th>分类</th>
                <th>发表时间</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
            <tr v-for="item in articleList">
                <td>{{ item.title }}</td>
                <td>{{ item.categoryId }}</td>
                <td>{{ item.updateTime }}</td>
                <td>{{ item.state }}</td>
                <td>
                    <button>编辑</button>
                    <button>删除</button>
                </td>
            </tr>
        </table>
    </div>
</template>