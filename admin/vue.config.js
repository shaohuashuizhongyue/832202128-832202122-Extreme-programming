var webpack = require('webpack');
module.exports = {
    transpileDependencies: true,

    //配置打开的地址
    devServer: {
        host: 'localhost',
        port: 8080,
    },


    // 在vue.config.js中configureWebpack中配置
// 要引入webpack
    configureWebpack: {
        plugins: [
            new webpack.ProvidePlugin({
                ' window.Quill': 'quill/dist/quill.js',
                'Quill': 'quill/dist/quill.js'
            })
        ]
    },
    chainWebpack: config => {
        config.plugin('provide').use(webpack.ProvidePlugin, [{
            'window.Quill': 'quill'
        }])
    },
}
