<template>
  <tr>
    <td>{{ no }}</td>
    <td>
      <router-link :to="`/board/detail/` + board_idx">{{
        board_title
      }}</router-link>
    </td>
    <td>{{ board_writer_id }}</td>
    <td>{{ board_view }}회</td>
    <td>{{ board_like }}개</td>
  </tr>
</template>

<script>
export default {
  name: "BoardListRow",
  props: {
    // 상위 component로 부터 넘어온 게시판 글 정보
    no: String,
    board_idx: Number,
    board_title: String,
    board_writer_id: String,
    board_view: Number,
    board_like: Number,
  },
  methods: {},
};
</script>
<style scope>
td {
  text-align: center;
  border-bottom: 1px solid #ddd;
  height: 50px;
}
</style>
