import Vue from "vue";
import Router from "vue-router";
import Login from "../components/login/Login";
import Index from "../components/index/Index";
import Worker from "../components/worker/Worker";
import Member from "../components/member/Member";
import AddWorker from "../components/worker/AddWorker";
import UpdateWorker from "../components/worker/UpdateWorker";
import AddMember from "../components/member/AddMember";
import UpdateMember from "../components/member/UpdateMember";
import ModifyPasswd from "../components/modifypasswd/ModifyPasswd";

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
      meta: {
        requireAuth: true
      },
      children: [
        {
          path: "worker",
          name: "worker",
          component: Worker,
          meta: {
            requireAuth: true,
            needAdmin: true
          }
        },
        {
          path: "member",
          name: "member",
          component: Member,
          meta: {
            requireAuth: true
          }
        },
        {
          path: "addworker",
          name: "addworker",
          component: AddWorker,
          meta: {
            requireAuth: true,
            needAdmin: true
          }
        },
        {
          path: "updateworker",
          name: "updateworker",
          component: UpdateWorker,
          meta: {
            requireAuth: true,
            needAdmin: true
          }
        },
        {
          path: "addmember",
          name: "addmember",
          component: AddMember,
          meta: {
            requireAuth: true
          }
        },
        {
          path: "updatemember",
          name: "updatemember",
          component: UpdateMember,
          meta: {
            requireAuth: true
          }
        },
        {
          path: "modifypasswd",
          name: "modifypasswd",
          component: ModifyPasswd,
          meta: {
            requireAuth: true
          }
        }
      ]
    }
  ]
});
