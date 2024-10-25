import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import router from './router'

const app =createApp(App)
app.use(router)  // 挂载路由
app.mount('#app')  // 挂载到id为app的元素上
