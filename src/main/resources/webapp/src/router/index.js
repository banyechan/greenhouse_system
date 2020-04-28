import Vue from 'vue'  //引入 vue
import Router from 'vue-router'

// 引入项目中的其他组件 (即项目中自己写的页面)
import HelloWorld from '@/components/HelloWorld' // 引入 helloworld 模块
import Home from '@/components/home'  // 引入 home 模块
import Layout from '@/components/layout'
import List from '@/components/studentList'
import User from '@/components/userList'


Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },{
      path:'/home',
      name:'home',
      component: Home
    },{
      path:'/layout',
      name:'layout',
      component: Layout
    },{
      path:'/list',
      name:'list',
      component: List
    },{
      path:'/user',
      name:'user',
      component: User
    }
  ]
})

