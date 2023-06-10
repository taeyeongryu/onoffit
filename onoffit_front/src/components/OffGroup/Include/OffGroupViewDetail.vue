<template>
  <div>
    <div class="container">
      <div class="row mb-3">
        <label for="group_title" class="col-sm-2 col-form-label"
          >그룹 이름</label
        >
        <div class="col-sm-10">
          <input
            type="text"
            class="form-control"
            id="group_title"
            readonly
            :value="`${offGroup.group_title}`"
          />
        </div>
      </div>
      <div class="row mb-3">
        <label for="group_message" class="col-sm-2 col-form-label"
          >한줄 글</label
        >
        <div class="col-sm-10">
          <input
            type="text"
            class="form-control"
            id="group_message"
            readonly
            :value="`${offGroup.group_message}`"
          />
        </div>
      </div>
      <div class="row mb-3">
        <label for="group_description" class="col-sm-2 col-form-label"
          >설명</label
        >
        <div class="col-sm-10">
          <input
            type="text"
            class="form-control"
            id="group_description"
            readonly
            :value="`${offGroup.group_description}`"
          />
        </div>
      </div>
      <div class="row mb-3">
        <label for="group_cnt" class="col-sm-2 col-form-label">참여인원</label>
        <div class="col-sm-10">
          <input
            type="text"
            class="form-control"
            id="group_cnt"
            readonly
            :value="`${offGroup.group_cnt}`"
          />
        </div>
      </div>
      <div class="row mb-3">
        <label for="group_reader_user_id" class="col-sm-2 col-form-label"
          >방장</label
        >
        <div class="col-sm-10">
          <input
            type="text"
            class="form-control"
            id="group_reader_user_id"
            readonly
            :value="`${offGroup.group_leader_user_id}`"
          />
        </div>
      </div>
      <div class="row mb-3">
        <label for="group_address" class="col-sm-2 col-form-label">주소</label>
        <div class="col-sm-10">
          <input
            type="text"
            class="form-control"
            id="group_address"
            readonly
            :value="`${offGroup.group_address}`"
          />
        </div>
      </div>
      <div class="d-flex justify-content-between align-items-center mt-4">
        <div
          v-if="
            offGroup.group_leader_user_id !==
              this.$store.state.loginUser.user_id &&
            this.$store.state.loginUser.user_id !== ''
          "
        >
          <button
            v-if="offGroup.joined == true"
            class="btn btn-primary mr-2"
            @click="putoffgroupjoin"
          >
            참가취소
          </button>
          <button
            v-if="offGroup.joined == false"
            class="btn btn-primary mr-2"
            @click="putoffgroupjoin"
          >
            참가
          </button>
        </div>
        <div v-else-if="this.$store.state.loginUser.user_id !== ''">
          <button class="btn btn-primary mr-2" @click="gotoModify">수정</button>
          <button class="btn btn-primary" @click="deleteGroup">삭제</button>
        </div>
        <button class="btn btn-primary" @click="gotoList">목록</button>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  computed: {
    ...mapState(["offGroup"]),
  },
  created() {
    const pathName = new URL(document.location).pathname.split("/");
    const group_id = pathName[pathName.length - 1];
    this.$store.commit("SET_OFFGROUP_BY_ID", group_id);
  },
  methods: {
    deleteGroup() {
      const pathName = new URL(document.location).pathname.split("/");
      const group_id = pathName[pathName.length - 1];
      this.$store.dispatch("deleteOffGroup", group_id);
      setTimeout(() => {
        this.$router.push("/offgroup");
      }, 500);
    },
    gotoList() {
      this.$router.push("/offgroup");
    },
    gotoModify() {
      const pathName = new URL(document.location).pathname.split("/");
      const group_id = pathName[pathName.length - 1];
      this.$router.push("/offgroup/modify/" + group_id);
    },
    putoffgroupjoin() {
      const pathName = new URL(document.location).pathname.split("/");
      const group_id = pathName[pathName.length - 1];
      this.$store.dispatch("putoffgroupjoin", group_id);
      setTimeout(() => {
        this.$router.push("/offgroup");
      }, 500);
    },
  },
};
</script>

<style scoped>
.container {
  margin-top: 30px;
}

.row.mb-3 {
  margin-bottom: 20px;
}

.col-form-label {
  font-weight: bold;
}

.d-flex.justify-content-between.align-items-center {
  margin-top: 30px;
}
</style>
