//代理转发，这一块有问题，请勿参考
let proxyObj = {};
proxyObj['/'] = {
    ws: false,
    target: "http://119.45.237.253:8080/",
    changeOrigin: true,
    pathRewrite:{
        '^/': ''
    }
};

module.exports = {
    devServer:{
        host: '0.0.0.0',
        port: 8081,
        proxy: proxyObj,
    }
};