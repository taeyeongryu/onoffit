<template>
  <div class="background-container">
    <!-- 내용을 추가하세요 -->
  </div>
</template>

<script>
export default {
  name: "HomeView",
  components: {
    // HelloWorld
  },
};
</script>

<style scoped>
* {
  font-family: "Gowun Dodum", sans-serif;
}
.background-container {
  height: 100vh; /* 화면의 세로 길이를 100%로 설정 */
  width: 100vw; /* 화면의 가로 길이를 100%로 설정 */
  background-image: url("@/assets/bruce-mars-gJtDg6WfMlQ-unsplash.jpg"); /* 배경 이미지 경로 설정 */
  background-size: cover; /* 배경 이미지를 컨테이너에 맞게 조절 */
  background-position: center; /* 배경 이미지를 가운데 정렬 */
}
</style>
