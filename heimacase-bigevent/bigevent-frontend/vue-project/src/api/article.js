// import axios from 'axios'

const loginUrl =   "/user/login"
const articleUrl = "/article/getAll"
const testUrl =    "/user/test"
// const baseURL =    "http://localhost:8080"

// const instance = axios.create({ baseURL })

import request from "@/util/request"

export async function articleSearchAllService(token) {
    try {
        const response = await request.get(articleUrl, {
            headers: {
                "Authorization": token.value
            },
            params: {
                secret: "991650"
            }
        });
        return response.data;
    } catch (err) {
        console.error("!!!!!: " +err);
        return null;
    }
}