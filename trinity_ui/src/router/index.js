import Vue from 'vue'
import Router from 'vue-router'

import loginView from "../views/loginView";
import leftNav from '@/components/leftNav'
import baseComponent from '@/components/baseComponent'
import homeView from "../views/stu/homeView"
import managerView from "../views/man/managerView"
import stuManView from "../views/man/stuManView"
import colView from "../views/col/colView"
import colDomView from "@/views/col/colDomView";
import stuSelect from "@/views/stuSelect";
import recoView from "@/views/stu/recoView"
Vue.use(Router)

export default new Router({
  routes: [
    {
      path:'/recoView',
      name:'recoView',
      component:recoView
    },
    {
      path:'/stuSelect',
      name:'stuSelect',
      component:stuSelect
    },
    {
      path:'/colDom',
      name:'colDom',
      component:colDomView
    },
    {
      path :'/colView',
      name :'colView',
      component: colView
    },
    {
      path :'/baseComponent',
      name :'baseComponent',
      component: baseComponent
    },
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
      path:'/managerView',
      name:'managerView',
      component:managerView
    },
    {
      path:'/stuManView',
      name:'stuManView',
      component:stuManView
    },
    {
      path:"*",
      redirect:'/loginView'
    }
    ]
})
