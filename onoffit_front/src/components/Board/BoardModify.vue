<template>
  <div class="container">
    <h2 class="text-center">수정하기</h2>
    <fieldset>
      <div class="form-group">
        <label for="board_title">글 제목</label>
        <input
          type="text"
          id="board_title"
          name="board_title"
          v-model="board.board_title"
          ref="board_title"
          class="form-control"
        />
      </div>
      <div class="form-group">
        <label for="board_content">내용</label>
        <textarea
          id="board_content"
          name="board_content"
          v-model="board.board_content"
          ref="board_content"
          class="form-control"
        ></textarea>
      </div>
      <div class="text-center">
        <button class="btn btn-primary" @click="updateBoard">수정</button>
      </div>
    </fieldset>
  </div>
</template>

<script>
export default {
  name: "BoardModify",
  methods: {
    updateBoard() {
      this.$store.dispatch("updateBoard", this.board);
      alert("수정이 완료되었습니다.");
      this.$router.push("/board");
    },
  },
  computed: {
    board() {
      return this.$store.state.board;
    },
  },
};
</script>

<style scoped>
.container {
  max-width: 500px;
  margin: 0 auto;
}

h2 {
  margin-top: 20px;
}

.form-group {
  margin-bottom: 20px;
}

label {
  font-weight: bold;
}

.form-control {
  width: 100%;
}

.btn-primary {
  background-color: #007bff;
  color: #fff;
}

.btn-primary:hover {
  background-color: #0069d9;
}

.text-center {
  text-align: center;
}
</style>
