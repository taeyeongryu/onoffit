<template>
  <div class="regist">
    <h1 class="underline">글 등록</h1>
    <div class="regist_form">
      <label for="board_title">글 제목</label>
      <input
        type="text"
        id="board_title"
        name="board_title"
        v-model="board_title"
        ref="board_title"
      />
      <label for="board_content">내용</label>
      <textarea
        type="text"
        id="board_content"
        name="board_content"
        v-model="board_content"
        ref="board_content"
      ></textarea>
      <br />
      <button v-if="type == 'create'" @click="registboard">등록</button>
      <button @click="moveList">목록</button>
    </div>
  </div>
</template>

<script>
export default {
  name: "BoardWriteForm",
  props: {
    // 상위 component로 부터 넘어온 type정보를 받음.
    type: { type: String },
  },
  data() {
    return {
      board_title: "",
      board_content: "",
      writer_id: "",
      board_idx: 0, // board_idx 초기화
      liked: false,
    };
  },
  methods: {
    registboard() {
      // 제목과 내용이 비어있는지 확인
      if (this.board_title.trim() === "" || this.board_content.trim() === "") {
        alert("제목과 내용을 입력해주세요.");
        return;
      }
      let board = {
        board_title: this.board_title,
        board_content: this.board_content,
        writer_id: this.$store.state.loginUser.user_id,
        board_like: 0,
        board_view: 0,
      };
      this.$store.dispatch("registBoard", board);
      this.$router.push("/board");
    },
    moveList() {
      this.$router.push("/board");
    },
  },
};
</script>

<style scoped>
.regist {
  padding: 10px;
}
.regist_form {
  text-align: left;
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}
input,
textarea,
.view {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  color: #787878;
  font-size: medium;
}
</style>
