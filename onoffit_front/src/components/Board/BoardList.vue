<template>
  <div>
    <!-- 이벤트 배너 -->
    <div>
      <b-carousel
        id="carousel-1"
        v-model="slide"
        :interval="4000"
        controls
        indicators
        background="#f8f9fa"
        img-width="1024"
        img-height="150"
        style="text-shadow: 1px 1px 2px #333"
        @sliding-start="onSlideStart"
        @sliding-end="onSlideEnd"
      >
        <b-carousel-slide img-src="https://picsum.photos/1024/150/?image=52"
          ><h1>ONOFFIT 페이지에 오신것을 환영합니다!!</h1>
        </b-carousel-slide>

        <b-carousel-slide img-src="https://picsum.photos/1024/150/?image=54">
          <h1>ONOFFIT 페이지에 오신것을 환영합니다!!</h1>
        </b-carousel-slide>

        <b-carousel-slide img-src="https://picsum.photos/1024/150/?image=58">
          <h1>ONOFFIT 페이지에 오신것을 환영합니다!!</h1></b-carousel-slide
        >

        <b-carousel-slide>
          <template #img>
            <img
              class="d-block img-fluid w-100"
              width="1024"
              height="200"
              src="https://picsum.photos/1024/150/?image=55"
              alt="image slot"
            />
          </template>
          <h1>ONOFFIT 페이지에 오신것을 환영합니다!!</h1>
        </b-carousel-slide>
      </b-carousel>
    </div>
    <h1 class="underline">게시판 목록</h1>
    <div class="parent">
      <!--검색 버튼-->
      <div class="search-container,d1" style="margin-left: 500px">
        <label for="key"></label>
        <div class="search-input-container">
          <select name="key" id="key" v-model="key" style="margin-right: 5px">
            <option value="none">선택</option>
            <option value="board_title">제목</option>
            <option value="board_content">내용</option>
            <option value="writer_id">글쓴이</option>
          </select>
          <input
            type="text"
            v-model="word"
            placeholder="검색할 키워드를 입력하세요."
          />
          <button class="clear-button" @click="clearWord">X</button>
          <button class="search-button" @click="searchBoard">검색</button>
        </div>
      </div>
      <!--검색 버튼-->

      <!--게시판 등록 버튼-->
      <div class="button-container,d1">
        <button
          v-if="!(this.$store.state.loginUser.user_id.length === 0)"
          class="register-button"
          @click="movePage"
          style="margin-right: 130px"
        >
          게시판 등록
        </button>
      </div>
      <!--게시판 등록버튼-->
    </div>
    <div v-if="currentList" class="table-container">
      <table id="board-list">
        <colgroup>
          <col style="width: 10%" />
          <col style="width: 40%" />
          <col style="width: 15%" />
          <col style="width: 10%" />
          <col style="width: 25%" />
        </colgroup>
        <thead>
          <tr>
            <th>no</th>
            <th>제목</th>
            <th>글쓴이</th>
            <th>조회수</th>
            <th>좋아요</th>
          </tr>
        </thead>
        <tbody>
          <board-list-row
            v-for="(board, index) in currentList"
            :key="index"
            :no="`${index + 1}`"
            :board_idx="board.board_idx"
            :board_title="board.board_title"
            :board_writer_id="board.writer_id"
            :board_view="board.board_view"
            :board_like="board.board_like"
            :liked="board.liked"
          />
        </tbody>
      </table>
    </div>

    <div v-else>
      <p>새로운 글을 등록해주세요</p>
    </div>
  </div>
</template>

<script>
import BoardListRow from "@/components/Board/Include/BoardListRow.vue";

export default {
  name: "BoardList",
  components: {
    BoardListRow,
  },
  data() {
    return {
      key: "none",
      word: "",
      slide: 0,
      sliding: null,
    };
  },
  created() {
    this.$store.dispatch("getBoardList", { key: "", word: "" });
  },
  computed: {
    boardList() {
      return this.$store.state.boardList;
    },
    sboardList() {
      return this.$store.state.sboardList;
    },
    currentList() {
      return this.sboardList.length > 0 ? this.sboardList : this.boardList;
    },
  },
  methods: {
    movePage() {
      this.$store.state.sboardList = []; //create누르고 다시 돌아오면 검색한 기록이 아직 남아있어서 안 뜨기 때문에 초기화
      this.$router.push({ name: "BoardCreate" });
    },
    searchBoard() {
      if (this.key === "none" || this.word === "") {
        // 검색 키워드가 선택되지 않았거나 입력되지 않았을 경우
        this.$store.commit("SET_SBOARDLIST", []);
      } else {
        this.$store.dispatch("searchBoard", { key: this.key, word: this.word });
      }
    },
    clearWord() {
      this.word = "";
    },
  },
};
</script>

<style scoped>
h1.underline {
  text-align: center;
  border-bottom: 2px solid #333;
  padding-bottom: 10px;
}

.table-container {
  display: flex;
  justify-content: center;
}
/*버튼 및 검색 버튼 시작 */
.parent {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px;
}
/* div.d1 {
  float: right;
} */

.button-container {
  display: flex;
  align-items: center;
  /* justify-content: flex-end; */
  /* margin-left: 100px; */
}
.search-container {
  text-align: center;
  margin-top: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.search-input-container {
  margin-right: 10px;
  /* display: flex;
  align-items: center;
  justify-content: center; */
}
.register-button {
  padding: 10px 20px;
  background-color: #333;
  color: #fff;
  border: none;
  border-radius: 5px;
  margin-top: 15px;
}
/*버튼 및 검색 버튼 끝 */

p {
  text-align: center;
  margin-bottom: 10px;
}

label {
  margin-right: 5px;
}

input[type="text"] {
  width: 220px;
  margin-right: 5px;
}

.clear-button,
.search-button {
  padding: 5px 10px;
  background-color: #333;
  color: #fff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.clear-button {
  margin-right: 5px;
}

#board-list {
  border-collapse: collapse;
  width: 80%;
  margin-top: 20px;
  margin-bottom: 20px;
  table-layout: fixed;
}

#board-list thead {
  background-color: #ccc;
  font-weight: bold;
}

#board-list td,
#board-list th {
  text-align: center;
  border-bottom: 1px solid #ddd;
  height: 50px;
}

tr:nth-child(even) {
  background-color: #f2f2f2;
}

#board-list th:nth-child(5) {
  width: 25%;
}
</style>
