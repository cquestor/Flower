<template>
  <div id="app">
    <router-view />
    <div id="messageBox">
      {{message}}
    </div>
  </div>
</template>

<script>
export default {
  name: 'App',
  mounted() {
    this.$bus.on("success", this.successMessage);
    this.$bus.on("error", this.errorMessage);
  },
  data() {
    return {
      message: ""
    }
  },
  methods: {
    successMessage(text) {
      this.message = text;
      document.getElementById("messageBox").classList.add("sucMessage");
      setTimeout(() => document.getElementById("messageBox").classList.remove("sucMessage"), 1500);
    },
    errorMessage(text) {
      this.message = text;
      document.getElementById("messageBox").classList.add("errMessage");
      setTimeout(() => document.getElementById("messageBox").classList.remove("errMessage"), 1500);
    }
  }
}
</script>

<style>
html,
body,
#app {
  width: 100vw;
  height: 100vh;
  overflow: hidden;
}

* {
  margin: 0;
  padding: 0;
  outline: none;
  box-sizing: border-box;
  font-family: Helvetica, Arial, sans-serif;
  user-select: none;
}

#messageBox {
  position: absolute;
  top: 10px;
  right: 20px;
  min-width: 150px;
  max-width: 200px;
  padding: 10px;
  border-radius: 10px 0 0 10px;
  color: white;
  font-size: 14px;
  font-weight: bold;
  opacity: 0;
  transform: translateY(-120%);
}

.sucMessage {
  background-color: #0a8e41;
  border-right: 8px solid #065c2a;
  animation: showMessage 1.5s ease-in-out;
}

.errMessage {
  background-color: #d60b18;
  border-right: 8px solid #930912;
  animation: showMessage 1.5s ease-in-out;
}

@keyframes showMessage {
  from {
    transform: translate(120%, 100%);
  }

  30% {
    transform: translateY(100%);
    opacity: 1;
  }

  70% {
    transform: translateY(100%);
    opacity: 1;
  }
}
</style>
