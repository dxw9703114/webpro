<template>
  <el-header>
    <el-menu
      :default-active="activeIndex"
      class="el-menu-demo"
      mode="horizontal"
      @select="handleSelect"
      router
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b">
      <el-menu-item index="home">首页</el-menu-item>
      <el-submenu index="2">
        <template slot="title">我的工作台</template>
        <el-menu-item index="2-1">图片管理</el-menu-item>
        <el-menu-item index="webmap">Web地图</el-menu-item>
        <el-menu-item index="excel">Excel工具</el-menu-item>
        <el-submenu index="2-4">
          <template slot="title">二级菜单</template>
          <el-menu-item index="2-4-1">三级菜单1</el-menu-item>
          <el-menu-item index="2-4-2">三级菜单2</el-menu-item>
          <el-menu-item index="2-4-3">三级菜单3</el-menu-item>
        </el-submenu>
      </el-submenu>
      <el-menu-item index="3" disabled>消息中心</el-menu-item>
      <el-menu-item index="4"><a href="https://www.ele.me" target="_blank">订单管理</a></el-menu-item>
    </el-menu>
  </el-header>
</template>

<script>
export default {
  name: 'Header',
  data () {
    return {
      activeIndex: this.$route.path.slice(1)
    }
  },
  methods: {
    init () {
      console.log(this.$route.path)
    },
    handleSelect (index, indexPath) {
      console.log(`${index},${indexPath}`)
    }
  },
  mounted () {
    this.init()
  }
}
</script>

<style scoped>

</style>
