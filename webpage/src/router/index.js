import Vue from 'vue'
import Router from 'vue-router'
import Home from '../pages/home/Home'
import WebMap from '../pages/webmap/WebMap'

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
      path: '/webmap',
      name: 'webmap',
      component: WebMap
    }
  ]
})
