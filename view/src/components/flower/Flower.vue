<template>
  <div id="container">
    <div id="content">
      <div v-for="item in flowers" :key="item.id" class="flowercard">
        <div class="one">
          <div class="flowerimg">
            <img :src="item.imgurl" />
          </div>
          <div class="info">
            花名：{{item.name}}<br/><br/>
            适合对象：{{item.target}}<br/><br/>
            库存：50
          </div>
        </div>
        <div class="two">{{item.flanguage}}</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Flower",
  data() {
    return {
      flowers: []
    };
  },
  mounted() {
    this.flowers = this.$route.params["flowers"];
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
#content {
  width: 100%;
  height: 100%;
  overflow: scroll;
  overflow-x: hidden;
}
.flowercard {
  width: 95%;
  height: 200px;
  margin: 10px;
  border: 2px solid #418cfd;
  border-radius: 10px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.flowercard .one {
  width: 100%;
  height: 70%;
  border-bottom: 2px solid #418cfd;
  position: relative;
  display: flex;
  justify-content: start;
  align-items: flex-start;
}
.flowerimg {
  width: 20%;
  min-width: 200px;
  height: 100%;
  border-right: 2px solid #418cfd;
  padding: 5px;
}
.flowerimg img {
  width: 100%;
  height: 100%;
}
.info {
    padding: 5px;
}
.flowercard .two {
  width: 100%;
  height: 30%;
  font-size: 14px;
  color: #418cfd;
  padding: 5px;
}
</style>
