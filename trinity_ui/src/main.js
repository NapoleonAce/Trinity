// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import Vuex from 'vuex'

import axios from 'axios'
import VueAxios from 'vue-axios'

import {getRequest} from './utils/api'
import {postRequest} from './utils/api'
import {deleteRequest} from './utils/api'
import {putRequest} from "./utils/api"
import {getRequestBydata} from "./utils/api";
import {putRequestTest} from "./utils/api";
// import Vuex from 'vuex'
Vue.use(ElementUI)
Vue.config.productionTip = false
Vue.use(Vuex)
Vue.use(VueAxios, axios)

Vue.prototype.getRequest = getRequest;
Vue.prototype.postRequest = postRequest;
Vue.prototype.deleteRequest = deleteRequest;
Vue.prototype.putRequest = putRequest;
Vue.prototype.getRequestBydata = getRequestBydata;
Vue.prototype.putRequestTest = putRequestTest;

const store = new Vuex.Store({
  state:{
    managerInfo:{}
  },
  getters:{

  },
  mutations:{

    //设置用户的信息，用于登录
    login(state,data){
      state.managerInfo = data
    },
  },
  actions:{

  }
})



/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
