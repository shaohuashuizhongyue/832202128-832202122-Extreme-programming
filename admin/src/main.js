import Vue from 'vue'
import  './plugins/axios'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/element.js'

Vue.config.productionTip = false

Vue.prototype.$ajax=axios;


const htbaseurl = "http://127.0.0.1:81/";
const javabaseurl = "http://127.0.0.1:8088";
const weburl = "http://127.0.0.1/"
Vue.prototype.$hturl = htbaseurl + "" ;//全局注册，后台接口地址
Vue.prototype.$imgurl = javabaseurl + "/upload/" ;//全局注册，图片附件地址
Vue.prototype.$javaurl = javabaseurl + "" ;//全局，java应用地址
Vue.prototype.$weburl = weburl + "" //全局，前台页面

// 全局过滤器
/*Object.keys(filter).forEach(key => {
    Vue.filter(key, filter[key])
});*/

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
