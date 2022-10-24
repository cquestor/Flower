<template>
  <div id="container">
    <table>
      <tr>
        <td>会员名称</td>
        <td>
          <input
            type="text"
            v-model="name"
            @blur="ckName"
            placeholder="请输入会员名称"
          /><span>{{ errName }}</span>
        </td>
      </tr>
      <tr>
        <td>年龄</td>
        <td>
          <input
            type="text"
            v-model="age"
            @blur="ckAge"
            @input="parseAge"
            placeholder="请输入年龄"
          /><span>{{ errAge }}</span>
        </td>
      </tr>
      <tr>
        <td>性别</td>
        <td>
          <input type="radio" value="男" v-model="sex" />男
          <input type="radio" value="女" v-model="sex" />女
        </td>
      </tr>
      <tr>
        <td>联系方式</td>
        <td>
          <input
            type="text"
            v-model="phone"
            @blur="ckPhone"
            placeholder="请输入联系方式"
          /><span>{{ errPhone }}</span>
        </td>
      </tr>
      <tr>
        <td>住址</td>
        <td>
          <input
            type="text"
            v-model="address"
            @blur="ckAddress"
            placeholder="请输入住址"
          /><span>{{ errAddress }}</span>
        </td>
      </tr>
      <tr>
        <td>偏好</td>
        <td>
          <input
            type="text"
            v-model="favour"
            @blur="ckFavour"
            placeholder="请输入偏好"
          /><span>{{ errFavour }}</span>
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
import { addMember } from "../../api";

export default {
  name: "AddMember",
  data() {
    return {
      name: "",
      age: "",
      sex: "男",
      phone: "",
      address: "",
      favour: "",
      errName: "",
      errAge: "",
      errPhone: "",
      errAddress: "",
      errFavour: ""
    };
  },
  methods: {
    parseAge() {
      if (parseInt(this.age).toString() != "NaN") {
        this.age = parseInt(this.age).toString();
      }
    },
    ckName() {
      if (this.name == "") {
        this.errName = "*请输入会员名称";
        return false;
      } else {
        this.errName = "";
        return true;
      }
    },
    ckAge() {
      if (this.age == "") {
        this.errAge = "*请输入年龄";
        return false;
      } else if (parseInt(this.age).toString() == "NaN") {
        this.errAge = "*请输入有效数字";
        return false;
      } else {
        this.errAge = "";
        return true;
      }
    },
    ckPhone() {
      if (this.phone == "") {
        this.errPhone = "*请输入联系方式";
        return false;
      } else {
        this.errPhone = "";
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
    ckFavour() {
      if (this.favour == "") {
        this.errFavour = "*请输入偏好";
        return false;
      } else {
        this.errFavour = "";
        return true;
      }
    },
    pageBack() {
      this.$router.back();
    },
    submit() {
      if (
        this.ckName() &
        this.ckAge() &
        this.ckPhone() &
        this.ckAddress() &
        this.ckFavour()
      ) {
        addMember({
          name: this.name,
          age: this.age,
          sex: this.sex,
          phone: this.phone,
          address: this.address,
          favour: this.favour,
          userid: localStorage.getItem("userid")
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
