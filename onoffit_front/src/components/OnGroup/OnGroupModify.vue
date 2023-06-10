<template>
  <div>
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
      <!-- <input type="text" id="sample6_postcode" placeholder="우편번호" /> -->
      <!-- 비디오 -->
      <!-- 
      <label for="input-live">유튜브 검색</label>
      <b-form-input
        id="input-live"
        v-model="keyword"
        aria-describedby="input-live-help input-live-feedback"
        placeholder="검색어를 입력하세요"
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
      </div> -->
      <!--비디오 -->
      <button class="btn btn-primary" @click="modifyOnGroup">수정</button>
      <button class="btn btn-primary" @click="gotoList">취소</button>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  data() {
    return {
      group_id: this.$store.state.onGroup.group_id,
      group_title: this.$store.state.onGroup.group_title,
      group_message: this.$store.state.onGroup.group_message,
      group_description: this.$store.state.onGroup.group_description,
      group_cnt: this.$store.state.onGroup.group_cnt,
      group_leader_user_id: this.$store.state.onGroup.group_leader_user_id,
      group_image: this.$store.state.onGroup.group_image,
      group_orgimage: this.$store.state.onGroup.group_orgimage,
      //비디오
      // video_id: this.$store.state.getselectedvideoList.video_id,
      // video_title: this.$store.state.getselectedvideoList.video_title,
      // video_url: this.$store.state.getselectedvideoList.video_url,
    };
  },

  computed: {
    ...mapState(["onGroup"]),
    // ...mapState(["getselectedvideoList"]),
  },

  methods: {
    gotoList() {
      this.$router.push("/ongroup");
    },
    modifyOnGroup() {
      let ongroup = {
        group_id: this.group_id,
        group_title: this.group_title,
        group_message: this.group_message,
        group_description: this.group_description,
        group_cnt: this.group_cnt,
        group_leader_user_id: this.group_leader_user_id,
        // group_address: this.addr,
        group_image: this.group_image,
        group_orgimage: this.group_orgimage,
      };
      // let video = {
      //   //비디오
      //   video_id: this.video_id,
      //   video_title: this.video_title,
      //   video_url: this.video_url,
      // };
      this.$store.dispatch("updateOnGroup", ongroup);
      // this.$store.dispatch("updateVideo", video);
      setTimeout(() => {
        this.$router.push("/ongroup");
      }, 500);
    },
  },
};
</script>

<style></style>
