// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from "vue";
import App from "./App";
import router from "./router";
import EventBus from "@/utils/bus";

Vue.config.productionTip = false;

Vue.use(EventBus);

router.beforeEach((to, from, next) => {
  if (to.meta.requireAuth) {
    if (localStorage.getItem("access_cquestor")) {
      next();
    } else {
      next({
        path: "/login",
        query: {
          redirect: to.fullPath
        }
      });
    }
  } else {
    next();
  }
  if (to.fullPath == "/login") {
    if (localStorage.getItem("access_cquestor")) {
      next({
        path: from.fullPath
      });
    } else {
      next();
    }
  }
  if (to.meta.needAdmin) {
    if (localStorage.getItem("usertype") === "0") {
      next();
    } else {
      next({ path: from.fullPath });
    }
  }
});

/* eslint-disable no-new */
new Vue({
  el: "#app",
  router,
  components: { App },
  template: "<App/>"
});
