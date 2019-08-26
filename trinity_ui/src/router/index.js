import Vue from 'vue'
import Router from 'vue-router'

import loginView from "../views/loginView";
import leftNav from '@/components/leftNav'
import homeView from "../views/homeView"
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/loginView',
      name: 'loginView',
      component: loginView
    },
    {
      path:'/leftNav',
      name:'leftNav',
      component: leftNav
    },
    {
      path:'/homeView',
      name:'homeView',
      component:homeView
    },
    {
      path:"*",
      redirect:'/loginView'
    }
    ]
})
