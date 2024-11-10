// 定制请求的示例
import axios from 'axios'
const baseURL = "http://localhost:8080"

const instance = axios.create({ baseURL })

// 响应拦截器
instance.interceptors.response.use(
    result => {
        return result.data
    },
    err => {
        console.log(err)
        alert("服务异常: " + err)
        return Promise.reject(err);  // 将错误对象返回，让调用者知道
    }
)

export default instance  // 导出请求实例