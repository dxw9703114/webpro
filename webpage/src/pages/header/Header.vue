<template>
  <div>
    <nav>
      <div>
        <ul>
          <li @click="toPage('home')">Home</li>
          <li @click="toPage('webmap')">WebMap</li>
        </ul>
      </div>
    </nav>
  </div>
</template>

<script>
export default {
  name: 'Header',
  methods: {
    toPage (path) {
      this.$router.push(path)
    }
  }
}
</script>

<style scoped>

</style>
