<template>
  <div class="container">
    <h2 class="mt-4">OnGroup</h2>
    <div class="group-buttons d-flex justify-content-center mt-4">
      <router-link class="btn btn-secondary mx-2" to="/ongroup"
        >GROUPLIST</router-link
      >
      <router-link
        class="btn btn-primary mx-2"
        v-if="isLoggedIn"
        to="/ongroup/create"
        >GROUPREGIST</router-link
      >
    </div>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  computed: {
    isLoggedIn() {
      return this.$store.state.loginUser.user_id !== "";
    },
  },
};
</script>

<style scoped>
.container {
  background-color: #f2f2f2; /* Change to gray color */
}
.group-buttons {
  margin-bottom: 20px;
}
</style>
