<template>
  <div id="app">
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css"
    />
    <header-view></header-view>
    <router-view />
    <!-- <footer-view></footer-view> -->
  </div>
</template>

<script>
import HeaderView from "@/components/common/HeaderView.vue";
// import FooterView from "@/components/common/FooterView.vue";

export default {
  components: {
    HeaderView,
    // FooterView,
  },
};
</script>
<style>
* {
  font-family: "Gowun Dodum", sans-serif;
}
</style>
