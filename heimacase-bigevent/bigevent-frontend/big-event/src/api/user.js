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

export const userInfoService = () => {
    return request.get('user/userInfo')
}

export const userInfoUpdateService = (userData) => {
    return request.post('/user/update', userData)
}

export const userAvatarUpdateService = (avatar_url) => {
    return request.patch('/user/updateAvatar?avatarUrl=' + avatar_url)
}

export const userPasswordUpdateService = (passwordData) => {
    return request.patch('/user/updatePwd', passwordData)
}