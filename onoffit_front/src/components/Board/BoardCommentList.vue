<template>
  <div>
    <h3>댓글 목록</h3>
    <ul class="comment-list">
      <li v-for="comment in commentList" :key="comment.comment_id">
        <div class="comment-info">
          <span class="comment-content">{{ comment.comment_content }}</span>
          <span class="comment-writer">{{ comment.writer_id }} 님</span>
        </div>
        <div class="comment-actions">
          <div
            v-if="comment.writer_id === loginUser.user_id && !comment.isEditing"
          >
            <button @click="editComment(comment)">수정</button>
            <button @click="deleteComment(comment)">삭제</button>
          </div>
          <div v-else-if="comment.isEditing">
            <input type="text" v-model="comment.updatedContent" />
            <button @click="saveComment(comment)">저장</button>
          </div>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  created() {
    this.$store.dispatch("getCommentList", this.$route.params.board_idx);
  },
  computed: {
    loginUser() {
      return this.$store.state.loginUser;
    },
    ...mapState(["commentList"]),
  },
  methods: {
    editComment(comment) {
      comment.isEditing = true;
      this.commentList.sort(); // 리스트 변화에 반응하지 않기 때문에
      comment.updatedContent = comment.comment_content; // 수정할 내용을 입력 필드에 표시
    },
    saveComment(comment) {
      this.$store.dispatch("updateComment", comment);
      comment.comment_content = comment.updatedContent;
      comment.isEditing = false; // 수정 모드 해제
    },
    deleteComment(comment) {
      this.$store.dispatch("deleteComment", comment);
    },
  },
};
</script>

<style scoped>
.comment-list {
  list-style-type: none;
  padding: 0;
}

.comment-info {
  margin-bottom: 10px;
}

.comment-content {
  font-weight: bold;
}

.comment-writer {
  color: #888;
}

.comment-actions button {
  padding: 6px 10px;
  margin-right: 5px;
  border-radius: 4px;
  background-color: #007bff;
  color: #fff;
  border: none;
  cursor: pointer;
}

.comment-actions button:hover {
  background-color: #0056b3;
}

.comment-actions input[type="text"] {
  width: 200px;
  padding: 6px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.comment-actions input[type="text"]:focus {
  outline: none;
  border-color: #007bff;
}
</style>
