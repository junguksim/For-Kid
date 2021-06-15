import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import User from "../views/User.vue";
import Post from "../views/Post.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/user",
    name: "user",
    component: User,
    children: [
      {
        path: "login",
        name: "user-login",
        component: () => import("@/components/user/Login.vue"),
      },
      {
        path: "signup",
        name: "user-signup",
        component: () => import("@/components/user/SignUp.vue"),
      },
      {
        path: "info",
        name: "user-info",
        component: () => import("@/components/user/Info.vue"),
      },
      {
        path: "modify",
        name: "user-modify",
        component: () => import("@/components/user/Modify.vue"),
      },
    ],
  },
  {
    path: "/post",
    name: "post",
    component: Post,
    // redirect: '/post/list',
    children: [
      {
        path: "regist",
        name: "post-regist",
        component: () => import("@/components/post/Regist.vue"),
      },
      {
        path: "list",
        name: "post-list",
        component: () => import("@/components/post/List.vue"),
      },
      {
        path: "detail/:no",
        name: "post-detail",
        component: () => import("@/components/post/Detail.vue"),
      },
      {
        path: "update/:no",
        name: "post-update",
        component: () => import("@/components/post/Update.vue"),
      },
    ],
  },
  {
    path: "/apartment",
    name: "apartment",
    component: () => import("@/components/apartment/Apartment.vue"),
    children: [
      {
        path: "list",
        name: "list",
        component: () => import("@/components/apartment/ApartmentList.vue"),
      },
    ],
  },
  {
    path: "/deal",
    name: "deal",
    component: () => import("@/components/apartment/ApartmentDeal.vue"),
    children: [
      {
        path: "list/:aptNo",
        name: "deal-list",
        component: () => import("@/components/apartment/ApartmentDealList.vue"),
      },
    ],
  },
  {
    path: "/school/:schoolCode",
    name: "school",
    component: () => import("@/components/school/School.vue"),
  },
  {
    path: "/edu-point",
    name: "edu-point",
    component: () => import("@/components/edu-point/EduPoint.vue"),
    children: [
      {
        path: "list",
        name: "edu-point-list",
        component: () => import("@/components/edu-point/EduPointList.vue"),
      },
      {
        path: "search",
        name: "edu-point-search",
        component: () => import("@/components/edu-point/EduPointSearch.vue"),
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
