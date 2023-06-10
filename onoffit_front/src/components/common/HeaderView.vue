<template>
  <div>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
      <!-- 수정된 부분: 왼쪽 공간과 폰트 변경 -->
      <router-link to="/" class="navbar-brand ml-3">ONOFFIT</router-link>
      <div class="navbar-collapse collapse">
        <ul class="navbar-nav mx-auto">
          <li class="nav-item">
            <router-link to="/info" class="nav-link">INTRODUCE</router-link>
          </li>
          <li class="nav-item">
            <router-link to="/ongroup" class="nav-link">ONLINE</router-link>
          </li>
          <li class="nav-item">
            <router-link to="/offgroup" class="nav-link">OFFLINE</router-link>
          </li>
          <li class="nav-item">
            <router-link to="/board" class="nav-link">BOARD</router-link>
          </li>
        </ul>
      </div>
      <ul class="navbar-nav ml-auto">
        <li class="nav-item" v-if="loginUser.user_id === ''">
          <router-link to="/login" class="nav-link">LOGIN</router-link>
        </li>
        <li class="nav-item" v-if="loginUser.user_id !== ''">
          <router-link to="/" @click.native="logout" class="nav-link"
            >LOGOUT</router-link
          >
        </li>
        <li class="nav-item">
          <router-link
            to="/signup"
            v-if="loginUser.user_id === ''"
            class="nav-link"
            >SIGNUP</router-link
          >
        </li>
        <li class="nav-item" v-if="loginUser.user_id !== ''">
          <router-link
            :to="`/mypage/on/${loginUser.user_id}`"
            class="nav-link"
            @click.native="moveto"
            >MYPAGE</router-link
          >
        </li>
      </ul>
    </nav>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  computed: {
    ...mapState(["loginUser"]),
  },
  methods: {
    logout() {
      this.$store.dispatch("logout");
    },
    moveto() {
      this.$store.dispatch("setMyOnGroup");
      this.$store.dispatch("setMyOffGroup");
    },
  },
};
</script>

<style scoped>
* {
  font-family: "Gowun Dodum", sans-serif;
}
.navbar {
  font-family: "Gowun Dodum", sans-serif;
  background-color: #f8f9fa;
}

.navbar-brand {
  font-weight: bold;
  color: #333;
  font-size: 20px; /* 수정된 부분: 폰트 크기 변경 */
}

.nav-link {
  color: #333;
}

.nav-link:hover {
  color: #007bff;
}

.navbar-collapse {
  justify-content: center;
}
</style>
