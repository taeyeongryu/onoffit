<template>
  <div class="container">
    <div class="form-group">
      <!-- 그룹 이름 입력 -->
      <div class="form-floating mb-3">
        <input
          type="text"
          class="form-control"
          id="group_title"
          placeholder="그룹 이름을 입력해 주세요"
          v-model="group_title"
        />
        <label for="group_title">그룹 이름</label>
      </div>

      <!-- 간단한 소개 입력 -->
      <div class="form-floating mb-3">
        <input
          type="text"
          class="form-control"
          id="group_message"
          placeholder="간단한 소개를 해주세요"
          v-model="group_message"
        />
        <label for="group_message">간단 설명</label>
      </div>

      <!-- 자세한 소개 입력 -->
      <div class="form-floating mb-3">
        <textarea
          class="form-control"
          id="group_description"
          placeholder="자세한 그룹 소개를 해주세요"
          v-model="group_description"
        ></textarea>
        <label for="group_description">자세한 설명</label>
      </div>
    </div>

    <!-- 주소 입력과 버튼 -->
    <div class="input-group mb-3">
      <input
        type="text"
        id="sample6_postcode"
        placeholder="우편번호"
        class="form-control"
      />
      <button class="btn btn-primary" @click="execDaumPostcode">
        우편번호 찾기
      </button>
    </div>
    <div class="mb-3">
      <input
        type="text"
        id="sample6_address"
        placeholder="주소"
        class="form-control"
      />
    </div>
    <div class="mb-3">
      <input
        type="text"
        id="sample6_extraAddress"
        placeholder="참고항목"
        class="form-control"
      />
    </div>
    <div class="mb-3">
      <input
        type="text"
        id="sample6_detailAddress"
        placeholder="상세주소"
        class="form-control"
      />
    </div>

    <!-- 등록 버튼과 취소 버튼 -->
    <div class="d-grid gap-3">
      <div>
        <button class="btn btn-primary" @click="registOffGroup">등록</button>
        <button class="btn btn-secondary" @click="gotoList">취소</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    type: String,
  },
  data() {
    return {
      isDaumScriptLoaded: false,
      group_title: "",
      group_message: "",
      group_description: "",
      group_leader_user_id: "",
      group_address: "",
      group_image: "",
      group_orgimage: "",
    };
  },

  mounted() {
    const script = document.createElement("script");
    script.src =
      "//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js";
    document.body.appendChild(script);
    script.onload = () => {
      this.isDaumScriptLoaded = true;
    };
  },

  methods: {
    gotoList() {
      this.$router.push("/offgroup");
    },
    registOffGroup() {
      let ongroup = {
        group_title: this.group_title,
        group_message: this.group_message,
        group_description: this.group_description,
        group_leader_user_id: this.$store.state.loginUser.user_id,
        group_address: this.addr,
        group_image: this.group_image,
        group_orgimage: this.group_orgimage,
      };
      this.$store.dispatch("registOffGroup", ongroup);
      setTimeout(() => {
        this.$router.push("/offgroup");
      }, 500);
    },
    execDaumPostcode() {
      if (this.isDaumScriptLoaded) {
        new window.daum.Postcode({
          oncomplete: (data) => {
            var addr = ""; // 주소 변수
            var extraAddr = ""; // 참고항목 변수

            if (data.userSelectedType === "R") {
              addr = data.roadAddress;
            } else {
              addr = data.jibunAddress;
            }

            if (data.userSelectedType === "R") {
              if (data.bname !== "" && /[동|로|가]$/g.test(data.bname)) {
                extraAddr += data.bname;
              }
              if (data.buildingName !== "" && data.apartment === "Y") {
                extraAddr +=
                  extraAddr !== ""
                    ? ", " + data.buildingName
                    : data.buildingName;
              }
              if (extraAddr !== "") {
                extraAddr = " (" + extraAddr + ")";
              }
              document.getElementById("sample6_extraAddress").value = extraAddr;
            } else {
              document.getElementById("sample6_extraAddress").value = "";
            }

            document.getElementById("sample6_postcode").value = data.zonecode;
            document.getElementById("sample6_address").value = addr;
            document.getElementById("sample6_detailAddress").focus();
            this.addr = addr;
          },
        }).open();
      }
    },
  },
};
</script>

<style scoped>
* {
  font-family: "Gowun Dodum", sans-serif;
}
.container {
  margin-top: 30px;
}

.form-floating {
  margin-bottom: 1.5rem;
}

.input-group {
  margin-bottom: 1.5rem;
}

.btn-secondary {
  margin-right: 0.5rem;
}
</style>
