<template>
  <div>
    <h1>{{ msg }}</h1>

    <div>
      <h4> 进入列表页面:</h4>
      <router-link to="/list">list page</router-link>
    </div>

    <div>
      <h4> 进入layout页面:</h4>
      <router-link to="/layout">layout page</router-link>
    </div>

    <div>
      <h4> 进入user页面:</h4>
      <router-link to="/user">layout page</router-link>
    </div>


  </div>
</template>

<script>
export default {
  name: 'home',
  data () {
    return {
      msg: 'Welcome to home ~'
    }
  }
}
</script>


<style scoped>

</style>
