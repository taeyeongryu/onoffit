<template>
  <div class="comment-form">
    <h3>댓글 작성</h3>
    <form @submit.prevent="createComment">
      <div class="form-field">
        <label for="comment_content">댓글 내용:</label>
        <textarea
          id="comment_content"
          v-model="comment_content"
          placeholder="댓글을 입력하세요"
        ></textarea>
      </div>
      <button type="submit" class="btn-submit">작성</button>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      comment_content: "",
    };
  },
  methods: {
    createComment() {
      const newComment = {
        board_idx: parseInt(this.$route.params.board_idx),
        comment_content: this.comment_content,
        writer_id: this.$store.state.loginUser.user_id,
      };
      this.$store.dispatch("createComment", newComment).then(() => {
        this.comment_content = ""; // 새로운 댓글 작성 후 입력 필드 초기화
      });
    },
  },
};
</script>

<style scoped>
.comment-form {
  width: 100%;
  margin-bottom: 20px;
}

.form-field {
  margin-bottom: 15px;
}

label {
  font-weight: bold;
}

textarea {
  width: 100%;
  height: 100px;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.btn-submit {
  padding: 8px 12px;
  border-radius: 4px;
  background-color: #007bff;
  color: #fff;
  border: none;
  cursor: pointer;
}

.btn-submit:hover {
  background-color: #0056b3;
}
</style>
