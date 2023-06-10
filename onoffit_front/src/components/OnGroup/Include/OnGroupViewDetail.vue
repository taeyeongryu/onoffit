<template>
  <div>
    <div class="container">
      <div class="row">
        <div class="col-12">
          <div class="mb-3">
            <label for="group_title" class="form-label">그룹 이름</label>
            <input
              type="text"
              class="form-control"
              id="group_title"
              readonly
              :value="`${onGroup.group_title}`"
            />
          </div>
          <div class="mb-3">
            <label for="group_message" class="form-label">한줄 글</label>
            <input
              type="text"
              class="form-control"
              id="group_message"
              readonly
              :value="`${onGroup.group_message}`"
            />
          </div>
          <div class="mb-3">
            <label for="group_description" class="form-label">설명</label>
            <input
              type="text"
              class="form-control"
              id="group_description"
              readonly
              :value="`${onGroup.group_description}`"
            />
          </div>
          <div class="mb-3">
            <label for="group_cnt" class="form-label">참여인원</label>
            <input
              type="text"
              class="form-control"
              id="group_cnt"
              readonly
              :value="`${onGroup.group_cnt}`"
            />
          </div>
          <div class="mb-3">
            <label for="group_reader_user_id" class="form-label">방장</label>
            <input
              type="text"
              class="form-control"
              id="group_reader_user_id"
              readonly
              :value="`${onGroup.group_leader_user_id}`"
            />
          </div>
          <!-- 영상 여기부터 -->
          <div>
            <div
              v-for="video in getselectedvideoList"
              :key="video.video_id"
              class="video-item"
            >
              <div class="video-wrapper">
                <iframe
                  :src="'https://www.youtube.com/embed/' + video.video_id"
                  width="560"
                  height="315"
                  frameborder="0"
                  allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
                  allowfullscreen
                ></iframe>
              </div>
              <p>{{ video.video_title }}</p>
            </div>
          </div>
          <!-- 영상 여기까지 -->
          <div class="mt-3">
            <button
              v-if="
                onGroup.joined === true &&
                this.$store.state.loginUser.user_id !== '' &&
                this.$store.state.loginUser.user_id !==
                  onGroup.group_leader_user_id
              "
              class="btn btn-primary me-2"
              @click="putongroupjoin"
            >
              참가취소
            </button>
            <button
              v-if="
                onGroup.joined === false &&
                this.$store.state.loginUser.user_id !== '' &&
                this.$store.state.loginUser.user_id !==
                  onGroup.group_leader_user_id
              "
              class="btn btn-primary me-2"
              @click="putongroupjoin"
            >
              참가
            </button>
            <button
              v-if="
                this.$store.state.loginUser.user_id ===
                onGroup.group_leader_user_id
              "
              class="btn btn-primary me-2"
              @click="gotoModify"
            >
              수정
            </button>
            <button
              v-if="
                this.$store.state.loginUser.user_id ===
                onGroup.group_leader_user_id
              "
              class="btn btn-primary me-2"
              @click="deleteGroup"
            >
              삭제
            </button>
            <button class="btn btn-primary" @click="gotoList">목록</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  computed: {
    ...mapState(["onGroup"]),
    ...mapState(["getselectedvideoList"]),
  },
  created() {
    const pathName = new URL(document.location).pathname.split("/");
    const group_id = pathName[pathName.length - 1];
    this.$store.commit("SET_ONGROUP_BY_ID", group_id);
    this.$store.dispatch("getVideoList", group_id);
  },
  methods: {
    deleteGroup() {
      const pathName = new URL(document.location).pathname.split("/");
      const group_id = pathName[pathName.length - 1];
      this.$store.dispatch("deleteOnGroup", group_id);
      setTimeout(() => {
        this.$router.push("/ongroup");
      }, 500);
    },
    gotoList() {
      this.$router.push("/ongroup");
    },
    gotoModify() {
      const pathName = new URL(document.location).pathname.split("/");
      const group_id = pathName[pathName.length - 1];
      this.$router.push("/ongroup/modify/" + group_id);
    },
    putongroupjoin() {
      const pathName = new URL(document.location).pathname.split("/");
      const group_id = pathName[pathName.length - 1];
      this.$store.dispatch("putongroupjoin", group_id);
      setTimeout(() => {
        this.$router.push("/ongroup");
      }, 500);
    },
  },
};
</script>

<style scoped>
.video-item {
  margin-bottom: 20px;
}

.video-wrapper {
  position: relative;
  width: 100%;
  height: 0;
  padding-bottom: 56.25%;
}

.video-wrapper iframe {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
}

.btn {
  margin-right: 10px;
}
</style>
