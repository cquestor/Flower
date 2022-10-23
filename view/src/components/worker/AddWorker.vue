<template>
  <div id="container">
    <table class="info-table">
      <tr>
        <td>用户名</td>
        <td>
          <input
            type="text"
            placeholder="请输入用户名"
            v-model="username"
            @blur="ckUsername"
          />
          <span>{{ errUsername }}</span>
        </td>
      </tr>
      <tr>
        <td>密码</td>
        <td>
          <input
            type="password"
            placeholder="请输入密码"
            v-model="userpwd"
            @blur="ckUserpwd"
          />
          <span>{{ errUserpwd }}</span>
        </td>
      </tr>
      <tr>
        <td>确认密码</td>
        <td>
          <input
            type="password"
            placeholder="请再次输入密码"
            v-model="reuserpwd"
            @blur="ckReUserpwd"
          />
          <span>{{ errReUserpwd }}</span>
        </td>
      </tr>
      <tr>
        <td>性别</td>
        <td>
          <input type="radio" value="男" v-model="gender" />男
          <input type="radio" value="女" v-model="gender" />女
        </td>
      </tr>
      <tr>
        <td>用户类型</td>
        <td>
          <select name="usertype" v-model="usertype">
            <option value="1">前台</option>
            <option value="2">销售人员</option>
          </select>
        </td>
      </tr>
      <tr>
        <td>地址</td>
        <td>
          <input
            type="text"
            placeholder="请输入地址"
            v-model="address"
            @blur="ckAddress"
          />
          <span>{{ errAddress }}</span>
        </td>
      </tr>
      <tr>
        <td>电话</td>
        <td>
          <input
            type="text"
            placeholder="请输入电话"
            v-model="phone"
            @blur="ckPhone"
          />
          <span>{{ errPhone }}</span>
        </td>
      </tr>
      <tr>
        <td></td>
        <td>
          <button @click="add">保存</button>
          <button @click="pageBack">返回</button>
        </td>
      </tr>
    </table>
  </div>
</template>

<script>
import { addWorker } from "../../api";

export default {
  name: "AddWorker",
  data() {
    return {
      username: "",
      userpwd: "",
      reuserpwd: "",
      gender: "男",
      usertype: 1,
      address: "",
      phone: "",
      errUsername: "",
      errUserpwd: "",
      errReUserpwd: "",
      errAddress: "",
      errPhone: ""
    };
  },
  methods: {
    pageBack() {
      this.$router.back();
    },
    ckUsername() {
      if (this.username == "") {
        this.errUsername = "*请输入用户名";
        return false;
      } else {
        this.errUsername = "";
        return true;
      }
    },
    ckUserpwd() {
      if (this.userpwd == "") {
        this.errUserpwd = "*请输入密码";
        return false;
      } else {
        this.errUserpwd = "";
        return true;
      }
    },
    ckReUserpwd() {
      if (this.userpwd != this.reuserpwd) {
        this.errReUserpwd = "*两次输入的密码不一致";
        return false;
      } else {
        this.errReUserpwd = "";
        return true;
      }
    },
    ckAddress() {
      if (this.address == "") {
        this.errAddress = "*请输入地址";
        return false;
      } else {
        this.errAddress = "";
        return true;
      }
    },
    ckPhone() {
      if (this.phone == "") {
        this.errPhone = "*请输入电话";
        return false;
      } else {
        this.errPhone = "";
        return true;
      }
    },
    add() {
      if (
        this.ckUsername() &
        this.ckUserpwd() &
        this.ckReUserpwd() &
        this.ckAddress() &
        this.ckPhone()
      ) {
        addWorker({
          username: this.username,
          userpwd: this.userpwd,
          usertype: this.usertype,
          gender: this.gender,
          address: this.address,
          phone: this.phone
        }).then(res => {
          if (res.statusCode === 200) {
            this.$bus.emit("success", res.message);
            this.$router.back();
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
  position: relative;
  padding: 20px;
  display: flex;
  justify-content: center;
  align-items: flex-start;
}
.info-table {
  width: 100%;
  border-collapse: collapse;
  border: 1px solid #00000050;
  color: grey;
  font-size: 16px;
}

.info-table tr {
  height: 35px;
}
.info-table td {
  padding: 10px;
  border: 1px solid #00000050;
}
.info-table tr td:nth-child(1) {
  text-align: right;
  width: 100px;
}

.info-table input[type="text"],
.info-table input[type="password"],
.info-table select {
  width: 300px;
  height: 30px;
  font-size: 16px;
  padding-left: 5px;
}
.info-table button {
  width: 150px;
  height: 30px;
  border: none;
  cursor: pointer;
}
.info-table button:nth-child(1) {
  color: white;
  background: #418cfd;
}
.info-table button:nth-child(1):active {
  background: #418cfd90;
}
.info-table button:nth-child(2):active {
  background: #00000010;
}
.info-table input ~ span {
  margin-left: 5px;
  font-size: 14px;
  color: #f57851;
}
</style>
