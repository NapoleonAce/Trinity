<template>
  <el-container class="left-nav">

    <el-aside>

      <div class="left-user-date">
        <el-dropdown
          style="color: black"
          trigger="click">
          <div id = 'user-message'>
            <img
              id = 'user-head-img'
              src="../assets/student.png" alt=""
            >
            <span id='user-name'>
           Ventura
         </span>

          </div>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>同步</el-dropdown-item>
            <el-dropdown-item>统计</el-dropdown-item>
            <el-dropdown-item>注销</el-dropdown-item>
            <router-link to="/aboutView">
              <el-dropdown-item divided>关于</el-dropdown-item>
            </router-link>
            <el-dropdown-item>高级会员</el-dropdown-item>
            <el-dropdown-item divided>小程序</el-dropdown-item>
            <el-dropdown-item></el-dropdown-item>

          </el-dropdown-menu>
        </el-dropdown>
      </div>

      <el-menu
        style = 'width: 100%;font-size: 18px'
        text-color="black"
        active-text-color="#ffd04b">

        <el-submenu index="1">
          <template slot="title">
            <i class="el-icon-location"></i>
            <span>管理员</span>
          </template>
          <el-menu-item-group>
            <el-menu-item index="1-1">用户管理</el-menu-item>
            <el-menu-item index="1-2">院校管理</el-menu-item>
            <el-menu-item index="1-3">学生管理</el-menu-item>
          </el-menu-item-group>
        </el-submenu>

        <el-submenu index="2">
          <template slot="title">
            <i class="el-icon-location"></i>
            <span>院校</span>
          </template>
          <el-menu-item-group>
            <el-menu-item index="2-1">专业管理</el-menu-item>
            <el-menu-item index="2-2">招生管理</el-menu-item>
          </el-menu-item-group>
        </el-submenu>

        <el-submenu index="3">
          <template slot="title">
            <i class="el-icon-location"></i>
            <span>学生</span>
          </template>
          <el-menu-item-group>
            <el-menu-item index="3-1">查询系统</el-menu-item>
            <el-menu-item index="3-2">个人信息</el-menu-item>
          </el-menu-item-group>
        </el-submenu>


      </el-menu>

    </el-aside>
  </el-container>

</template>

<script>
    export default {
        name: "leftNav",
        methods: {
          handleOpen(key, keyPath) {
            console.log(key, keyPath);
          },
          handleClose(key, keyPath) {
            console.log(key, keyPath);
          }
        }
    }
</script>

<style scoped>
  /*.left-nav a{*/
  /*  color: #000;*/
  /*  text-decoration: none;*/
  /*}*/
  a{
    color: #000;
    text-decoration: none;
  }
  .menu-item-text{
    font-size: 20px;
  }
  .left-nav{
    /*不能少于300px*/
    /*text-align: right;*/
    /*border-right: 1px solid #ededed;*/
    top: 0;
    padding-top: 2%;
    position: fixed;
    float: right;
    height: 100%;
    min-width: 300px;
    /*width: 20%;*/

    /*color: black;*/
  }
  .left-user-date{
    display: inline-block;
    position: relative;
    left: 40px;
  }
  .left-nav-header-date{
    display: inline-block;
    margin-left: 20px;
  }
  .solar-date{
    font-size: 8px;
  }
  .lunar-date{
    font-size: 14px;
  }
  .today-date{
    font-size: 30px;
  }
  #user-message{
    display: inline-block;
  }
  #user-name{
    font-size: 18px;
    display: block;
    margin: 10px 0 20px 0;
  }
  #user-head-img{
    width: 70px;
    height: 70px;
    border-radius: 50%;
    border:solid #2c3e50 1px;
  }

</style>
