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
        :before-remove="beforeRemove"
        :file-list="fileList">
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
      dialogVisible: false,
      fileList: []
    }
  },
  mounted () {
    this.queryList()
  },
  methods: {
    queryList () {
      this.$ajax({
        method: 'get',
        url: '/image/list'
      }).then(res => {
        console.log(res)
        this.fileList = res.data
      })
    },
    beforeRemove (file, fileList) {
      console.log(file, fileList)
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        // this.removeFromFileList(file, fileList)
        this.$ajax({
          method: 'delete',
          url: '/image/'+file.id
        }).then(res => {
          this.$message({
            type: 'success',
            message: '删除成功!'
          })
          this.queryList()
        }).catch(() => {
          this.$message({
            type: 'error',
            message: '删除失败!'
          })
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
      return false
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
          this.queryList()
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
