// (async () => {
//     const ins = axios.create({
//         baseURL: 'http://localhost:8080'
//     });
//
//     ins.interceptors.request.use(config => {
//         console.log('请求拦截器 | 开始请求')
//         console.log("url: " + config.url + " | method: " + config.method + " | params: " + JSON.stringify(config.params))
//         console.log("")
//         return config
//     });
//
//     ins.interceptors.response.use(response => {
//         console.log('响应拦截器 | 开始响应')
//         console.log("url: " + response.config.url + " | status: " + response.status)
//         let data = response.data;
//         console.log("data: " + JSON.stringify(data.message) + " | code: " + data.code)
//         console.log("")
//         return response
//     })
//
//     // 异步操作代码
//     const res1 = await ins.get('/captcha/getBase64Bytes', {
//         params: {
//             timestamp: new Date().getTime()
//         }
//     });
//
//     const res2 = await ins.post('/user/login', {} ,{
//         params: {
//             username: 'jimmy',
//             password: 'jimmy'
//         }
//     });
// })()

// fetch('http://localhost:8080/captcha/getBase64Bytes?timestamp=' + new Date().getTime())
//     .then(res => {
//         if (res.ok) {
//             return res.json()
//         }
//     })
//     .then(date => {
//         console.log(date)
//     })

fetch('http://localhost:8080/user/login', {
    method: 'POST',
    headers: {
        'Content-Type': 'application/json'
    },
    body: JSON.stringify({
        username: 'jimmy',
        password: 'jimmy'
    })
})
    .then(res => {
    if (res.ok) {
        return res.json()
    }
})
    .then(data => {
        console.log(data)
    })