<template>
  <el-main>
    <el-upload
      class="upload"
      action="#"
      :before-remove="beforeRemove"
      multiple
      :limit="3"
      :file-list="fileList">
      <el-button size="small" type="primary">点击上传</el-button>
      <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
    </el-upload>
  </el-main>
</template>

<script>
export default {
  name: 'Excel',
  data () {
    return {
      fileList: []
    }
  },
  methods: {
    /** 删除文件之前的操作 */
    beforeRemove (file, fileList) {
      return this.$confirm(`确定移除${file.name}？`)
    }
  }
}
</script>

<style scoped>
.upload {

}
</style>
