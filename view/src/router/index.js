import Vue from "vue";
import Router from "vue-router";
import Login from "../components/login/Login";
import Index from "../components/index/Index";
import Worker from "../components/worker/Worker"
import Member from "../components/member/Member"

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: "/",
      redirect: "/login"
    },
    {
      path: "/login",
      name: "login",
      component: Login
    },
    {
      path: "/index",
      name: "index",
      component: Index,
      children: [
        {
          path: "worker",
          name: "worker",
          component: Worker
        },
        {
          path: "member",
          name: "member",
          component: Member
        }
      ]
    }
  ]
});
