<template>
  <el-main>
    <div class="el-main-header">
      <h4>图片管理</h4>
    </div>
    <div class="el-main-content">
      <el-upload
        action=""
        list-type="picture-card"
        :http-request="uploadImg"
        :on-preview="handlePictureCardPreview"
        :on-remove="handleRemove">
        <i class="el-icon-plus"></i>
      </el-upload>
      <el-dialog :visible.sync="dialogVisible">
        <img width="100%" :src="dialogImageUrl" alt="">
      </el-dialog>
    </div>
  </el-main>
</template>

<script>
export default {
  name: 'Img',
  data () {
    return {
      dialogImageUrl: '',
      dialogVisible: false
    }
  },
  methods: {
    handleRemove (file, fileList) {
      console.log(file, fileList)
    },
    handlePictureCardPreview (file) {
      this.dialogImageUrl = file.url
      this.dialogVisible = true
    },
    uploadImg (item) {
      const file = item.file
      const form = new FormData()
      form.append('file', file)
      this.$ajax({
        method: 'post',
        url: '/image/upload',
        // 参数拼接到url
        params: {},
        data: form
      })
        .then(resp => {
          console.log(resp)
        })
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
  .el-main-content{
    float: left;
  }
  el-upload--picture-card{
    float: left;
  }
</style>
