import instance from "@/utils/request";
// import { useTokenStore } from "@/store/token";


// 文章分类列表查询
export const articleCategoryListService = () => {

    // const tokenStore = useTokenStore()
    // alert(tokenStore.token)

    return instance.get('/category')

    // return instance.get('/category', {
    //     headers: {
    //         'Authorization': tokenStore.token
    //     }
    // })
}

// 文章分类添加接口
export const articleCategoryAddService = (categoryData) => {
    return instance.post('/category', categoryData)
}

// 文章分类修改
export const articlCategoryEditService = (categoryData) => {
    return instance.put('/category', categoryData)
}

// 文章分类删除
export const articleCategoryDeleteService = (id) => {
    return instance.delete('/category' + `?id=${id}`)
}

// 文章列表查询
export const articleListService = (params) => {
    return instance.get('/article',{params: params})
}