<template>
  <div style="text-align: center;">
    <div id="app-div" style="background-color: aquamarine;">
      <h1>Test Page</h1>
      <button v-on:click="sendRequest">SendRequest</button>
      <br>
      <span>{{ response }}</span>
    </div>

    <div style="background-color: bisque; margin-top: 20px;">
      <Article />
    </div>

    <div style="background-color: aqua; margin-top: 20px;">
      <API />
    </div>

  </div>
</template>
<script setup>

// import axios from "https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"
import { ref } from 'vue'
import API from './Api.vue'
import Article from './Article.vue'

const response = ref(null);
const sendRequest = () => {
  // "http://uncleyumo.cn/big-event/user/login"
  console.log("SendRequest() Called");
  // let url = "http://uncleyumo.cn/big-event/user/login";
  let url = "http://localhost:8080/user/test";
  let xhr = new XMLHttpRequest();

  xhr.open("POST", url, true);
  xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
  xhr.setRequestHeader("Authorization", "Basic YWRtaW46YWRtaW4=");
  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4 && xhr.status === 200) {
      response.value = xhr.responseText;
      console.log(response.value);
    }
  };
  // let data = JSON.stringify({
  //   "username": "jimmy",
  //   "password": "jimmy"
  // });
  xhr.send();
}
</script>

<style scoped></style>
