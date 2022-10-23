<template>
  <div id="container">
    <table>
      <tr>
        <td>用户名</td>
        <td><input type="text" v-model="user.username" /></td>
      </tr>
      <tr>
        <td>性别</td>
        <td>
          <input type="radio" value="男" v-model="user.gender" /><span>男</span>
          <input type="radio" value="女" v-model="user.gender" /><span>女</span>
        </td>
      </tr>
      <tr>
        <td>用户类型</td>
        <td>
          <select name="usertype" v-model="user.usertype">
            <option value="1">前台</option>
            <option value="2">销售人员</option>
          </select>
        </td>
      </tr>
      <tr>
        <td>地址</td>
        <td>
          <input v-model="user.address" type="text" />
        </td>
      </tr>
      <tr>
        <td>电话</td>
        <td>
          <input v-model="user.phone" type="text" />
        </td>
      </tr>
      <tr>
        <td></td>
        <td>
          <button @click="updateOne">保存</button>
          <button @click="pageBack">返回</button>
        </td>
      </tr>
    </table>
  </div>
</template>

<script>
import { updateWorker } from "../../api";

export default {
  name: "UpdateWorker",
  mounted() {
    this.user = this.$route.params["user"];
  },
  data() {
    return {
      user: ""
    };
  },
  methods: {
    pageBack() {
      this.$router.back();
    },
    updateOne() {
      updateWorker(this.user).then(res => {
        if (res.statusCode === 200) {
          this.$bus.emit("success", res.message);
          this.$router.back();
        } else {
          this.$bus.emit("error", res.message);
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
  padding: 20px;
  display: flex;
  justify-content: center;
  align-items: flex-start;
}
table {
  border-collapse: collapse;
  width: 100%;
  color: grey;
  font-size: 16px;
}
table td {
  border: 1px solid #00000050;
  padding: 10px;
}
table tr td:nth-child(1) {
  text-align: right;
  padding-right: 10px;
  width: 100px;
}
table tr td:nth-child(2) {
  text-align: left;
  padding-left: 10px;
}
table input[type="text"],
table input[type="password"],
table select {
  width: 300px;
  height: 30px;
  font-size: 16px;
  padding-left: 5px;
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
table input[type="radio"] {
  cursor: pointer;
}
table input ~ span {
  margin-left: 5px;
  font-size: 14px;
}
table button {
  width: 150px;
  height: 30px;
  border: none;
  cursor: pointer;
}
</style>
