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
import AddUseRecord from "../components/record/AddUseRecord";
import CardType from "../components/cardtype/CardType";
import AddCardType from "../components/cardtype/AddCardType";
import UpdateCardType from "../components/cardtype/UpdateCardType";
import Card from "../components/card/Card";
import AddCard from "../components/card/AddCard";
import UpdateCard from "../components/card/UpdateCard";
import UseCard from "../components/usecard/UseCard";

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
        },
        {
          path: "adduserecord",
          name: "adduserecord",
          component: AddUseRecord,
          meta: {
            requireAuth: true
          }
        },
        {
          path: "cardtype",
          name: "cardtype",
          component: CardType,
          meta: {
            requireAuth: true
          }
        },
        {
          path: "addcardtype",
          name: "addcardtype",
          component: AddCardType,
          meta: {
            requireAuth: true
          }
        },
        {
          path: "updatecardtype",
          name: "updatecardtype",
          component: UpdateCardType,
          meta: {
            requireAuth: true
          }
        },
        {
          path: "card",
          name: "card",
          component: Card,
          meta: {
            requireAuth: true
          }
        },
        {
          path: "addcard",
          name: "addcard",
          component: AddCard,
          meta: {
            requireAuth: true
          }
        },
        {
          path: "updatecard",
          name: "updatecard",
          component: UpdateCard,
          meta: {
            requireAuth: true
          }
        },
        {
          path: "usecard",
          name: "usecard",
          component: UseCard,
          meta: {
            requireAuth: true
          }
        }
      ]
    }
  ]
});
