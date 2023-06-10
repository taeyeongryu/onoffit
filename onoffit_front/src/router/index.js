import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import BoardView from "../views/BoardView.vue";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'HomeView',
    component: HomeView
  },
  {
    path: '/ongroup',
    name: 'OnGroupView',
    component: () => import('../views/OnGroupView.vue'),
    children:[
      {
        path: 'create',
        name: 'OnGroupCreate',
        component: ()=> import('../components/OnGroup/OnGroupCreate.vue')
      },
      {
        path: 'detail/:group_id',
        name: 'OnGroupDetail',
        component: ()=>import('../components/OnGroup/OnGroupDetail.vue')
      },
      {
        path:'modify/:group_id',
        name: 'OnGroupModify',
        component:()=>import('../components/OnGroup/OnGroupModify.vue')
      },
      {
        path:'',
        name: 'OnGroupList',
        component:()=>import('../components/OnGroup/OnGroupList.vue')
      }

    ]
  },
  {
    path : '/offgroup',
    name : 'OffGroupView',
    component:() => import('../views/OffGroupView.vue'),
    children:[
      {
        path: 'create',
        name: 'OffGroupCreate',
        component: ()=> import('../components/OffGroup/OffGroupCreate.vue')
      },
      {
        path: 'detail/:group_id',
        name: 'OffGroupDetail',
        component: ()=>import('../components/OffGroup/OffGroupDetail.vue')
      },
      {
        path:'modify/:group_id',
        name: 'OffGroupModify',
        component:()=>import('../components/OffGroup/OffGroupModify.vue')
      },
      {
        path:'',
        name: 'OffGroupList',
        component:()=>import('../components/OffGroup/OffGroupList.vue')
      }
    ]
  },
  {
    path : '/info',
    name : 'InfoView',
    component:()=> import('../views/InfoView.vue')
  },
  {
    path: "/board",
    component: BoardView,
    children: [
      {
        path: "",
        name: "BoardList",
        component: () => import("@/components/Board/BoardList.vue"),
      },
      {
        path: "create",
        name: "BoardCreate",
        component: () => import("@/components/Board/BoardCreate.vue"),
      },
      {
        path: "detail/:board_idx",
        name: "BoardDetail",
        component: () => import("@/components/Board/BoardDetail.vue"),
      },
      {
        path: "modify/:board_idx",
        name: "BoardModify",
        component: () => import("@/components/Board/BoardModify.vue"),
      },
    ],
  },
  {
    path:'/login',
    name:'LoginView',
    component:()=>import('../views/LoginView.vue')
  },
  {
    path:'/mypage',
    name:'MypageView',
    component:()=>import('../views/MyPageView.vue'),
    children:[
      {
        path:'on/:user_id',
        name:'MyOnGroupList',
        component:()=>import('../components/MyPage/MyOnGroupList.vue'),
      },
      {
        path:'off/:user_id',
        name:'MyOffGroupList',
        component:()=>import('../components/MyPage/MyOffGroupList.vue'),
      }
    ]
  
  },
  {
    path:'/signup',
    name:'SignUpView',
    component:()=>import('../views/SignUpView.vue')
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
