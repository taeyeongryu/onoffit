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
      <input type="text" id="sample6_postcode" placeholder="우편번호" />
      <input
        type="button"
        @click="execDaumPostcode"
        value="우편번호 찾기"
      /><br />
      <input type="text" id="sample6_address" placeholder="주소" /><br />
      <input type="text" id="sample6_extraAddress" placeholder="참고항목" />
      <input type="text" id="sample6_detailAddress" placeholder="상세주소" />
      <button class="btn btn-primary" @click="modifyOffGroup">수정</button>
      <button class="btn btn-primary" @click="gotoList">취소</button>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  data() {
    return {
      isDaumScriptLoaded: false,
      group_id: this.$store.state.offGroup.group_id,
      group_title: this.$store.state.offGroup.group_title,
      group_message: this.$store.state.offGroup.group_message,
      group_description: this.$store.state.offGroup.group_description,
      group_cnt: this.$store.state.offGroup.group_cnt,
      group_leader_user_id: this.$store.state.offGroup.group_leader_user_id,
      group_address: this.$store.state.offGroup.group_address,
      group_image: this.$store.state.offGroup.group_image,
      group_orgimage: this.$store.state.offGroup.group_orgimage,
    };
  },
  computed: {
    ...mapState(["offGroup"]),
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
    modifyOffGroup() {
      let ongroup = {
        group_id: this.group_id,
        group_title: this.group_title,
        group_message: this.group_message,
        group_description: this.group_description,
        group_cnt: this.group_cnt,
        group_leader_user_id: this.group_leader_user_id,
        group_address: this.addr,
        group_image: this.group_image,
        group_orgimage: this.group_orgimage,
      };
      this.$store.dispatch("updateOffGroup", ongroup);
      setTimeout(() => {
        this.$router.push("/offgroup");
      }, 500);
    },

    execDaumPostcode() {
      if (this.isDaumScriptLoaded) {
        new window.daum.Postcode({
          oncomplete: (data) => {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ""; // 주소 변수
            var extraAddr = ""; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === "R") {
              // 사용자가 도로명 주소를 선택했을 경우
              addr = data.roadAddress;
            } else {
              // 사용자가 지번 주소를 선택했을 경우(J)
              addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if (data.userSelectedType === "R") {
              // 법정동명이 있을 경우 추가한다. (법정리는 제외)
              // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
              if (data.bname !== "" && /[동|로|가]$/g.test(data.bname)) {
                extraAddr += data.bname;
              }
              // 건물명이 있고, 공동주택일 경우 추가한다.
              if (data.buildingName !== "" && data.apartment === "Y") {
                extraAddr +=
                  extraAddr !== ""
                    ? ", " + data.buildingName
                    : data.buildingName;
              }
              // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
              if (extraAddr !== "") {
                extraAddr = " (" + extraAddr + ")";
              }
              // 조합된 참고항목을 해당 필드에 넣는다.
              document.getElementById("sample6_extraAddress").value = extraAddr;
            } else {
              document.getElementById("sample6_extraAddress").value = "";
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById("sample6_postcode").value = data.zonecode;
            document.getElementById("sample6_address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("sample6_detailAddress").focus();
            this.addr = addr;
          },
        }).open();
      }
    },
  },
};
</script>

<style></style>
