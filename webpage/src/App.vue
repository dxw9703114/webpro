<template>
  <div id="app">
    <Header/>
    <router-view/>
  </div>
</template>

<script>
import Main from './pages/home/Home'
import Header from './pages/header/Header'
export default {
  name: 'App',
  components: {Header, Main}
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
