<template>
  <el-main>
    <div class="el-main-header">
      <h4>图片管理</h4>
    </div>
    <el-upload
      action="https://jsonplaceholder.typicode.com/posts/"
      list-type="picture-card"
      :on-preview="handlePictureCardPreview"
      :on-remove="handleRemove">
      <i class="el-icon-plus"></i>
    </el-upload>
    <el-dialog :visible.sync="dialogVisible">
      <img width="100%" :src="dialogImageUrl" alt="">
    </el-dialog>
  </el-main>
</template>

<script>
export default {
  name: 'Image',
  data () {
    return {
      dialogImageUrl: '',
      dialogVisible: false
    }
  },
  methods: {
    goBack () {
      this.$router.push({path: '/'})
    },
    handleRemove (file, fileList) {
      console.log(file, fileList)
    },
    handlePictureCardPreview (file) {
      this.dialogImageUrl = file.url
      this.dialogVisible = true
    }
  }
}
</script>

<style scoped>
.el-main-header{
  display: inline-block;
  float: left;
  width: 100%;
}
  .el-main-header h4{
    float: left;
  }
</style>
