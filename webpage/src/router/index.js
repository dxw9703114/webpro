import Vue from 'vue'
import Router from 'vue-router'
import Home from '../pages/home/Home'
import WebMap from '../pages/webmap/WebMap'
import Excel from '../pages/excel/Excel'
import Img from '../pages/img/Img'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      redirect: {
        name: 'home'
      }
    },
    {
      path: '/home',
      name: 'home',
      component: Home
    },
    {
      path: '/img',
      name: 'img',
      component: Img
    },
    {
      path: '/webmap',
      name: 'webmap',
      component: WebMap
    },
    {
      path: '/excel',
      name: 'excel',
      component: Excel
    }
  ]
})
