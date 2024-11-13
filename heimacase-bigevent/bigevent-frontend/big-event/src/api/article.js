import instance from "@/utils/request";


// 文章分类列表查询
export const articleCategoryListService = () => {
    return instance.get('/category')
}