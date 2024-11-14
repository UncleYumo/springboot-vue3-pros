// 导入createRouter和createWebHistory方法
// createRouter方法用于创建路由实例，createWebHistory方法用于创建基于浏览器历史记录的路由实例
import { createRouter, createWebHistory } from 'vue-router'

// 导入各个页面组件
import LoginVue from '@/views/Login.vue'
import LayoutVue from '@/views/Layout.vue'

import ArticleCategoryVue from '@/views/article/ArticleCategory.vue'
import ArticleManageVue from '@/views/article/ArticleManage.vue'

import UserAvatarVue from '@/views/user/UserAvatar.vue'
import UserInfoVue from '@/views/user/UserInfo.vue'
import UserResetPasswordVue from '@/views/user/UserResetPassword.vue'

// 路由配置
const routes = [
    {
        path: '/login',
        component: LoginVue
    },
    {
        path: '/',
        component: LayoutVue,
        redirect: '/article/category',
        children: [
            {
                path: '/article/category',
                component: ArticleCategoryVue
            },
            {
                path: '/article/manage',
                component: ArticleManageVue
            },
            {
                path: '/user/avatar',
                component: UserAvatarVue
            },
            {
                path: '/user/info',
                component: UserInfoVue
            },
            {
                path: '/user/reset-password',
                component: UserResetPasswordVue
            }
        ]
    }
]

// 创建路由实例
const router = createRouter({
    history: createWebHistory(),  // 使用浏览器历史记录
    routes  // 等于 routes: routes
})

// 导出路由实例
export default router