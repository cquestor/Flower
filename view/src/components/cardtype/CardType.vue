<template>
  <div id="container">
    <div id="btnBar">
      <span class="btn" @click="toAddPage">
        <svg
          viewBox="0 0 1024 1024"
          version="1.1"
          xmlns="http://www.w3.org/2000/svg"
          p-id="6247"
          width="20"
          height="20"
        >
          <path
            d="M544 213.333333v266.666667H810.666667v64H544V810.666667h-64V544H213.333333v-64h266.666667V213.333333z"
            p-id="6248"
            fill="#eeeeee"
          ></path>
        </svg>
        <span>新增会员卡</span>
      </span>
    </div>
    <div id="content">
      <div class="card" v-for="item in cards" :key="item.id">
        <div class="mainPane">
          <div>{{ item.cardName }}会员卡</div>
          <div>价值{{ item.cardPrice }}元</div>
        </div>
        <div class="bottomPane">
          <span>充值卡到期天数: {{ item.cardExpireDay }}天</span>
          <span>
            <span @click="updateOne(item)">修改</span>
            <span @click="deleteOne(item.id)">删除</span>
          </span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { getCardTypeList, deleteCardType } from "../../api";

export default {
  name: "CardType",
  mounted() {
    this.load();
  },
  data() {
    return {
      cards: []
    };
  },
  methods: {
    load() {
      getCardTypeList().then(res => {
        if (res.statusCode === 200) {
          this.cards = res.data;
        } else {
          this.$bus.emit(res.message);
        }
      });
    },
    toAddPage() {
      this.$router.push("/index/addcardtype");
    },
    deleteOne(target) {
      const confirm = window.confirm("确定要删除吗？");
      if (!confirm) return;
      deleteCardType(target).then(res => {
        if (res.statusCode === 200) {
          this.$bus.emit("success", res.message);
          this.load();
        } else {
          this.$bus.emit("error", res.message);
        }
      });
    },
    updateOne(target) {
      this.$router.push({ name: "updatecardtype", params: { card: target } });
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
#btnBar {
  width: 100%;
  height: 40px;
  display: flex;
  justify-content: start;
  align-items: center;
  font-size: 14px;
  color: grey;
  padding-left: 60px;
}
#btnBar .btn {
  margin-right: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
}
#btnBar .btn svg path {
  fill: grey;
}
#btnBar .btn:hover {
  color: #418cfd;
}
#btnBar .btn:hover svg path {
  fill: #418cfd;
}
#content {
  width: 100%;
  height: calc(100% - 40px);
  display: flex;
  justify-content: start;
  align-items: flex-start;
  position: relative;
  flex-wrap: wrap;
  overflow: scroll;
  overflow-x: hidden;
}
.card {
  width: 380px;
  height: 200px;
  margin: 10px;
  border: 2px solid #418cfd;
  border-radius: 10px;
  position: relative;
}
.card:hover {
  box-shadow: 5px 5px 10px #00000050;
}
.card .mainPane {
  width: 100%;
  height: calc(100% - 40px);
  position: absolute;
  padding: 20px;
}
.card .mainPane > div:nth-child(1) {
  font-size: 20px;
  font-weight: bold;
}
.card .mainPane > div:nth-child(2) {
  position: absolute;
  right: 20px;
  top: 50%;
  transform: translateY(-50%);
}
.card .bottomPane {
  width: 100%;
  height: 40px;
  background: #418cfd;
  position: absolute;
  bottom: 0;
  border-radius: 0 0 10px 10px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 10px;
}
.card .bottomPane > span:nth-child(1) {
  color: white;
}
.card .bottomPane > span:nth-child(2) {
  color: black;
}
.card .bottomPane > span:nth-child(2) > span {
  cursor: pointer;
}
</style>
