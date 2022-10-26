<template>
  <div id="container">
    <table>
      <tr>
        <td>会员卡ID</td>
        <td>
          <input type="text" v-model="cid" @blur="ckCid" />
          <span>{{ errCid }}</span>
        </td>
      </tr>
      <tr>
        <td>消卡人</td>
        <td>
          <select v-model="userid">
            <option v-for="item in sells" :key="item.id" :value="item.id">{{
              item.username
            }}</option>
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
          <button @click="submit">消卡</button>
        </td>
      </tr>
    </table>
  </div>
</template>

<script>
import { getSellList, findCardById, addCardRecord } from "../../api";

export default {
  name: "AddUseRecord",
  mounted() {
    this.getSellList();
  },
  data() {
    return {
      errCid: "",
      cid: "",
      userid: "",
      memo: "",
      sells: []
    };
  },
  methods: {
    getSellList() {
      getSellList().then(res => {
        if (res.statusCode === 200) {
          this.sells = res.data;
          this.userid = res.data[0].id;
        } else {
          this.$bus.emit("error", res.message);
        }
      });
    },
    async ckCid() {
      if (this.cid == "") {
        this.errCid = "*请输入会员卡ID";
        return false;
      } else if (!(await this.getCid())) {
        this.errCid = "*会员卡不存在";
        return false;
      } else {
        this.errCid = "";
        return true;
      }
    },
    async getCid() {
      var res = await findCardById(this.cid);
      if (res.statusCode === 200) {
        return true;
      } else {
        return false;
      }
    },
    async submit() {
      if (await this.ckCid()) {
        addCardRecord({
          cid: this.cid,
          userid: this.userid,
          memo: this.memo
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
