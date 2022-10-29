<template>
  <div id="container">
    <!-- 搜索区 -->
    <div id="searchBar">
      <span>请输入会员卡ID:</span>
      <input
        type="text"
        placeholder="请输入会员卡ID"
        v-model="searchName"
        @input="cksearch"
      />
      <button @click="search">查询</button>
    </div>

    <!-- 新增|删除 按钮 -->
    <div id="btnBar">
      <span class="btn" @click="addCard"> </span>
      <span class="btn">
        <svg
          t="1666345290655"
          class="icon"
          viewBox="0 0 1024 1024"
          version="1.1"
          xmlns="http://www.w3.org/2000/svg"
          p-id="6993"
          width="20"
          height="20"
        >
          <path
            d="M341.013333 394.666667l27.754667 393.450666h271.829333l27.733334-393.450666h64.106666L704.426667 792.618667a64 64 0 0 1-63.829334 59.498666H368.768a64 64 0 0 1-63.829333-59.52L276.885333 394.666667h64.128z m139.306667 19.818666v298.666667h-64v-298.666667h64z m117.013333 0v298.666667h-64v-298.666667h64zM181.333333 288h640v64h-640v-64z m453.482667-106.666667v64h-256v-64h256z"
            p-id="6994"
            fill="#eeeeee"
          ></path>
        </svg>
        <span @click="deleteList">批量删除</span>
      </span>
    </div>

    <!-- 结果表格  -->
    <div id="content">
      <div
        id="preBtn"
        class="pageBtn"
        :style="records.hasPreviousPage ? '' : 'visibility: hidden;'"
        @click="prePage"
      >
        <svg
          t="1666345992968"
          class="icon"
          viewBox="0 0 1024 1024"
          version="1.1"
          xmlns="http://www.w3.org/2000/svg"
          p-id="7715"
          width="128"
          height="128"
        >
          <path
            d="M641.28 278.613333l-45.226667-45.226666-278.634666 278.762666 278.613333 278.485334 45.248-45.269334-233.365333-233.237333z"
            p-id="7716"
            fill="#ffffff"
          ></path>
        </svg>
      </div>
      <div id="result">
        <table>
          <thead>
            <tr>
              <th>
                <input type="checkbox" @click="ckAll" id="allCheckBox" />
              </th>
              <th>会员卡号</th>
              <th>持卡人</th>
              <th>消卡人</th>
              <th>消卡时间</th>
              <th>备注</th>
            </tr>
          </thead>

          <tbody>
            <tr v-for="item in records.list" :key="item.id">
              <td>
                <input
                  type="checkbox"
                  ref="ckbox"
                  :value="item.id"
                  @click="ckSelect"
                />
              </td>
              <td>{{ item.id }}</td>
              <td>{{ item.mname }}</td>
              <td>{{ item.username }}</td>
              <td>{{ item.usedate }}</td>
              <td>{{ item.memo }}</td>
            </tr>
          </tbody>
        </table>

        <!-- 页数指示 -->
        <div id="pageCount">
          <ul>
            <li
              v-for="item in records.navigatepageNums"
              :key="item"
              :class="item == records.pageNum ? 'li-active' : ''"
              @click="load(item)"
            ></li>
          </ul>
        </div>
      </div>

      <!-- 下一页 -->
      <div
        id="sufBtn"
        class="pageBtn"
        :style="records.hasNextPage ? '' : 'visibility: hidden;'"
        @click="nextPage"
      >
        <svg
          viewBox="0 0 1024 1024"
          version="1.1"
          xmlns="http://www.w3.org/2000/svg"
          p-id="7332"
          width="128"
          height="128"
        >
          <path
            d="M593.450667 512.128L360.064 278.613333l45.290667-45.226666 278.613333 278.762666L405.333333 790.613333l-45.226666-45.269333z"
            p-id="7333"
            fill="#ffffff"
          ></path>
        </svg>
      </div>
    </div>
  </div>
</template>

<script>
import { getRecords, deleteRecords } from "../../api";

export default {
  name: "UseCard",
  data() {
    return {
      records: "",
      ids: [],
      searchName: ""
    };
  },
  methods: {
    load(pageIndex) {
      getRecords({
        cid: this.searchName
      }).then(res => {
        console.log(res);
        if (res.statusCode === 200) {
          this.records = res.data;
        } else {
          this.records = "";
        }
      });
    },
    addCard() {
      this.$router.push("/index/addcard");
    },
    deleteList() {
      if (this.ids.length > 0) {
        const confirm = window.confirm("确定要删除吗？");
        if (!confirm) return;
        deleteRecords({ ids: this.ids }).then(res => {
          if (res.statusCode === 200) {
            document.getElementById("allCheckBox").checked = false;
            this.$bus.emit("success", res.message);
            this.load(this.records.pageNum);
          } else {
            this.$bus.emit("error", res.message);
          }
        });
      }
    },
    prePage() {
      this.load(this.records.prePage);
    },
    ckAll(e) {
      this.ids = [];
      const ckboxs = this.$refs["ckbox"];
      ckboxs.forEach(element => {
        element.checked = e.target.checked;
      });
      if (e.target.checked) {
        this.records.list.forEach(item => {
          this.ids.push(item.id);
        });
      }
    },
    ckSelect(e) {
      let id = e.target._value;
      if (this.ids.indexOf(id) == -1) {
        this.ids.push(id);
      } else {
        this.ids.splice(this.ids.indexOf(id), 1);
      }
    },
    updateOne(model) {
      this.$router.push({ name: "updatecard", params: { card: model } });
    },
    delOne(target) {
      const confirm = window.confirm("确定要删除吗？");
      if (!confirm) return;
      deleteRecords({ ids: [target] }).then(res => {
        if (res.statusCode === 200) {
          this.$bus.emit("success", res.message);
          this.load(this.records.pageNum);
        } else {
          this.$bus.emit("error", res.message);
        }
      });
    },
    nextPage() {
      this.load(this.member.nextPage);
    },
    search() {
      getRecords({
        cid: this.searchName
      }).then(res => {
        if (res.statusCode === 200) {
          this.records = res.data;
          this.$bus.emit("success", res.message);
        } else {
          this.$bus.emit("error", res.message);
        }
      });
    },
    cksearch() {
      if (this.searchName == "") {
        this.load(1);
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
#searchBar {
  width: 100%;
  display: flex;
  justify-content: start;
  align-items: center;
  position: relative;
  padding-left: 60px;
  font-size: 16px;
  color: grey;
}
#searchBar span {
  margin-right: 10px;
}
#searchBar input {
  width: 200px;
  height: 30px;
  border: 1px solid grey;
  border-radius: 8px;
  padding-left: 10px;
}
#searchBar button {
  width: 80px;
  height: 30px;
  margin-left: 10px;
  border: none;
  background: #418cfd;
  color: white;
  border-radius: 5px;
  cursor: pointer;
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
#btnBar .btn:nth-child(1):hover {
  color: #418cfd;
}
#btnBar .btn:nth-child(1):hover svg path {
  fill: #418cfd;
}
#btnBar .btn:nth-child(2):hover {
  color: #f57851;
}
#btnBar .btn:nth-child(2):hover svg path {
  fill: #f57851;
}
#content {
  width: 100%;
  height: calc(100% - 70px);
  display: flex;
  justify-content: center;
  align-items: center;
  position: relative;
}
#preBtn {
  float: left;
  height: 50%;
  width: 50px;
}
#sufBtn {
  float: right;
  height: 50%;
  width: 50px;
}
#result {
  height: 100%;
  width: calc(100% - 100px);
  padding: 10px;
  position: relative;
}
.pageBtn {
  border-radius: 10px;
  background-color: #418cfd50;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
  transform: translateY(-10%);
}
.pageBtn:active {
  background-color: #418cfd;
}
#result table {
  width: 100%;
  border-collapse: collapse;
}
#result table th {
  height: 35px;
  background: #418cfd;
  color: white;
  border: 2px solid #418cfd;
}
#result table td {
  text-align: center;
  height: 35px;
  border: 2px solid #418cfd;
  background: white;
}
.actions span {
  cursor: pointer;
}
#pageCount {
  width: calc(100% - 20px);
  height: 30px;
  position: absolute;
  bottom: 60px;
  display: flex;
  justify-content: center;
  align-items: center;
}
#pageCount ul {
  display: flex;
  justify-content: center;
  align-items: center;
}
#pageCount ul li {
  display: block;
  width: 10px;
  height: 10px;
  background: #418cfd50;
  border-radius: 50%;
  margin: 0 5px;
  cursor: pointer;
}
.li-active {
  background: #418cfd !important;
}
#updateBtn {
  margin-right: 10px;
}
#updateBtn:hover {
  color: #8abf8a;
}
#deleteBtn:hover {
  color: #f77751;
}
</style>
