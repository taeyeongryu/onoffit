<template>
  <div>
    <div class="form-group">
      <label for="group_title">그룹 이름</label>
      <input
        type="text"
        class="form-control"
        id="group_title"
        placeholder="그룹 이름을 입력해 주세요"
        v-model="group_title"
      />
      <label for="group_message">간단 설명</label>
      <input
        type="text"
        class="form-control"
        id="group_message"
        placeholder="간단한 소개를 해주세요"
        v-model="group_message"
      />
      <label for="group_description">자세한 설명</label>
      <input
        type="textarea"
        class="form-control"
        id="group_description"
        placeholder="자세한 그룹 소개를 해주세요"
        v-model="group_description"
      />
    </div>
    <!-- 비디오 -->
    <div>
      <label for="input-live">유튜브 검색</label>
      <b-form-input
        id="input-live"
        v-model="keyword"
        aria-describedby="input-live-help input-live-feedback"
        placeholder="검색어를 입력하고 Enter를 누르세요"
        trim
        @keydown.enter="search"
        type="text"
      ></b-form-input>
      <b-form-invalid-feedback id="input-live-feedback">
        Enter at least 1 letters
      </b-form-invalid-feedback>
      <div :class="{ 'video-container': dataExists }">
        <div
          v-for="video in videoList"
          :key="video.id.videoId"
          class="video-item"
        >
          <input type="checkbox" v-model="selectedVideos" :value="video" />
          <div class="video-wrapper">
            <iframe
              :src="'https://www.youtube.com/embed/' + video.id.videoId"
              width="560"
              height="315"
              frameborder="0"
              allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
              allowfullscreen
            ></iframe>
          </div>
          <p>{{ video.snippet.title }}</p>
        </div>
      </div>
      <!--비디오 끝 -->
      <button
        class="btn btn-primary"
        @click="registOnGroup"
        variant="outline-primary"
        :disabled="selectedVideos.length === 0"
      >
        등록
      </button>
      <button class="btn btn-primary" @click="gotoList">취소</button>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  data() {
    return {
      group_title: "",
      group_message: "",
      group_description: "",
      group_leader_user_id: "",
      group_image: "",
      group_orgimage: "",
      //비디오 추가
      keyword: "",
      selectedVideos: [],
    };
  },
  computed: {
    ...mapState(["videoList"]),

    dataExists() {
      console.log("Selected Video Count:", this.selectedVideos.length);
      return this.videoList.length > 0; // 선택된 영상이 있는지 여부를 반환
    },
  },
  methods: {
    gotoList() {
      this.$router.push("/ongroup");
    },
    registOnGroup() {
      // 선택한 영상과 글 내용을 서버에 전송하여 글 등록
      console.log("Selected Videos:", this.selectedVideos);
      let ongroup = {
        group_title: this.group_title,
        group_message: this.group_message,
        group_description: this.group_description,
        group_leader_user_id: this.$store.state.loginUser.user_id,
        group_image: this.group_image,
        group_orgimage: this.group_orgimage,
      };
      this.$store.dispatch("registOnGroup", ongroup);
      // this.$store.dispatch("registVideoList"); //연습용
      console.log(this.$store.state.onGroupListtmp.group_id);

      setTimeout(() => {
        //순서 바뀌어서 group_id 못 가지고 오는 경우가 있어서 시간 지연함.
        this.$store.dispatch("registVideo", {
          videoList: this.selectedVideos,
          group_id: this.$store.state.onGroupListtmp.group_id,
        });
        console.log("selectedVideos");
        console.log(this.selectedVideos);

        setTimeout(() => {
          this.selectedVideos = []; // selectedVideos 초기화
          this.$store.commit("CLEAR_VIDEOLIST"); // videoList 초기화
          this.$router.push("/ongroup");
        }, 1000); // 1초의 지연 시간 설정
      }, 1000); // 1초의 지연 시간 설정
    },
    search() {
      //비디오 찾기
      if (!this.keyword) {
        this.$store.state.videoList = []; //키워드 없으면 검색 리스트 초기화
        return;
      } else {
        this.$store.dispatch("searchYoutube", this.keyword);
      }
    },
  },
};
</script>

<style scoped>
/*@media (max-width: 768px) {
  /* 화면이 작을 때는 1행 2열로 변경 
  .video-container {
    grid-template-columns: repeat(2, 1fr);
  }
}*/

.video-container {
  display: grid;
  grid-template-columns: repeat(5, 1fr); /* 5개의 열로 구성 */
  grid-gap: 20px; /* 각 동영상 사이의 간격 설정 */
  border: 1px solid black;
  padding: 10px;
}

.video-item {
  display: flex;
  flex-direction: column;
}
.video-item p {
  margin-top: 10px;
}

.video-wrapper {
  position: relative;
  padding-bottom: 56.25%;
  overflow: hidden;
}

.video-wrapper iframe {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  border: none;
}
</style>
