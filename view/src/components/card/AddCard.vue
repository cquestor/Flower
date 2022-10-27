<template>
  <div id="container">
    <table>
      <tr>
        <td>会员卡ID:</td>
        <td>
          <input type="text" v-model="cardid" />
          <span></span>
        </td>
      </tr>
      <tr>
        <td>持卡人</td>
        <td>
          <select v-model="mid">
            <option v-for="item in members" :key="item.id" :value="item.id">{{
              item.name
            }}</option>
          </select>
        </td>
      </tr>
      <tr>
        <td>会员卡类型</td>
        <td>
          <select v-model="ctypeid">
            <option v-for="item in cardtypes" :key="item.id" :value="item.id">{{
              item.cardName
            }}</option>
          </select>
        </td>
      </tr>
      <tr>
        <td>卡状态</td>
        <td>
          <select v-model="cstate">
            <option value="0">未使用</option>
            <option value="1">正常</option>
            <option value="2">过期</option>
            <option value="3">禁用</option>
          </select>
        </td>
      </tr>
      <tr>
        <td>备注</td>
        <td><textarea cols="38" rows="5" v-model="memo"></textarea></td>
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
import { addCard, getAllMembers, getCardTypeList } from "../../api";

export default {
  name: "AddCard",
  data() {
    return {
      members: [],
      cardtypes: [],
      cardid: new Date().getTime(),
      mid: "",
      ctypeid: "",
      userid: localStorage.getItem("userid"),
      cstate: 0,
      memo: ""
    };
  },
  mounted() {
    this.listMembers();
    this.listCardTypes();
  },
  methods: {
    listMembers() {
      getAllMembers().then(res => {
        if (res.statusCode === 200) {
          this.members = res.data;
          this.mid = res.data[0].id;
        } else {
          this.$bus.emit("error", res.message);
        }
      });
    },
    listCardTypes() {
      getCardTypeList().then(res => {
        if (res.statusCode === 200) {
          this.cardtypes = res.data;
          this.ctypeid = res.data[0].id;
        } else {
          this.$bus.emit("error", res.message);
        }
      });
    },
    pageBack() {
      this.$router.back();
    },
    submit() {
      addCard({
        id: this.cardid,
        mid: this.mid,
        ctypeid: this.ctypeid,
        userid: this.userid,
        cstate: this.cstate,
        memo: this.memo
      }).then(res => {
        if (res.statusCode === 200) {
          this.$bus.emit("success", "会员卡添加成功");
        } else {
          this.$bus.emit("error", "会员卡添加失败");
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
table input,
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
