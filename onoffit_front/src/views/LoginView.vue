<template>
  <div class="container">
    <br />
    <h2 class="text-center">로그인</h2>
    <fieldset class="login-form">
      <div class="form-group form-floating">
        <input
          type="text"
          id="user_id"
          v-model="user_id"
          class="form-control"
        />
        <label for="user_id">아이디</label>
      </div>
      <div class="form-group form-floating">
        <input
          type="password"
          id="user_password"
          v-model="user_password"
          class="form-control"
        />
        <label for="user_password">비밀번호</label>
      </div>
      <div class="text-center">
        <button class="btn btn-primary mt-3" @click="login">로그인</button>
        <button class="btn btn-secondary mt-3" @click="signup">회원가입</button>
      </div>
    </fieldset>
  </div>
</template>

<script>
export default {
  name: "LoginForm",
  data() {
    return {
      user_id: "",
      user_password: "",
    };
  },
  methods: {
    login() {
      let user = {
        user_id: this.user_id,
        user_password: this.user_password,
      };

      this.$store.dispatch("setLoginUser", user);
      this.$router.push("/");
    },
    signup() {
      this.$router.push("/signup");
    },
    logout() {
      this.$store.dispatch("logout");
    },
  },
};
</script>

<style scoped>
* {
  font-family: "Gowun Dodum", sans-serif;
}
.container {
  max-width: 400px;
  margin: 0 auto;
  padding: 20px;
}

.login-form {
  border: 1px solid #ccc;
  border-radius: 5px;
  padding: 20px;
  background-color: #f9f9f9;
}

.form-group {
  margin-bottom: 20px;
}

label {
  font-weight: bold;
}

.btn {
  width: 100%;
}

.btn-primary {
  background-color: #007bff;
  border-color: #007bff;
}

.btn-secondary {
  background-color: #6c757d;
  border-color: #6c757d;
}

.btn-primary:hover,
.btn-secondary:hover {
  background-color: #0056b3;
  border-color: #0056b3;
}

.btn-primary:focus,
.btn-secondary:focus {
  box-shadow: none;
}
</style>
