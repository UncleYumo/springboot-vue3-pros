//定制请求的实例

import { useTokenStore } from '@/store/token';

// import { useRouter } from 'vue-router'
// const router = useRouter();

import router from '@/router/index'

//导入axios  npm install axios
import axios from 'axios';
import { ElMessage } from 'element-plus';

//定义一个变量,记录公共的前缀  ,  baseURL
// const baseURL = 'http://localhost:8080';
// const baseURL = 'http://uncleyumo.cn/big-event/api';
const baseURL = '/api';

const FOREIGN_KEY_CONSTRAINTS = "CONSTRAINT `fk_article_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`))"

const instance = axios.create({baseURL})
instance.interceptors.request.use(
    (config)=> {
        // 添加token
        const tokenStore = useTokenStore();
        if (tokenStore.token) {
            config.headers.Authorization = tokenStore.token;
        }
        return config;
    },
    (error) => {
        Promise.reject(error);
    }
)

//添加响应拦截器
instance.interceptors.response.use(
    result => {
        if ( result.data.code === 0 ) {
            return result.data;
        } else {

            if (result.data.message.includes(FOREIGN_KEY_CONSTRAINTS)) {
                return Promise.reject("FOREIGN_KEY_CONSTRAINTS");
            }

            alert(result.data.message ? result.data.message : '服务异常');
            return Promise.reject(result.data);
        }
    },
    err => {

        // 判断响应状态码是否为401，如果是，则代表token失效，则跳转到登录页面
        if (err.response.status === 401) {
            ElMessage.error('登录失效，请重新登录');
            router.push('/login');
            return Promise.reject(err);
        } else {
            ElMessage.error(err.response.data.message);
            return Promise.reject(err);
        }
    }
)

export default instance;