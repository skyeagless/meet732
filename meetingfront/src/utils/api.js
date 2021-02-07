import axios from 'axios'
import {Message} from "element-ui";

//响应拦截器(统一处理所有请求成功之后响应过来的数据)
axios.interceptors.response.use(function (response) {
    let res = response.data;
    if(res.status === 0){
        return res.data;
    }else{
        Message.error({message:res.msg});
        return Promise.reject(res)
    }
},(error => {
    let res = error.response;
    Message.error({message:res.data.message});
    return Promise.reject(error);
}));

//域名地址
let base = 'http://119.45.237.253:8080';
//传递Json的post
export const postRequest = (url,params)=>{
    return axios({
        method: 'post',
        url: `${base}${url}`,
        data: params,
    });
};

export const putRequest = (url,params)=>{
    return axios({
        method: 'put',
        url: `${base}${url}`,
        data: params,
    });
};

export const getRequest = (url,params)=>{
    return axios({
        method: 'get',
        url: `${base}${url}`,
        data: params,
    });
};

export const deleteRequest = (url,params)=>{
    return axios({
        method: 'delete',
        url: `${base}${url}`,
        data: params,
    });
};