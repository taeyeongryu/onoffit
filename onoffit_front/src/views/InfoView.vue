<template>
  <div class="intro-container">
    <h1 class="intro-title">ONOFFIT 소개</h1>
    <!-- <div class="intro-description">
      <p>우리 팀은 ONOFFIT입니다.</p>
      <p>ONOFFIT은 온라인과 오프라인을 연결하는 플랫폼입니다.</p>
      <p>우리의 목표는 사람들을 모으고 소통하며 성장하는 것입니다.</p>
    </div> -->
    <h2 class="intro-subtitle">팀원 소개</h2>
    <div class="team-members">
      <div class="team-member">
        <img
          src="@/assets/KakaoTalk_20200707_233251285.jpg"
          alt="Team Member 1"
          class="member-image"
        />
        <h3 class="member-name">유태영</h3>
        <p class="member-role">FULLSTACK DEV & PM</p>
      </div>
      <div class="team-member">
        <img
          src="@/assets/메일용.jpg"
          alt="Team Member 2"
          class="member-image"
        />
        <h3 class="member-name">이준혁</h3>
        <p class="member-role">FULLSTACK DEV & PM</p>
      </div>
      <div class="team-member">
        <img
          src="@/assets/2AB80BCD-BB89-482E-A044-65CCF4E183C8.jpg"
          alt="Team Member 3"
          class="member-image"
        />
        <h3 class="member-name">이지현</h3>
        <p class="member-role">FULLSTACK DEV & PM</p>
      </div>
    </div>
  </div>
</template>

<style scoped>
* {
  font-family: "Gowun Dodum", sans-serif;
}
.intro-container {
  height: calc(100vh - 40px); /* 화면의 세로 길이에서 위아래 padding 값 제외 */
  width: 100vw;
  margin: auto; /* 위아래 padding 값에 따라 조정 */
  text-align: center;
  background-image: url("@/assets/luca-bravo-4yta6mU66dE-unsplash.jpg");
  background-size: cover;
  background-position: center;
  padding: 20px; /* 위아래 padding 값 */
}

.intro-title {
  font-family: "Gowun Dodum", sans-serif;
  font-size: 40px;
  margin-bottom: 20px;
  font-weight: bold; /* 글씨체를 두꺼운 스타일로 설정 */
  color: black; /* 흰색으로 설정 */
}

.intro-description {
  font-family: "Gowun Dodum", sans-serif;
  font-size: 35px;
  margin-bottom: 40px;
  color: black; /* 흰색으로 설정 */
}

.intro-subtitle {
  font-family: "Gowun Dodum", sans-serif;
  font-size: 24px;
  margin-top: 25px;
  margin-bottom: 40px;
  font-weight: bold; /* 글씨체를 두꺼운 스타일로 설정 */
  color: black; /* 흰색으로 설정 */
}

.team-members {
  display: flex;
  justify-content: center;
}

.team-member {
  margin: 0 20px;
}

.member-image {
  width: 200px;
  height: 200px;
  border-radius: 50%;
  margin-bottom: 10px;
}

.member-name {
  font-size: 35px;
  margin-bottom: 5px;
  font-weight: bold; /* 글씨체를 두꺼운 스타일로 설정 */
  color: white; /* 흰색으로 설정 */
}

.member-role {
  font-size: 25px;
  color: white;
}
</style>
