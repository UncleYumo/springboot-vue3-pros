import './assets/main.scss'

import { createApp } from 'vue'
import App from './App.vue'

import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

import router from './router'
import { createPinia  } from 'pinia'
import { createPersistedState } from 'pinia-persistedstate-plugin'
import local from 'element-plus/dist/locale/zh-cn'

import { QuillEditor } from '@vueup/vue-quill'
import '@vueup/vue-quill/dist/vue-quill.snow.css';

const app = createApp(App)
const pinia = createPinia()

pinia.use(createPersistedState())
app.component('QuillEditor', QuillEditor)
app.use(router)
app.use(pinia)
app.use(ElementPlus, { locale: local }).mount('#app')
