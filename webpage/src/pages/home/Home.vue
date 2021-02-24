<template>
  <el-main>
    <h1>{{title}}</h1>
    <el-row :gutter="20">
      <el-col :span="8">
        <el-card shadow="hover">图片管理</el-card>
      </el-col>
      <el-col :span="8">
        <el-card shadow="hover">Web地图</el-card>
      </el-col>
      <el-col :span="8">
        <el-card shadow="hover">Excel工具</el-card>
      </el-col>
    </el-row>
  </el-main>
</template>

<script>
export default {
  name: 'Main',
  data () {
    return {
      title: 'Home Page'
    }
  },
  methods: {
  }
}
</script>

<style>
  @import '../../assets/css/main.less';
</style>
