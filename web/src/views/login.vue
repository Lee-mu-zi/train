<template>

  <a-row class="login">
    <a-col :span="8" :offset="8" class="login-main">
      <h1 style="text-align: center">
        <rocket-two-tone/>&nbsp;仿12306售票系统
      </h1>
      <a-form
          :model="loginForm"
          name="basic"
          autocomplete="off"
      >
        <a-form-item
            label=""
            name="mobile"
            :rules="[{ required: true, message: '请输入手机号!' }]"
        >
          <a-input v-model:value="loginForm.mobile"/>
        </a-form-item>

        <a-form-item
            label=""
            name="code"
            :rules="[{ required: true, message: '请输入验证码!' }]"
        >
          <a-input v-model:value="loginForm.code">
            <template #addonAfter>
              <a @click="sendCode">获取验证码</a>
            </template>
          </a-input>
        </a-form-item>

        <a-form-item>
          <a-button type="primary" block @click="login">登录</a-button>
        </a-form-item>
      </a-form>
    </a-col>
  </a-row>

</template>
<script>
import {defineComponent, reactive} from 'vue';
import axios from 'axios';
import { notification } from 'ant-design-vue';

export default defineComponent({
  name: "login-view",

  setup() {

    const loginForm = reactive({
      mobile: '19999999999',
      code: '',

    });

    const sendCode = () => {
      axios.post("http://localhost:8000/member/member/send-code", {
        mobile: loginForm.mobile
      }).then(res => {
        console.log(res);
        let data = res.data;
        if (data.success) {
          notification["success"]({
            message: "验证码发送成功",
          });
          loginForm.code = "8888";
        } else {
          notification["error"]({
            message: data.message,
          });
        }
      })
    }

    const login = () => {
      axios.post("http://localhost:8000/member/member/login", {
        mobile: loginForm.mobile,
        code: loginForm.code,
      }).then(res => {
        let data = res.data;
        if (data.success) {
          notification["success"]({
            message: "登录成功",
          });
          console.log(data.content);
        } else {
          notification["error"]({
            message: data.message,
          });
        }
      })
    }

    return {
      loginForm,
      sendCode,
      login,
    };

  },
});
</script>

<style>
.login-main h1 {
  font-size: 25px;
  font-weight: bold;
}

.login-main {
  margin-top: 100px;
  padding: 30px 30px 20px;
  border: 2px solid grey;
  border-radius: 10px;
  background-color: #fcfcfc;
}
</style>