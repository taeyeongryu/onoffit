<template>
  <div>
    <div class="header bg-primary">
      <h1 class="text-center text-white">
        {{ $store.state.loginUser.user_id }}님의 마이페이지
      </h1>
    </div>
    <div class="content">
      <div class="section">
        <div class="section-header">
          <b-button v-b-toggle.collapse-2 class="section-title bg-secondary">
            LIKE
          </b-button>
        </div>
        <b-collapse id="collapse-2">
          <board-like></board-like>
        </b-collapse>
      </div>
      <div class="section">
        <div class="section-header">
          <b-button v-b-toggle.collapse-3 class="section-title bg-secondary">
            BOARD
          </b-button>
        </div>
        <b-collapse id="collapse-3">
          <board-write></board-write>
        </b-collapse>
      </div>
    </div>
    <div class="content">
      <div class="section">
        <div class="section-header">
          <b-button v-b-toggle.collapse-4 class="section-title bg-primary">
            ONLINE GROUP
          </b-button>
        </div>
        <b-collapse id="collapse-4">
          <my-on-group-list></my-on-group-list>
        </b-collapse>
      </div>
      <div class="section">
        <div class="section-header">
          <b-button v-b-toggle.collapse-5 class="section-title bg-primary">
            OFFLINE GROUP
          </b-button>
        </div>
        <b-collapse id="collapse-5">
          <my-off-group-list></my-off-group-list>
        </b-collapse>
      </div>
    </div>
  </div>
</template>

<script>
import BoardLike from "@/components/MyPage/BoardLike.vue";
import BoardWrite from "@/components/MyPage/BoardWrite.vue";
import MyOnGroupList from "@/components/MyPage/MyOnGroupList.vue";
import MyOffGroupList from "@/components/MyPage/MyOffGroupList.vue";

export default {
  components: {
    BoardLike,
    BoardWrite,
    MyOnGroupList,
    MyOffGroupList,
  },
  data() {
    return {
      user_id: "",
    };
  },
  mounted() {
    this.user_id = this.$store.state.loginUser.user_id;
  },
};
</script>

<style scoped>
.header {
  font-family: "Gowun Dodum", sans-serif;
  height: 100px;
  background-color: #007bff;
  padding: 7px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.header h1 {
  margin: 0;
  font-size: 24px;
  text-transform: uppercase;
}

.content {
  display: flex;
  justify-content: space-between;
  margin-top: 50px;
  margin-left: 20px;
  margin-right: 20px;
}

.section {
  flex-grow: 1;
  width: 10%;
}

.section-header {
  display: flex;
  justify-content: space-between;
  margin-bottom: 10px;
}

.section-title {
  padding: 10px;
  font-weight: bold;
  text-transform: uppercase;
}

.text-center {
  text-align: center;
}

.no-margin {
  margin: 0;
}

/* Style changes for button colors */
.bg-secondary {
  background-color: #6c757d;
}

.bg-primary {
  background-color: #007bff;
  color: #fff;
}
</style>
