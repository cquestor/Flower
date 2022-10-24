<template>
  <div id="container">
    <table>
      <tr>
        <td>会员名称</td>
        <td>
          <input type="text" v-model="user.name" placeholder="请输入会员名称" />
        </td>
      </tr>
      <tr>
        <td>年龄</td>
        <td>
          <input type="text" v-model="user.age" placeholder="请输入年龄" />
        </td>
      </tr>
      <tr>
        <td>性别</td>
        <td>
          <input type="radio" value="男" v-model="user.sex" />男
          <input type="radio" value="女" v-model="user.sex" />女
        </td>
      </tr>
      <tr>
        <td>联系方式</td>
        <td>
          <input
            type="text"
            v-model="user.phone"
            placeholder="请输入联系方式"
          />
        </td>
      </tr>
      <tr>
        <td>住址</td>
        <td>
          <input type="text" v-model="user.address" placeholder="请输入住址" />
        </td>
      </tr>
      <tr>
        <td>偏好</td>
        <td>
          <input type="text" v-model="user.favour" placeholder="请输入偏好" />
        </td>
      </tr>
      <tr>
        <td></td>
        <td>
          <button @click="submit">保存</button>
          <button @click="pageBack">返回</button>
        </td>
      </tr>
    </table>
  </div>
</template>

<script>
import { updateMember } from "../../api";

export default {
  name: "UpdateMember",
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
    submit() {
      updateMember(this.user).then(res => {
        if (res.statusCode === 200) {
          this.$bus.emit("success", res.message);
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
table input[type="text"],
table input[type="password"],
table select {
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
