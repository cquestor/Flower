<template>
  <div id="container">
    <input type="text" name="username" v-model="username" /><br />
    <input type="password" name="userpwd" v-model="userpwd" /><br />
    <button @click="login">登录</button>
  </div>
</template>

<script>
import { login } from "../../api";

export default {
  name: "Login",
  mounted() {
    localStorage.removeItem("userid");
    localStorage.removeItem("username");
    localStorage.removeItem("usertype");
    localStorage.removeItem("access_halfsay");
  },
  data() {
    return {
      username: "",
      userpwd: ""
    };
  },
  methods: {
    login() {
      login({ username: this.username, userpwd: this.userpwd }).then(res => {
        if (res.statusCode !== 200) {
          this.$bus.emit("error", res.message);
        } else {
          localStorage.setItem("userid", res.data.userid);
          localStorage.setItem("username", res.data.username);
          localStorage.setItem("usertype", res.data.usertype);
          localStorage.setItem("access_cquestor", res.data.jwtToken);
          this.$bus.emit("success", res.message);
          if (res.data.usertype === "0") {
            this.$router.replace("/index/worker");
          } else {
            this.$router.replace("/index/member");
          }
        }
      });
    }
  }
};
</script>

<style scoped>
#container {
  width: 100%;
  height: 100%;
}
</style>
