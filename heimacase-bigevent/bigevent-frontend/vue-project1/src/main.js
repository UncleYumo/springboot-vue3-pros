import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue' // 导入App.vue组件


import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'  // 导入element-plus的默认样式
import 'element-plus/theme-chalk/dark/css-vars.css'  // 导入element-plus的暗黑主题样式
import locale from 'element-plus/dist/locale/zh-cn.js'  // 导入中文语言包

const app = createApp(App) // 创建Vue应用实例

app.use(ElementPlus,{locale})  
app.mount('#app')  // 挂载到id为app的元素上
