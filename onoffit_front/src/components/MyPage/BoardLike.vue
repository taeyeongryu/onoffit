<template>
  <div class="board-like">
    <div v-if="boards.length !== 0">
      <table class="table table-striped">
        <thead>
          <tr>
            <th scope="col" style="width: 10%">#</th>
            <th scope="col" style="width: 60%">제목</th>
            <th scope="col" style="width: 10%">작성자</th>
            <th scope="col" style="width: 10%">좋아요</th>
            <th scope="col" style="width: 10%">조회수</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(board, index) in boards" :key="index">
            <td scope="col">{{ index + 1 }}</td>
            <td>
              <router-link
                :to="{
                  name: 'BoardDetail',
                  params: { board_idx: board.board_idx },
                }"
                class="board-link"
                >{{ board.board_title }}</router-link
              >
            </td>
            <td>{{ board.writer_id }}</td>
            <td>{{ board.board_like }}</td>
            <td>{{ board.board_view }}</td>
          </tr>
        </tbody>
      </table>
    </div>
    <div v-else class="text-center empty-message">좋아한 글이 없습니다.</div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "BoardLike",
  data() {
    return {
      boards: [],
    };
  },
  methods: {
    getData() {
      axios
        .get("http://localhost:9999/api/getliked", {
          params: {
            id: this.$store.state.loginUser.user_id,
          },
        })
        .then((res) => {
          this.boards = res.data;
        });
    },
  },
  created() {
    this.getData();
  },
};
</script>

<style scoped>
* {
  font-family: "Gowun Dodum", sans-serif;
}
.table {
  width: 100%;
  border-collapse: collapse;
}

.table th,
.table td {
  padding: 8px;
  text-align: left;
}

.table th {
  background-color: #f2f2f2;
}

.table-striped tbody tr:nth-of-type(odd) {
  background-color: #f9f9f9;
}

.board-like {
  margin-top: 20px;
}

.board-link {
  color: #007bff;
  text-decoration: none;
}

.text-center {
  text-align: center;
  margin-top: 20px;
}

.empty-message {
  color: #6c757d;
  font-weight: bold;
  font-size: 18px;
}
</style>
