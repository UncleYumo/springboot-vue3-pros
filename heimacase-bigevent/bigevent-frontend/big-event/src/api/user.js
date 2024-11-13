import request from "@/utils/request";

export const userRegisterService = (registerData) => {
    const params = new URLSearchParams()  // 将对象转换为url参数
    for (let key in registerData) {
        params.append(key, registerData[key])
    }
    // alert(params)
    return request.post('user/register', params)
}

export const userLoginService = (loginData) => {
    const params = new URLSearchParams()  // 将对象转换为url参数
    for (let key in loginData) {
        params.append(key, loginData[key])
    }
    return request.post('user/login', params)
}