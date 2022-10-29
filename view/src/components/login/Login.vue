<template>
  <div id="container">
    <div class="logindiv">
      <div id="title">铛铛鲜花店会员管理系统</div>
      <div>
        <label>账号: </label>
        <input
          type="text"
          name="username"
          v-model="username"
          autocomplete="off"
          @blur="ckUsername"
        />
      </div>
      <div>{{ errUsername }}</div>
      <div>
        <label>密码: </label>
        <input type="password" name="userpwd" v-model="userpwd" @blur="ckPwd" />
      </div>
      <div>{{ errPwd }}</div>
      <button @click="login">登录</button>
    </div>
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
      userpwd: "",
      errUsername: "",
      errPwd: ""
    };
  },
  methods: {
    login() {
      if (this.ckUsername() & this.ckPwd()) {
        login({ username: this.username, userpwd: this.userpwd }).then(res => {
          if (res.statusCode !== 200) {
            this.$bus.emit("error", res.message);
          } else {
            localStorage.setItem("userid", res.data.userid);
            localStorage.setItem("username", res.data.username);
            localStorage.setItem("usertype", res.data.usertype);
            localStorage.setItem("access_halfsay", res.data.jwtToken);
            this.$bus.emit("success", res.message);
            if (res.data.usertype === "0") {
              this.$router.replace("/index/worker");
            } else {
              this.$router.replace("/index/member");
            }
          }
        });
      } else {
        return;
      }
    },
    ckUsername() {
      if (this.username == "") {
        this.errUsername = "*账号不能为空";
        return false;
      } else {
        this.errUsername = "";
        return true;
      }
    },
    ckPwd() {
      if (this.userpwd == "") {
        this.errPwd = "*账号不能为空";
        return false;
      } else {
        this.errPwd = "";
        return true;
      }
    }
  }
};
</script>

<style scoped>
#container {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  background: url(../../assets/bg.png) no-repeat;
  background-size: 100% 100%;
}
.logindiv {
  width: 40%;
  height: 30%;
  min-width: 500px;
  min-height: 300px;
  border: 2px solid #8cb7d7;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  border-radius: 30px;
  position: relative;
}
.logindiv #title {
  position: absolute;
  top: 0;
  margin-top: 5%;
  font-size: 26px;
  font-weight: bold;
  letter-spacing: 5px;
}
.logindiv > div:nth-child(2),
.logindiv > div:nth-child(4) {
  margin-top: 10px;
}
.logindiv > div:nth-child(3),
.logindiv > div:nth-child(5) {
  width: 250px;
  text-align: left;
  font-size: 14px;
  color: red;
}
.logindiv input {
  width: 300px;
}
.logindiv > button {
  width: 200px;
  position: absolute;
  bottom: 0;
  margin-bottom: 10%;
}
</style>
