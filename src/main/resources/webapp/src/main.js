// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'

//iview
import iView from 'iview';   
import 'iview/dist/styles/iview.css';    // 使用 CSS

// axios
import axios from 'axios';


Vue.config.productionTip = false
Vue.prototype.axios = axios


Vue.use(iView);

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})


// new Vue({
//   el: '#app',  //创建的vue实例负责处理的区域
//   router, //路由处理vue页面的跳转，相当于路径导航
//   render: h => h(App)
// })
// template:‘<app/>’,components:{App}配合使用与单独使用render:h=>h(App)会达到同样的效果
// 前者识别<template>标签，后者直接解析template下的id为app的div(忽略template的存在)