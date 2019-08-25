import Vue from 'vue'
import Router from 'vue-router'

import loginView from "../views/loginView";

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/loginView',
      name: 'loginView',
      component: loginView
    },
    {
      path:"*",
      redirect:'/loginView'
    }
    ]
})
