<template>
  <div class="login-body">
    <div class="login-image-container">
<!--      <img-->
<!--        style="width: 500px;height: 300px"-->
<!--        src="../assets/trinity.jpg"-->
<!--        alt="">-->
    </div>
    <el-card class="login-block">
      <!--        <el-row>-->
      <!--          <el-col :span="8"><p>用户名</p></el-col>-->
      <!--          <el-col :span="16"><el-input></el-input></el-col>-->
      <!--        </el-row>-->
      <div class="other-login-container">
        <div
          :class="otherLoginContainerItemClass"
          @click="changeChosenRole('student')">
          <img
            :class="chosenRole === 'student' ? chosenBorderColor :''"
            src="../assets/student.png"
            alt="">
          <p>学生</p>
        </div>

        <div
          :class="otherLoginContainerItemClass"
          @click="changeChosenRole('college')">
          <img :class="chosenRole === 'college' ? chosenBorderColor :''"
            src="../assets/teacher2.png"
            alt="">
          <p>院校</p>
        </div>

        <div
          :class="otherLoginContainerItemClass"
          @click="changeChosenRole('admin')">
          <img :class="chosenRole === 'admin' ? chosenBorderColor :''"
            src="../assets/bussnes.png"
            alt="">
          <p>管理员</p>
        </div>
      </div>

      <el-divider
        content-position="center"
        style="margin-top:5%;font-size: 12px;color: #AAAAAA">
        不同类型账号登录
      </el-divider>

      <div class="input-item"
           style="display: inline">
        <p class="login-label-text">用户名</p>
        <el-input
          class="login-input"
          style="margin-right: 0px"
          v-model="manager.name"></el-input>
      </div>
      <div class="input-item">
        <p class="login-label-text">密码</p>
        <el-input
          class="login-input"
          v-model="manager.pw">
        </el-input>
      </div>
<!--      <router-link to="/homeView">-->
        <el-button class="login-button" type="primary" @click="handleLogin">
          登录
        </el-button>
<!--      </router-link>-->
      <div class="login-validate-field">
        {{responseMessage}}
      </div>
    </el-card>

  </div>
</template>

<script>
  import Util from "@/util/HttpUtil";

  export default {
    name: "loginView",
    data() {
      return {
        labelPosition: 'right',
        manager: {
          name: '',
          pw: '',
          role: ''
        },
        chosenRole:'',
        otherLoginContainerItemClass: "other-login-container-item",
        chosenBorderColor:"chosen-border",
        responseMessage:"",
        loading: false
      };
    },
    methods:{
      changeChosenRole(role){
        this.chosenRole = role
      },
      handleLogin(){
        //网络请求进行匹配
        var _this = this;
        this.loading = true;
        if (this.chosenRole === 'student'){
          this.manager.role = 'ROLE_stu'
        } else if (this.chosenRole === 'college'){
          this.manager.role = 'ROLE_col'
        } else if (this.chosenRole === 'admin'){
          this.manager.role = 'ROLE_admin'
        }
        this.postRequest('/login', {
          manName: this.manager.name,
          password: this.manager.pw,
          role: this.manager.role
        }).then(resp => {
          _this.loading = false;
          if (resp){
            if (resp.status === 200){
              var _manager = this.manager
              _this.$store.commit('login',_manager)
              _this.$router.push('/homeView')
            }else {
              responseMessage = resp.data
            }
          }
        })
      }
    }
  }
</script>

<style scoped>
  .login-validate-field{

  }
  .login-button{
    margin: 30px;
    position: relative;
    left: 30%;
    width: 30%;
    font-size: 16px;
    display: block;
  }
  .chosen-border {
    border: 3px solid;
  }
  #wechat{
    width: 55%;
    height: 55%;
  }
  .other-login-container{
    width: 80%;
    margin-left: 12%;
    margin-right: 8%;
    margin-top: 10%;
  }
  .other-login-container img{
    border-radius: 50%;
  }
  .other-login-container-item{
    display: inline-block;
    width: 30%;
    text-align: center;
  }
  .other-login-container img{
    margin: auto;
    display: inline-block;

    width: 60%;
    height: 60%;

  }
  .login-input{
    display: inline-block;
    width:70%;
    right: 0;
  }
  .login-label-text{
    width: 15%;
    display: inline-block;
    margin-right: 5%
  }
  .login-block{
    font-size: 16px;
  }
  .login-body{
    width: 100%;
    height: 100%;
    background-color: #fdfdfd;
    text-align: left;
  }
  .login-image-container{

    margin-top: 5%;
    margin-bottom: 2%;
  }
  .login-image-container img{
    margin: auto;
    display: block;
    width: 120px;
    height: 120px;
  }

  .login-block{
    padding: 2%;
    margin: auto;
    display: block;
    width: 25%;
    height: 40%;
    background-color: white;
  }
</style>
