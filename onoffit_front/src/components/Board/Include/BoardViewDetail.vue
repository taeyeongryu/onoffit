<template>
  <div class="board-view-detail">
    <h1>게시판 상세</h1>
    <div class="detail-form">
      <label for="title">제목</label>
      <div class="view">{{ board.board_title }}</div>
      <label for="content">내용</label>
      <div class="view">{{ board.board_content }}</div>
      <label for="writer_id">작성자</label>
      <div class="view">{{ board.writer_id }}</div>
      <label for="board_like">좋아요</label>
      <div class="view">{{ board.board_like }}</div>
      <label for="view">조회수</label>
      <div class="board-view">{{ board.board_view }}</div>
      <div class="buttons" style="padding-top: 15px">
        <router-link
          :to="`/board/modify/${board.board_idx}`"
          v-if="this.$store.state.loginUser.user_id === board.writer_id"
          class="btn btn-primary"
          >수정</router-link
        >
        <button
          class="btn btn-danger"
          v-if="this.$store.state.loginUser.user_id === board.writer_id"
          @click="deleteBoard"
        >
          삭제
        </button>
        <router-link to="/board" class="btn btn-secondary">목록</router-link>
        <button
          v-if="board.liked"
          class="btn btn-outline-danger"
          @click="changelikeBoard"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="16"
            height="16"
            fill="currentColor"
            class="bi bi-arrow-through-heart-fill"
            viewBox="0 0 16 16"
          >
            <!-- SVG path data -->
            <path
              fill-rule="evenodd"
              d="M2.854 15.854A.5.5 0 0 1 2 15.5V14H.5a.5.5 0 0 1-.354-.854l1.5-1.5A.5.5 0 0 1 2 11.5h1.793l3.103-3.104a.5.5 0 1 1 .708.708L4.5 12.207V14a.5.5 0 0 1-.146.354l-1.5 1.5ZM16 3.5a.5.5 0 0 1-.854.354L14 2.707l-1.006 1.006c.236.248.44.531.6.845.562 1.096.585 2.517-.213 4.092-.793 1.563-2.395 3.288-5.105 5.08L8 13.912l-.276-.182A23.825 23.825 0 0 1 5.8 12.323L8.31 9.81a1.5 1.5 0 0 0-2.122-2.122L3.657 10.22a8.827 8.827 0 0 1-1.039-1.57c-.798-1.576-.775-2.997-.213-4.093C3.426 2.565 6.18 1.809 8 3.233c1.25-.98 2.944-.928 4.212-.152L13.292 2 12.147.854A.5.5 0 0 1 12.5 0h3a.5.5 0 0 1 .5.5v3Z"
            />
          </svg>
        </button>
        <button v-else class="btn btn-outline-dark" @click="changelikeBoard">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="16"
            height="16"
            fill="currentColor"
            class="bi bi-arrow-through-heart"
            viewBox="0 0 16 16"
          >
            <!-- SVG path data -->
            <path
              fill-rule="evenodd"
              d="M2.854 15.854A.5.5 0 0 1 2 15.5V14H.5a.5.5 0 0 1-.354-.854l1.5-1.5A.5.5 0 0 1 2 11.5h1.793l.53-.53c-.771-.802-1.328-1.58-1.704-2.32-.798-1.575-.775-2.996-.213-4.092C3.426 2.565 6.18 1.809 8 3.233c1.25-.98 2.944-.928 4.212-.152L13.292 2 12.147.854A.5.5 0 0 1 12.5 0h3a.5.5 0 0 1 .5.5v3a.5.5 0 0 1-.854.354L14 2.707l-1.006 1.006c.236.248.44.531.6.845.562 1.096.585 2.517-.213 4.092-.793 1.563-2.395 3.288-5.105 5.08L8 13.912l-.276-.182a21.86 21.86 0 0 1-2.685-2.062l-.539.54V14a.5.5 0 0 1-.146.354l-1.5 1.5Zm2.893-4.894A20.419 20.419 0 0 0 8 12.71c2.456-1.666 3.827-3.207 4.489-4.512.679-1.34.607-2.42.215-3.185-.817-1.595-3.087-2.054-4.346-.761L8 4.62l-.358-.368c-1.259-1.293-3.53-.834-4.346.761-.392.766-.464 1.845.215 3.185.323.636.815 1.33 1.519 2.065l1.866-1.867a.5.5 0 1 1 .708.708L5.747 10.96Z"
            />
          </svg>
        </button>
      </div>
    </div>
    <!--댓글-->
    <board-comment-create />
    <board-comment-list />
  </div>
</template>

<script>
import BoardCommentCreate from "@/components/Board/BoardCommentCreate.vue";
import BoardCommentList from "@/components/Board/BoardCommentList.vue";
import { mapState } from "vuex";

export default {
  name: "BoardViewDetail",
  components: {
    BoardCommentCreate,
    BoardCommentList,
  },
  computed: {
    ...mapState(["board"]),
  },
  created() {
    this.$store.dispatch("getBoard", this.$route.params.board_idx);
  },
  methods: {
    //게시판
    changelikeBoard() {
      this.$store.dispatch("changelikeBoard", {
        board: this.board,
        user_id: this.$store.state.loginUser.user_id,
      });
      alert("좋아요 버튼을 누르셨습니다.");
    },
    deleteBoard() {
      this.$store.dispatch("deleteBoard", this.board);
      alert("삭제를 누르셨습니다.");
      this.$router.push("/board");
    },
  },
};
</script>

<style scoped>
.board-view-detail {
  width: 70%;
  margin: 0 auto;
}

.detail-form {
  margin-bottom: 20px;
}

.view {
  background-color: #f7f7f7;
  padding: 10px;
  border-radius: 4px;
  margin-bottom: 10px;
}

.board-view {
  background-color: #f7f7f7;
  padding: 10px;
  border-radius: 4px;
  margin-bottom: 10px;
}

.buttons {
  display: flex;
  justify-content: space-between;
}

.buttons button {
  margin-right: 10px;
}
</style>
