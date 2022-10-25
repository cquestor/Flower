<template>
  <div id="container">
    <table>
      <tr>
        <td>原密码</td>
        <td>
          <input
            type="password"
            v-model="oldPasswd"
            placeholder="请输入原始密码"
            @blur="ckOldpasswd"
          />
          <span>{{ errOldpasswd }}</span>
        </td>
      </tr>
      <tr>
        <td>新密码</td>
        <td>
          <input
            type="password"
            v-model="newPasswd"
            placeholder="请输入新密码"
            @blur="ckNewpasswd"
          />
          <span>{{ errNewpasswd }}</span>
        </td>
      </tr>
      <tr>
        <td>确认密码</td>
        <td>
          <input
            type="password"
            v-model="rePasswd"
            placeholder="请再次输入新密码"
            @blur="ckRepasswd"
          />
          <span>{{ errRepasswd }}</span>
        </td>
      </tr>
      <tr>
        <td></td>
        <td>
          <button @click="submit">确认修改</button>
        </td>
      </tr>
    </table>
  </div>
</template>

<script>
import { modifyPwd } from "../../api";

export default {
  name: "ModifyPasswd",
  mounted() {
    this.userid = this.$route.params["userid"];
  },
  data() {
    return {
      userid: "",
      user: "",
      errOldpasswd: "",
      errNewpasswd: "",
      errRepasswd: "",
      oldPasswd: "",
      newPasswd: "",
      rePasswd: ""
    };
  },
  methods: {
    ckOldpasswd() {
      if (this.oldPasswd == "") {
        this.errOldpasswd = "*请输入原始密码";
        return false;
      } else {
        this.errOldpasswd = "";
        return true;
      }
    },
    ckNewpasswd() {
      if (this.newPasswd == "") {
        this.errNewpasswd = "*请输入新密码";
        return false;
      } else {
        this.errNewpasswd = "";
        return true;
      }
    },
    ckRepasswd() {
      if (this.rePasswd == "") {
        this.errRepasswd = "*请再次输入密码";
        return false;
      } else if (this.rePasswd != this.newPasswd) {
        this.errRepasswd = "*两次输入的密码不一致";
        return false;
      } else {
        this.errRepasswd = "";
        return true;
      }
    },
    submit() {
      if (this.ckOldpasswd() & this.ckNewpasswd() & this.ckRepasswd()) {
        console.log({
          id: this.userid,
          olduserpwd: this.oldPasswd,
          userpwd: this.newPasswd
        });
        modifyPwd({
          id: this.userid,
          olduserpwd: this.oldPasswd,
          userpwd: this.newPasswd
        }).then(res => {
          if (res.statusCode === 200) {
            this.$bus.emit("success", res.message);
          } else {
            this.$bus.emit("error", res.message);
          }
        });
      } else {
        return;
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
  flex-direction: column;
  justify-content: start;
  align-items: center;
  position: relative;
}
table {
  border-collapse: collapse;
  width: 100%;
  color: grey;
  font-size: 16px;
}
table td {
  border: 1px solid #00000050;
}
table tr {
  height: 35px;
}
table tr td:nth-child(1) {
  width: 100px;
  text-align: right;
  padding-right: 10px;
}
table tr td:nth-child(2) {
  padding-left: 10px;
}
table input {
  width: 300px;
  height: 30px;
  font-size: 16px;
  padding-left: 5px;
}
table button {
  width: 150px;
  height: 30px;
  border: none;
  cursor: pointer;
}
table button:nth-child(1) {
  color: white;
  background: #418cfd;
}
table button:nth-child(1):active {
  background: #418cfd90;
}
table button:nth-child(2):active {
  background: #00000010;
}
table input ~ span {
  margin-left: 5px;
  font-size: 14px;
  color: #f57851;
}
</style>
