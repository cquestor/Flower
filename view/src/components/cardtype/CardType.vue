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
  </div>
</template>

<script>
export default {
  name: "CardType",
  methods: {
    toAddPage() {
      this.$router.push("/index/addcardtype");
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
</style>
