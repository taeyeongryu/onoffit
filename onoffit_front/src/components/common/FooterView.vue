<template>
  <footer class="footer">
    <div class="container">
      <span class="text-muted">&copy; ONOFFIT 2023</span>
    </div>
  </footer>
</template>

<style scoped>
.footer {
  background-color: #f8f9fa;
  padding: 10px;
  position: fixed; /* 변경된 부분: fixed 위치 설정 */
  left: 0;
  right: 0;
  bottom: 0;
  z-index: 999; /* 다른 요소 위에 표시하기 위한 z-index 설정 */
}

.container {
  text-align: center;
}

.text-muted {
  font-size: 12px;
}

/* 추가된 스타일 */
body {
  margin-bottom: 30px; /* 본문 내용이 footer 아래에 가려지지 않도록 margin-bottom 설정 */
}
</style>
