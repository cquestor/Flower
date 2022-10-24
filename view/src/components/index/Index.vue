<template>
  <div id="container">
    <div id="leftPane">
      <div id="logo">
        <img src="../../assets/logo.png" />
        <h2>HalfSay</h2>
      </div>
      <div id="sidebar">
        <router-link
          class="sidebtn"
          to="/index/worker"
          exact-active-class="active"
          v-if="usertype === '0'"
        >
          <svg
            t="1666281159089"
            class="icon"
            viewBox="0 0 1024 1024"
            version="1.1"
            xmlns="http://www.w3.org/2000/svg"
            p-id="11358"
          >
            <path
              d="M644.8 581.568l160.64 187.456a64 64 0 0 1-48.597 105.643H267.157a64 64 0 0 1-48.597-105.643l160.661-187.435a253.813 253.813 0 0 0 61.206 26.944l-173.27 202.134h489.686l-173.27-202.134a254.613 254.613 0 0 0 61.227-26.965zM512 149.333c117.824 0 213.333 95.51 213.333 213.334S629.824 576 512 576s-213.333-95.51-213.333-213.333S394.176 149.333 512 149.333z m0 64A149.333 149.333 0 1 0 512 512a149.333 149.333 0 0 0 0-298.667z"
              p-id="11359"
            ></path>
          </svg>
          <span>员工管理</span>
        </router-link>
        <router-link
          class="sidebtn"
          to="/index/member"
          exact-active-class="active"
        >
          <svg
            t="1666281587775"
            class="icon"
            viewBox="0 0 1024 1024"
            version="1.1"
            xmlns="http://www.w3.org/2000/svg"
            p-id="16844"
            width="200"
            height="200"
          >
            <path
              d="M617.216 170.666667c114.24 0 206.869333 92.608 206.869333 206.869333 0 72.533333-37.333333 136.32-93.802666 173.269333l168.746666 196.885334A64 64 0 0 1 850.432 853.333333l-101.888 0.021334c11.221333-19.413333 14.293333-42.496 8.746667-64L850.432 789.333333 634.24 537.109333l60.992-39.872a142.869333 142.869333 0 0 0-75.584-262.549333 251.264 251.264 0 0 0-55.424-57.173333A206.976 206.976 0 0 1 617.216 170.666667z m-61.162667 412.757333l140.8 164.266667A64 64 0 0 1 648.213333 853.333333H181.824a64 64 0 0 1-48.597333-105.642666l140.8-164.266667c18.026667 12.373333 37.76 22.442667 58.773333 29.781333L181.824 789.333333h466.410667l-150.997334-176.128c21.034667-7.338667 40.768-17.386667 58.816-29.781333zM415.04 170.666667c114.24 0 206.869333 92.608 206.869333 206.869333 0 114.24-92.629333 206.869333-206.869333 206.869333-114.261333 0-206.869333-92.629333-206.869333-206.869333C208.170667 263.274667 300.778667 170.666667 415.04 170.666667z m0 64a142.869333 142.869333 0 1 0 0 285.738666 142.869333 142.869333 0 0 0 0-285.738666z"
              p-id="16845"
            ></path>
          </svg>
          <span>会员管理</span>
        </router-link>
      </div>
    </div>
    <div id="rightPane">
      <div id="topBar">
        <div id="searchBar">
          <!-- 搜索框 -->
          <div id="searchBox">
            <div id="searchicon">
              <svg
                viewBox="0 0 1024 1024"
                version="1.1"
                xmlns="http://www.w3.org/2000/svg"
                p-id="5875"
                width="20"
                height="20"
              >
                <path
                  d="M469.333 192c153.174 0 277.334 124.16 277.334 277.333 0 68.054-24.534 130.411-65.216 178.688L846.336 818.24l-48.341 49.877L630.4 695.125a276.053 276.053 0 0 1-161.067 51.542C316.16 746.667 192 622.507 192 469.333S316.16 192 469.333 192z m0 64C351.51 256 256 351.51 256 469.333s95.51 213.334 213.333 213.334 213.334-95.51 213.334-213.334S587.157 256 469.333 256z"
                  p-id="5876"
                ></path>
              </svg>
            </div>
            <input id="searchinput" type="text" placeholder="搜索" required />
            <button id="search">搜索</button>
          </div>

          <!-- 标题 -->
          <img id="title" src="../../assets/name.png" />

          <!-- 用户名|退出 -->
          <div id="userBar">
            <span>{{ username }}</span>
            <div id="cropLine"></div>
            <span id="quit" @click="quit">退出</span>
          </div>
        </div>
      </div>
      <div id="banner">
        首页&nbsp;>>&nbsp;员工管理
      </div>
      <div id="content">
        <router-view />
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Index",
  data() {
    return {
      username: localStorage.getItem("username"),
      usertype: localStorage.getItem("usertype")
    };
  },
  methods: {
    quit() {
      localStorage.removeItem("userid");
      localStorage.removeItem("username");
      localStorage.removeItem("usertype");
      localStorage.removeItem("access_halfsay");
      this.$bus.emit("success", "退出成功");
      this.$router.replace("/login");
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
  align-items: center;
}
#leftPane {
  width: 15%;
  height: 100%;
  min-width: 200px;
  padding: 0 20px;
  position: relative;
  box-shadow: 1px 0 5px #00000060;
  z-index: 1;
}
#leftPane #logo {
  width: 100%;
  height: 70px;
  border-bottom: 1px solid #00000050;
  display: flex;
  justify-content: start;
  align-items: center;
}
#leftPane #logo img {
  width: 30px;
  margin-right: 10px;
}
#leftPane #logo h2 {
  color: #418cfd;
}
#leftPane #sidebar {
  width: 100%;
  height: calc(100% - 70px);
  display: flex;
  flex-direction: column;
  justify-content: start;
  padding: 8px 0;
}
#leftPane #sidebar .sidebtn {
  margin: 8px 0;
  color: grey;
  font-size: 14px;
  padding: 5px;
  border-radius: 5px;
  display: flex;
  justify-content: start;
  align-items: center;
  cursor: pointer;
  text-decoration: none;
}
#leftPane #sidebar .sidebtn svg {
  margin-right: 2px;
  width: 25px;
  height: 25px;
}
#leftPane #sidebar .sidebtn svg path {
  fill: grey;
  stroke-width: 5;
  stroke: grey;
}
#leftPane #sidebar .active {
  color: #418cfd;
  background-color: #418cfd60;
}
#leftPane #sidebar .active svg path {
  fill: #418cfd;
}
#rightPane {
  width: 85%;
  height: 100%;
  position: relative;
}
#rightPane #topBar {
  height: 70px;
  width: 100%;
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 0 20px;
}
#rightPane #topBar #searchBar {
  width: 100%;
  height: 100%;
  border-bottom: 1px solid #00000050;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
#searchBox {
  width: 300px;
  height: 35px;
  background: #f4f6f8;
  border-radius: 10px;
  display: flex;
  justify-content: start;
  align-items: center;
}
#searchBox #searchicon {
  height: 35px;
  width: 35px;
  display: flex;
  justify-content: center;
  align-items: center;
}
#searchBox #searchicon svg path {
  fill: grey;
}
#searchBox #searchinput {
  height: 35px;
  width: 260px;
  border: none;
  background: transparent;
  font-size: 14px;
  transition: all 0.3s ease-in;
}
#searchBox #search {
  height: 25px;
  width: 0px;
  border-radius: 10px;
  border: none;
  background: #418cfd;
  color: white;
  font-size: 13px;
  white-space: nowrap;
  transition: all 0.3s ease-in;
  cursor: pointer;
}
#searchBox #search:active {
  background: #418cfd60;
}
#searchBox #searchinput:valid {
  width: 220px;
}
#searchBox #searchinput:valid ~ #search {
  width: 40px;
}
#topBar #userBar {
  display: flex;
  justify-content: center;
  align-items: center;
  color: #00000050;
  font-size: 14px;
}
#topBar #userBar #cropLine {
  height: 25px;
  width: 0.5px;
  background: #00000050;
  margin: 0 10px;
}
#quit {
  cursor: pointer;
}
#quit:hover {
  color: #f57851;
}
#quit:active {
  color: #f5785180;
}
#banner {
  height: 30px;
  padding: 0 20px;
  display: flex;
  justify-content: start;
  align-items: center;
  font-size: 14px;
  color: grey;
}
#content {
  width: 100%;
  height: calc(100% - 100px);
  background: #fafafa;
  padding: 0 20px 20px 20px;
}
#title {
  height: 30px;
  transform: translateX(-30%);
}
</style>
