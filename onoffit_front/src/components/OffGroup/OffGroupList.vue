<template>
  <div>
    <table class="table table-striped">
      <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col">그룹 이름</th>
          <th scope="col">그룹 설명</th>
          <th scope="col">그룹 리더</th>
          <th scope="col">참가 인원</th>
        </tr>
      </thead>
      <tbody>
        <off-group-list-row
          v-for="(group, index) in paginatedList"
          :key="index"
          :no="`${index + 1}`"
          :group_id="group.group_id"
          :group_title="group.group_title"
          :group_message="group.group_message"
          :group_description="group.group_description"
          :group_cnt="group.group_cnt"
          :group_leader_user_id="group.group_leader_user_id"
          :group_address="group.group_address"
          :group_image="group.group_image"
          :group_orgimage="group.group_orgimage"
        >
        </off-group-list-row>
      </tbody>
    </table>
    <div class="pagination-container">
      <button
        v-for="page in totalPages"
        :key="page"
        :class="{ active: page === currentPage }"
        @click="goToPage(page)"
      >
        {{ page }}
      </button>
    </div>
  </div>
</template>
<script>
import OffGroupListRow from "./Include/OffGroupListRow.vue";
import { mapState } from "vuex";
export default {
  computed: {
    ...mapState(["offGroupList"]),
    totalPages() {
      return Math.ceil(this.offGroupList.length / this.itemsPerPage); // 전체 페이지 수 계산
    },
    paginatedList() {
      const startIndex = (this.currentPage - 1) * this.itemsPerPage; // 시작 인덱스 계산
      const endIndex = startIndex + this.itemsPerPage; // 끝 인덱스 계산
      return this.offGroupList.slice(startIndex, endIndex); // 현재 페이지에 해당하는 목록 반환
    },
  },
  data() {
    return {
      currentPage: 1,
      itemsPerPage: 10,
    };
  },
  mounted() {
    this.$store.dispatch("getOffGroup");
  },
  components: {
    OffGroupListRow,
  },
  methods: {
    goToPage(page) {
      this.currentPage = page; // 페이지 변경
    },
  },
};
</script>
<style scoped>
.table {
  font-size: 14px;
  text-align: center; /* 테이블 내용 가운데 정렬 */
}

.table thead th {
  font-weight: bold;
}

.table-striped tbody tr:nth-of-type(even) {
  background-color: #f9f9f9;
}

.table-group-divider tr:not(:last-child) {
  border-bottom: 1px solid #ddd;
}

.table-group-divider td,
.table-group-divider th {
  padding-top: 10px;
  padding-bottom: 10px;
  text-align: center; /* 테이블 셀 내용 가운데 정렬 */
}

.pagination-container {
  text-align: center;
  margin-top: 20px;
}

.pagination-container button {
  margin-right: 5px;
  border: 1px solid #ddd;
  padding: 5px 10px;
  background-color: white;
  cursor: pointer;
}

.pagination-container button.active {
  font-weight: bold;
}
</style>
