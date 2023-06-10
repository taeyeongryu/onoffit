import Vue from 'vue'
import Vuex from 'vuex'
import axios from "axios";
// import router from "@/router";

Vue.use(Vuex);
import {createVuexPersistedState} from "vue-persistedstate";
const REST_API = `http://localhost:9999/api`;

export default new Vuex.Store({
  plugins:[createVuexPersistedState()],
  state: {
    aonGroupList :[],
    bonGroupList :[],
    aoffGroupList:[],
    boffGroupList:[],
    onGroupList : [],
    offGroupList : [],
    loginUser : {},
    //offGroupList.vue 페이지에서 detail으로 넘어갈때 업데이트 됨
    offGroup:{},
    onGroup:{},
    //--게시판(이지현)
    //1. BoardList.vue에서 사용됨.
    sboardList: [],
    //2. BoardWriteForm.vue, BoardDetail.vue에서 사용됨.
    boardList: [],
    board: {},
    //3. REGIST_BOARD에서 board 등록시 자동으로 vuex에서도 board_idx를 증가시켜주기 위해서
    currentIndex: 0,
    //--댓글(이지현)
    comment: {}, //댓글
    commentList: [], //댓글 목록
   //--비디오(이지현)
   videoList: [], //유튜브에서 검색한 비디오 목록
   video: {},
   selectedvideoList: [], //글 등록에 선택한 비디오 목록
   onGroupListtmp: [],
   getselectedvideoList: [],

  },

  getters: {
  },

  mutations: {
    SET_AONGROUP(state,payload){
      state.aonGroupList=payload;
    },
    SET_BONGROUP(state,payload){
      state.bonGroupList=payload;
    },
    SET_AOFFGROUP(state,payload){
      state.aoffGroupList=payload;
    },
    SET_BOFFGROUP(state,payload){
      state.boffGroupList=payload;
    },
    //DB에서 가져온 onGroupList  state에 저장
    //태영
    SET_ONGROUP(state, payload){
      state.onGroupList=payload;
    },
    //DB에서 가져온 offGroupList  state에 저장
    //태영
    SET_OFFGROUP(state, payload){
      state.offGroupList=payload;
    },
    //offgroup에서 새로운 group등록하는 메서드
    //태영
    
    //offgroupList에서 detail로 들어갈때 state에 offgroup 설정하는 메서드
    //태영
    SET_OFFGROUP_BY_ID(state, payload){
      for (let index = 0; index < state.offGroupList.length; index++) {
        if (state.offGroupList[index].group_id==payload) {
          state.offGroup=state.offGroupList[index];
          break;
        }
      }
    },
    //ongroupList에서 detail로 들어갈때 state에 ongroup 설정하는 메서드
    //태영
    SET_ONGROUP_BY_ID(state, payload){
      for (let index = 0; index < state.onGroupList.length; index++) {
        if (state.onGroupList[index].group_id==payload) {
          state.onGroup=state.onGroupList[index];
          break;
        }
      }
    },
    //태영
    REGIST_ONGROUP(state, payload) {
      //참여인원 한명으로 설정 해주고 state에 등록해 준다
      payload.group_cnt = 1;
      state.offGroupList.push(payload);
    },
    //태영
    REGIST_OFFGROUP(state, payload) {
      //참여인원 한명으로 설정 해주고 state에 등록해 준다
      payload.group_cnt = 1;
      state.onGroupList.push(payload);
    },
    //태영
    UPDATE_OFFGROUP(state, payload){
      for (let index = 0; index < state.offGroupList.length; index++) {
        if (state.offGroupList[index].group_id===payload.group_id) {
          state.offGroupList[index]=payload;
        }
      }
    },
    //태영
    UPDATE_ONGROUP(state, payload){
      for (let index = 0; index < state.onGroupList.length; index++) {
        if (state.onGroupList[index].group_id===payload.group_id) {
          state.onGroupList[index]=payload;
        }
      }
    },
    //참가버튼 누르는 메서드
    //태영
    PUT_OFFGROUP_JOIN(state){
      for (let index = 0; index < state.offGroupList.length; index++) {
        if (state.offGroupList[index].group_id===state.loginUser.group_id) {
          if (state.offGroupList[index].joined) {
            state.offGroupList[index].joined=false;
          }
          else{
            state.offGroupList[index].joined=true;
          }
        }
      }
    },
    //참가버튼 누르는 메서드
    //태영
    PUT_ONGROUP_JOIN(state){
      for (let index = 0; index < state.onGroupList.length; index++) {
        if (state.onGroupList[index].group_id===state.loginUser.group_id) {
          if (state.onGroupList[index].joined) {
            state.onGroupList[index].joined=false;
          }
          else{
            state.onGroupList[index].joined=true;
          }
        }
      }
    },
  
    SET_LOGINUSER(state, payload){
      state.loginUser=payload;
    },
    //로그아웃으로, 저장된 vuex에 저장된 로그인 유저와 그룹리스트 전부를 날린다.-이준혁
    LOGOUT(state) {
      state.loginUser.user_id = "";
      state.onGroupList=[];
      state.offGroupList=[];
    },
    
    //--게시판
    SET_BOARDLIST(state, payload) {
      state.boardList = payload;
    },
    //searchBoard()에서 사용.
    SET_SBOARDLIST(state, payload) {
      state.sboardList = payload;
    },
    //registBoard()에서 사용. WriteForm에서 등록한 board를 vuex state에도 저장하기 위해서
    //BoardDetail.vue에서 사용. detail에 들어갈 때 board를 다시 갱신. 이때 저장된 board를 BoardModify.vue에서 사용
    SET_BOARD(state, board) {
      state.board = board;
    },
    // // registBoard()에서 사용
    REGIST_BOARD_TO_BOARDLIST(state, board) {
      state.boardList.push(board);
    },
    //changelikeBoard()에서 사용
    UPDATE_BOARD(state, payload) {
      state.board = payload;
    },
    //deleteBoard()에서 사용
    DELETE_BOARD(state, board) {
      const index = state.boardList.findIndex(
        (item) => item.board_idx === board.board_idx
      );
      if (index !== -1) {
        state.boardList.splice(index, 1);
      }
    },
    //--댓글
    REGIST_COMMENT(state, comment) {
      state.commentList.push(comment);
    },
    GET_COMMENTLIST(state, commentList) {
      state.commentList = commentList;
    },
    UPDATE_COMMENT(state, comment) {
      state.comment = comment;
    },
    DELETE_COMMENT(state, comment) {
      const index = state.commentList.findIndex(
        (item) => item.board_idx === comment.board_idx
      );
      if (index !== -1) {
        state.commentList.splice(index, 1);
      }
    },
    //--비디오
    SEARCH_YOUTUBE(state, videoList) {
      state.videoList = videoList;
      console.log(videoList);
    },
    REGIST_VIDEO(state, selectedvideoList) {
      state.selectedvideoList = selectedvideoList;
      console.log(selectedvideoList);
    },
    REGIST_ONGROUP_DB(state, result) {
      state.onGroupListtmp = result;
      console.log(result);
    },
    GET_SELECTEDVIDEOLIST(state, result) {
      state.getselectedvideoList = result;
      console.log("group_id에 맞는 선택된 거 가져와");
      console.log(result);
    },
    CLEAR_VIDEOLIST(state) {
      state.videoList = []; // videoList 초기화
      //안 해주면 새로운 글 등록하러가도 이전에 검색했던 키워드를 중심으로 유튜브영상이 뿌려짐.
    },
  },
  actions: {
    // 내가 만들거나 들어간 온라인 그룹을 찾아서 state의 onGroupList에 집어넣는 메서드로, 마이페이지로 이동할때 작동한다.-이준혁
    setMyOnGroup(context) {
      this.state.aonGroupList = [];
      this.state.bonGroupList = [];
      const API_URL = `${REST_API}/group/myongroup`;
      console.log(this.state.loginUser.user_id);
      axios({
        url: API_URL,
        method: "GET",
        params: {
          user_id: this.state.loginUser.user_id,
        },
      }).then((res) => {
        context.commit("SET_ONGROUP", res.data);
        this.state.onGroupList.forEach((element) => {
          element.joined=true;
          if (element.group_leader_user_id === this.state.loginUser.user_id) {
            this.state.aonGroupList.push(element);
          } else {
            this.state.bonGroupList.push(element);
          }
        });
      });
    },
    // 내가 만들거나 들어간 오프라인 그룹을 찾아서 state의 offGroupList에 집어넣는 메서드로, 마이페이지로 이동할때 작동한다.-이준혁
    setMyOffGroup(context) {
      this.state.aoffGroupList = [];
      this.state.boffGroupList = [];
      const API_URL = `${REST_API}/group/myoffgroup`;
      axios({
        url: API_URL,
        method: "GET",
        params: {
          user_id: this.state.loginUser.user_id,
        },
      }).then((res) => {
        context.commit("SET_OFFGROUP", res.data);
        this.state.offGroupList.forEach((element) => {
          element.joined=true;
          if (element.group_leader_user_id === this.state.loginUser.user_id) {
            this.state.aoffGroupList.push(element);
          } else {
            this.state.boffGroupList.push(element);
          }
        });
      });
    },
    // 새 유저를 등록할때 실행하는 메서드로, 회원가입에서 내용을 기입하고 회원가입을 눌렀을때 작동된다.-이준혁
    createUser(context, user) {
      const API_URL = `${REST_API}/regist`;
      console.log(user),
      axios({
        url: API_URL,
        method: "POST",
        params:{
          user_id:user.user_id,
          user_password:user.user_password,
          user_nickname:user.user_nickname,
          user_name:user.user_name,
          user_email:user.user_email,
        },
      }).then(() => {
        alert("등록이 완료되었습니다.");
      });
    },
    //로그인된 유저를 vuex에 등록할때 실행하는 메서드로, 로그인 버튼을 눌렀을 때 작동한다.-이준혁
    setLoginUser(context, user) {
      const API_URL = `${REST_API}/login`;
      console.log(user);
      axios({
        url: API_URL,
        method: "POST",
        params:{
          user_id:user.user_id,
          user_password:user.user_password,
        },
      })
        .then((res) => {
          context.commit("SET_LOGINUSER", res.data["loginUser"]);
          sessionStorage.setItem("access-token", res.data["access-token"]);
          alert(`${res.data["loginUser"].user_id}님 반갑습니다!`);
          
        })
        .catch(() => {
          alert("아이디나 비밀번호가 일치하지 않습니다.");
        });
    },
    //로그아웃을 눌렀을때 실행되는 메서드로, vuex에 등록된 로그인된 유저, 온라인 그룹 리스트,오프라인 그룹 리스트 전부를 초기화 시키는 역할을 한다.-이준혁
    logout({ commit }) {
      sessionStorage.setItem("access-token", null);
      commit("LOGOUT");
    },
    //onGroup 등록할때 비디오 배열로 넘기는 메서드
    //태영
    //수정 조금 해야함
    registVideoList(){
      const API_URL=`${REST_API}/video`;
      //잘된다 이형식으로 넘기면 된다. 최종 형식 제발 잘 되자.....ㅜㅜ
      var myVideoList = {
        videoList: [
          {
            "video_id":"video_id",
            "video_title":"video_title",
            "video_url":"video_url"
          },
          {
            "video_id":"video_id2",
            "video_title":"video_title2",
            "video_url":"video_url2"
          }
        ],
        group_id: 2
      };
      axios({
        url:API_URL,  
        method: 'post',
        data : myVideoList
        }).then(res=>{
        console.log(res);
      }).catch(err=>{
        console.log(err);
      });
    },

    //offgroup 등록하는 메서드
    //태영
    registOffGroup({commit},payload){
      const API_URL=`${REST_API}/group/off`;
      axios({
        url:API_URL,  
        method: 'post',
        data : payload
      }).then(res=>{
        console.log(res);
        commit("REGIST_OFFGROUP",payload);
      }).catch(err=>{
        console.log(err);
      });
    },
    //ongroup 등록하는 메서드
    //태영
    registOnGroup({commit},payload){
      const API_URL=`${REST_API}/group/on`;
      axios({
        url:API_URL,  
        method: 'post',
        data : payload
      }).then(res=>{
        console.log(res);
        commit("REGIST_ONGROUP",payload);
        commit("REGIST_ONGROUP_DB",res.data);
      }).catch(err=>{
        console.log(err);
      });
    },
    //ongroup 업데이트하는 메서드
    //태영
    updateOnGroup({commit},payload){
      console.log(payload);
      const API_URL=`${REST_API}/group/on`;
      axios({
        url:API_URL,  
        method: 'put',
        data : payload
        }).then(res=>{
          commit("UPDATE_ONGROUP",payload);
          console.log(res);
      }).catch(err=>{
        console.log(err);
      });
    },
    //offgroup 업데이트하는 메서드
    //태영
    updateOffGroup({commit},payload){
      console.log(payload);
      const API_URL=`${REST_API}/group/off`;
      axios({
        url:API_URL,  
        method: 'put',
        data : payload
        }).then(res=>{
          commit("UPDATE_OFFGROUP",payload);
          console.log(res);
      }).catch(err=>{
        console.log(err);
      });
    },
    //그룹 삭제하는 메서드
    //태영
    deleteOffGroup({commit},payload){
      commit
      console.log(payload);
      const API_URL=`${REST_API}/group/off`;
      axios({
        url:API_URL,  
        method: 'delete',
        data : payload
        }).then(res=>{
          console.log(res);
      }).catch(err=>{
        console.log(err);
      });
    },
     //그룹 삭제하는 메서드
    //태영
    deleteOnGroup({commit},payload){
      commit
      console.log(payload);
      const API_URL=`${REST_API}/group/on`;
      axios({
        url:API_URL,  
        method: 'delete',
        data : payload
        }).then(res=>{
          console.log(res);
      }).catch(err=>{
        console.log(err);
      });
    },
    //태영
    putongroupjoin({commit},payload){
      const onGroup = this.state.onGroup;
      const user_id = this.state.loginUser.user_id;
      console.log(payload);
      console.log(user_id);
      console.log(onGroup);
      const API_URL=`${REST_API}/group/on/putjoin`;
      axios({
        url:API_URL,  
        method: 'put',
        data : {
          user_id:user_id,
          onGroup:onGroup,
        }
        }).then(res=>{
          commit('PUT_ONGROUP_JOIN')
          console.log(res);
      }).catch(err=>{
        console.log(err);
      });
    },
    //태영
    putoffgroupjoin({commit},payload){
      const offGroup = this.state.offGroup;
      const user_id = this.state.loginUser.user_id;
      console.log(payload);
      console.log(user_id);
      console.log(offGroup);
      const API_URL=`${REST_API}/group/off/putjoin`;
      axios({
        url:API_URL,  
        method: 'put',
        data : {
          user_id:user_id,
          offGroup:offGroup,
        }
        }).then(res=>{
          commit('PUT_OFFGROUP_JOIN')
          console.log(res);
      }).catch(err=>{
        console.log(err);
      });
    },
    //로그인 한 유저를 기반으로 ongroupList가져오는 메서드
    //태영
    getOnGroup({commit}){
      const API_URL=`${REST_API}/group/on`;
      axios({
        url:API_URL,
        method:"get",
        params: {
          user_id: this.state.loginUser.user_id
        }
      }).then((res)=>{
        console.log(res);
        commit("SET_ONGROUP",res.data);
      }).catch((err)=>{
        console.log(err);
      }
      );
    },
    //로그인 한 유저를 기반으로 offgroupList가져오는 메서드
    //태영
    getOffGroup({commit}){
      const API_URL=`${REST_API}/group/off`;
      axios({
        url:API_URL,
        method:"get",
        params: {
          //여기에 로그인한 user_id가 들어가야 한다.
          user_id: this.state.loginUser.user_id
        }
      }).then((res)=>{
        console.log(res);
        commit("SET_OFFGROUP",res.data);
      }).catch((err)=>{
        console.log(err);
      }
      );
    },
    //--게시판
    //BoardWriteForm에서 사용됨.
    //board를 db와 vuex state에 등록함
    registBoard({ commit }, board) {
      const API_URL = `${REST_API}/board`;
      axios({
        url: API_URL,
        method: "POST",
        params: board,
      })
        .then(() => {
          commit;
          commit("REGIST_BOARD_TO_BOARDLIST", board);
          alert("잘 등록되었습니다.");
        })
        .catch((err) => {
          alert("등록되지 않았습니다.");
          console.log(err);
        });
    },
    //BoardModify.vue에서 사용됨.
    //db와 vuex state에 있는 board 수정함
    updateBoard({ commit }, board) {
      const API_URL = `${REST_API}/board`;
      axios({
        url: API_URL,
        method: "PUT",
        params: board,
      })
        .then(() => {
          commit("UPDATE_BOARD", board);
          alert("잘 수정되었습니다.");
        })
        .catch((err) => {
          alert("수정되지 않았습니다.");
          console.log(err);
        });
    },
    //BoardViewDetail.vue에서 사용
    //board를 db와 vuex에서 삭제
    deleteBoard({ commit }, board) {
      const API_URL = `${REST_API}/board`;
      axios({
        url: API_URL,
        method: "DELETE",
        params: board,
      })
        .then(() => {
          commit("DELETE_BOARD", board);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getBoardList({ commit }, payload) {
      const { key, word } = payload;
      const API_URL = `${REST_API}/board`;
      axios({
        url: API_URL,
        method: "GET",
        params: {
          user_id: this.state.loginUser.user_id,
          key: key,
          word: word,
        },
      })
        .then((res) => {
          commit("SET_BOARDLIST", res.data);
        })
        .catch((err) => {
          alert("1. 등록되지 않았습니다.");
          console.log(err);
        });
    },
    getBoard({ commit }, board_idx) {
      const API_URL = `${REST_API}/board/one`;
      axios({
        url: API_URL,
        method: "GET",
        params: {
          user_id: this.state.loginUser.user_id,
          board_idx: board_idx,
        },
      })
        .then((res) => {
          commit("SET_BOARD", res.data);
        })
        .catch((err) => {
          alert("등록되지 않았습니다.");
          console.log(err);
        });
    },
    //BoardViewDetail.vue에서 사용
    //board의 좋아요를 조작함.
    changelikeBoard({ commit }, payload) {
      const board = payload.board;
      const user_id = payload.user_id;
      const API_URL = `${REST_API}/board/putlike`;
      console.log(board);
      console.log(this.state.loginUser.user_id);
      axios({
        url: API_URL,
        method: "PUT",
        data: {
          user_id: user_id,
          board: board,
        },
      })
        .then(() => {
          if (board.liked) {
            board.liked = false;
            board.board_like--;
          } else {
            board.liked = true;
            board.board_like++;
          }
          commit("SET_BOARD", board);
          console.log("바뀐 값");
          console.log(board);
          alert("좋아합니다.");
        })
        .catch((err) => {
          alert("좋아하지 않습니다.");
          console.log(err);
        });
    },
    //BoardList.vue에서 사용
    //db에서 조건에 맞는 board를 가져와서 vuex의 sboardList에 저장
    searchBoard({ commit }, payload) {
      const { key, word } = payload;
      const API_URL = `${REST_API}/board`;
      axios({
        url: API_URL,
        method: "GET",
        params: {
          user_id: this.state.loginUser.user_id,
          key: key,
          word: word,
        },
      })
        .then((res) => {
          commit("SET_SBOARDLIST", res.data);
          alert("잘 등록되었습니다.");
        })
        .catch((err) => {
          alert("등록되지 않았습니다.");
          console.log(err);
        });
    },
    //--댓글
    createComment({ commit }, comment) {
      const API_URL = `${REST_API}/comment`;
      axios({
        url: API_URL,
        method: "POST",
        data: comment,
      })
        .then(() => {
          commit("REGIST_COMMENT", comment); //commentList에 댓글 등록
          alert("잘 등록되었습니다.");
        })
        .catch((err) => {
          alert("등록되지 않았습니다.");
          console.log(err);
        });
    },
    updateComment({ commit }, comment) {
      const API_URL = `${REST_API}/comment`;
      axios({
        url: API_URL,
        method: "PUT",
        data: comment,
      })
        .then(() => {
          commit("UPDATE_COMMENT", comment);
          alert("잘 수정되었습니다.");
        })
        .catch((err) => {
          alert("수정되지 않았습니다.");
          console.log(err);
        });
    },
    deleteComment({ commit }, comment) {
      const API_URL = `${REST_API}/comment`;
      axios({
        url: API_URL,
        method: "DELETE",
        data: comment,
      })
        .then(() => {
          commit("DELETE_COMMENT", comment);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 댓글 리스트 가져오기
    getCommentList({ commit }, board_idx) {
      const API_URL = `${REST_API}/comment/${board_idx}`;
      axios({
        url: API_URL,
        method: "GET",
      })
        .then((res) => {
          commit("GET_COMMENTLIST", res.data);
        })
        .catch((err) => {
          alert("가져오지 못 했습니다.");
          console.log(err);
        });
    },
    //--비디오
    //동영상 검색
    searchYoutube({ commit }, payload) {
      const URL = "https://www.googleapis.com/youtube/v3/search";
      // const API_KEY = process.env.VUE_APP_YOUTUBE_API_KEY;
      const API_KEY = "AIzaSyCSA3ZfQOjrcH1s461WwAhQKuaibSdUGJI";
      console.log(API_KEY);
      axios({
        url: URL,
        method: "GET",
        params: {
          key: API_KEY,
          part: "snippet",
          q: payload + "운동",
          type: "video",
          maxResults: 10,
        },
      })
        .then((res) => {
          commit("SEARCH_YOUTUBE", res.data.items);
        })
        .catch((err) => console.log(err));
    },
    // 선택한 동영상 등록
    registVideo({ commit }, payload) {
      const API_URL = `${REST_API}/video`;
      console.log(payload.group_id);
      var myVideoList = {
        videoList: payload.videoList.map((video) => ({
          video_id: video.id.videoId,
          video_title: video.snippet.title,
          video_url: video.snippet.thumbnails.default.url,
        })),
        group_id: payload.group_id,
      };
      axios({
        url: API_URL,
        method: "POST",
        data: myVideoList,
      })
        .then(() => {
          commit("REGIST_VIDEO", myVideoList);
          alert("잘 등록되었습니다.");
        })
        .catch((err) => {
          alert("등록되지 않았습니다.");
          console.log(err);
          console.log(myVideoList.group_id);
        });
    },
    // 선택한 동영상 리스트 가져오기 (상세보기에서 가져와서 등록하면 될 듯!!!)
    getVideoList({ commit }, group_id) {
      const API_URL = `${REST_API}/video`;
      axios({
        url: API_URL,
        method: "GET",
        params: { group_id },
      })
        .then((res) => {
          console.log("여기까지는 성공");
          commit("GET_SELECTEDVIDEOLIST", res.data);
        })
        .catch((err) => {
          alert("가져오지 못 했습니다.");
          console.log(err);
        });
    },
    //비디오 수정하기
    // updateOnGroup({ commit }, group_id) {},
    // 선택한 동영상 삭제
    deleteVideo({ commit }, video) {
      const API_URL = `${REST_API}/video`;
      axios({
        url: API_URL,
        method: "DELETE",
        data: video,
      })
        .then(() => {
          commit("DELETE_VIDEO", video);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  modules: {
  }
});
