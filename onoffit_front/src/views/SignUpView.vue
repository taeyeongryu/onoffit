<template>
  <div class="container">
    <h2 class="text-center">회원 가입</h2>
    <div class="form-group form-floating">
      <input type="text" id="user_id" v-model="user_id" class="form-control" />
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
    <div class="form-group form-floating">
      <input
        type="password"
        id="user_password_check"
        v-model="user_password_check"
        class="form-control"
      />
      <label for="user_password_check">비밀번호 확인</label>
    </div>
    <div class="form-group form-floating">
      <input
        type="text"
        id="user_nickname"
        v-model="user_nickname"
        class="form-control"
      />
      <label for="user_nickname">닉네임</label>
    </div>
    <div class="form-group form-floating">
      <input
        type="text"
        id="user_name"
        v-model="user_name"
        class="form-control"
      />
      <label for="user_name">이름</label>
    </div>
    <div class="form-group form-floating">
      <input
        type="email"
        id="user_email"
        v-model="user_email"
        class="form-control"
      />
      <label for="user_email">이메일</label>
    </div>
    <div class="text-center">
      <button class="btn btn-primary" @click="regist">등록</button>
      <button class="btn btn-secondary" @click="movePage">뒤로가기</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "UserList",
  data() {
    return {
      user_id: "",
      user_password: "",
      user_nickname: "",
      user_name: "",
      user_email: "",
      user_password_check: "",
    };
  },
  methods: {
    movePage() {
      this.$router.push("/login");
    },
    regist() {
      if (
        this.user_id === "" ||
        this.user_password === "" ||
        this.user_name === "" ||
        this.user_email === "" ||
        this.user_nickname === "" ||
        this.user_password_check === ""
      ) {
        alert("모든 내용을 입력해주세요");
        return;
      }
      if (this.user_password !== this.user_password_check) {
        alert("비밀번호 확인란과 비밀번호란이 일치하지 않습니다.");

        return;
      }

      axios
        .get("http://localhost:9999/api/getuser", {
          params: {
            user_id: this.user_id,
          },
        })
        .then(() => {
          let user = {
            user_id: this.user_id,
            user_password: this.user_password,
            user_nickname: this.user_nickname,
            user_name: this.user_name,
            user_email: this.user_email,
          };

          this.$store.dispatch("createUser", user);
          this.$router.push("/");
        })
        .catch(() => {
          alert("중복되는 아이디가 존재합니다.");

          return;
        });
    },
  },
};
</script>

<style scoped>
* {
  font-family: "Gowun Dodum", sans-serif;
}
.container {
  font-family: "Gowun Dodum", sans-serif;
  max-width: 500px; /* 수정된 부분: 넓이 조정 */
  margin: 0 auto;
  margin-top: 50px;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  background-color: #f9f9f9;
}
h2 {
  font-family: "Gowun Dodum", sans-serif;
  text-align: center;
  margin-bottom: 20px;
  color: #333;
}

.form-group {
  font-family: "Gowun Dodum", sans-serif;
  margin-bottom: 15px;
}

label {
  font-family: "Gowun Dodum", sans-serif;
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
  color: #555;
}

input {
  font-family: "Gowun Dodum", sans-serif;
  width: 100%;
  padding: 5px;
  font-size: 16px;
  border-radius: 3px;
  border: 1px solid #ccc;
}

.text-center {
  font-family: "Gowun Dodum", sans-serif;
  text-align: center;
  margin-top: 20px;
}

.btn {
  font-family: "Gowun Dodum", sans-serif;
  margin-right: 10px;
}

.btn-primary {
  font-family: "Gowun Dodum", sans-serif;
  background-color: #007bff;
  border-color: #007bff;
}

.btn-primary:hover {
  font-family: "Gowun Dodum", sans-serif;
  background-color: #0069d9;
  border-color: #0062cc;
}

.btn-secondary {
  font-family: "Gowun Dodum", sans-serif;
  background-color: #6c757d;
  border-color: #6c757d;
}

.btn-secondary:hover {
  font-family: "Gowun Dodum", sans-serif;
  background-color: #5a6268;
  border-color: #545b62;
}

.alert {
  font-family: "Gowun Dodum", sans-serif;
  margin-top: 20px;
  padding: 10px;
  border-radius: 5px;
  font-size: 14px;
  color: #721c24;
  background-color: #f8d7da;
  border: 1px solid #f5c6cb;
}

.alert-danger {
  font-family: "Gowun Dodum", sans-serif;
  color: #721c24;
  background-color: #f8d7da;
  border-color: #f5c6cb;
}

.alert-danger strong {
  font-family: "Gowun Dodum", sans-serif;
  font-weight: bold;
}

.alert-danger ul {
  font-family: "Gowun Dodum", sans-serif;
  margin: 0;
  padding-left: 20px;
  list-style-type: square;
}

.alert-danger ul li {
  font-family: "Gowun Dodum", sans-serif;
  margin-bottom: 5px;
}
</style>
