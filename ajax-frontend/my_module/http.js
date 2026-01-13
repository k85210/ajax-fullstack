// 封裝axios

import axios from "./axios.js";
// 效果和上面的相同
// import axios from "https://cdnjs.cloudflare.com/ajax/libs/axios/1.11.0/esm/axios.js";

const http = axios.create(
    {
        baseURL: "https://jsonplaceholder.typicode.com",
        timeout: 5000
    }
);

// 請求前進行攔截
http.interceptors.request.use(
    // 請求前進行設定
    (config) => {
        // 通常做 token(令牌) 設定
        const token = "I am Alice";
        if (token) {
            config.headers.Authorization = `Bearer ${token}`;
        }
        return config;
    },

    // 如果再請求發送前出錯，要進行錯誤處理(少見)
    (error) => {
        // 類似java throw exception
        return Promise.reject(error);
    },

)

// 回應完成前進行攔截
http.interceptors.response.use(
    // 回應前操作
    (resp) => {
        return resp.data; // 幫開發者拿出資料
    },
    // 全域錯誤處理
    (error) => {
        // 先判斷錯誤是否存在
        if (!error.response) {
            return;
        }
        const status = error.response.status;
        
        if (status == 404) {
            alert("找不到頁面");
            return;
        }

        if (status == 400) {
            alert("參數錯誤");
            return;
        }

        return Promise.reject(error);
    }
)

export default http;

