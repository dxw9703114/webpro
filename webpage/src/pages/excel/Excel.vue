<template>
  <el-main>
    <el-upload
      class='upload'
      action='#'
      :before-remove='beforeRemove'
      multiple
      :limit='3'
      :file-list='fileList'>
      <el-button size='small' type='primary'>点击上传</el-button>
      <div slot='tip' class='el-upload__tip'>只能上传jpg/png文件，且不超过500kb</div>
    </el-upload>
    <el-button @click='send' type='primary'>发送数据到NP</el-button>
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
    },
    send () {
      const param = {
        'pttyah': '2c0292941d214b5abe032caefe55d3b2',
        'ywxtbs': '2c0292941d214b5abe032caefe55d3b2',
        'cflj': '/lthj/fyjr/dengxiaowei_ceishi.zip',
        'fsdwbh': '1234',
        'jsdwbh': '2400',
        'ywlb': '1',
        'ywjdbh': '0211A',
        'sjbs': '2c0292941d214b5abe032caefe55d3b2',
        'fssj': '2020-03-10'
      }
      const url = '/zfxt/api/send/data'
      this.$ajax({
        method: 'post',
        url: url,
        data: param
      }).then(res => {
        console.log(res)
      })
    }
  }
}
</script>

<style scoped>
.upload {

}
</style>
