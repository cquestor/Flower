<template>
  <div id="container">
    <table>
      <tr>
        <td>充值卡名称</td>
        <td>
          <input
            type="text"
            v-model="cardName"
            placeholder="请输入充值卡名称"
            @blur="ckCardName"
          />
          <span>{{ errCardName }}</span>
        </td>
      </tr>
      <tr>
        <td>卡价格(元)</td>
        <td>
          <input
            type="number"
            v-model="cardPrice"
            placeholder="请输入充值卡价格"
            @blur="ckCardPrice"
          />
          <span>{{ errCardPrice }}</span>
        </td>
      </tr>
      <tr>
        <td>到期天数</td>
        <td>
          <input
            type="number"
            v-model="cardExpireDay"
            placeholder="请输入到期天数"
            @blur="ckCardExpireDay"
          />
          <span>{{ errCardExpireDay }}</span>
        </td>
      </tr>
      <tr>
        <td>卡可见性</td>
        <td>
          <input type="radio" value="1" v-model="cardSee" />可见
          <input type="radio" value="0" v-model="cardSee" />不可见
        </td>
      </tr>
      <tr>
        <td>充值卡权重</td>
        <td>
          <select v-model="cardRank">
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
            <option value="6">6</option>
          </select>
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
import { addCardType, ckCardTypeName } from "../../api";

export default {
  name: "AddCardType",
  data() {
    return {
      errCardName: "",
      errCardPrice: "",
      errCardExpireDay: "",
      cardName: "",
      cardPrice: "",
      cardExpireDay: "",
      cardSee: 1,
      cardRank: 1
    };
  },
  methods: {
    async ckCardName() {
      if (this.cardName == "") {
        this.errCardName = "*请输入充值卡名称";
        return false;
      } else if (!(await this.ckCardNameExist())) {
        this.errCardName = "*该名称已存在";
        return false;
      } else {
        this.errCardName = "";
        return true;
      }
    },
    ckCardPrice() {
      if (this.cardPrice == "") {
        this.errCardPrice = "请输入充值卡价格";
        return false;
      } else {
        this.errCardPrice = "";
        return true;
      }
    },
    ckCardExpireDay() {
      if (this.cardExpireDay == "") {
        this.errCardExpireDay = "请输入到期时间";
        return false;
      } else {
        this.errCardExpireDay = "";
        return true;
      }
    },
    pageBack() {
      this.$router.back();
    },
    async ckCardNameExist() {
      var res = await ckCardTypeName(this.cardName);
      if (res.statusCode === 404) {
        return true;
      } else {
        return false;
      }
    },
    async submit() {
      console.log(1);
      if (
        (await this.ckCardName()) &
        this.ckCardPrice() &
        this.ckCardExpireDay()
      ) {
        addCardType({
          cardName: this.cardName,
          cardPrice: this.cardPrice,
          cardExpireDay: this.cardExpireDay,
          cardSee: this.cardSee,
          cardRank: this.cardRank
        }).then(res => {
          if (res.statusCode == 200) {
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
table input[type="text"],
table input[type="number"],
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
