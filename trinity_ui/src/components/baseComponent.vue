<template>
  <div>
    <el-aside width="300px">
      <left-nav></left-nav>
    </el-aside>
    <el-header class="my-header" style="text-align: left">
      <el-menu :default-active="activeIndex" class="header-nav" mode="horizontal" >
        <el-menu-item>
          <el-input
            class="my-header-search"
            placeholder="请输入搜索内容"
            prefix-icon="el-icon-search">
            <el-select v-model="selectType" slot="prepend" placeholder="请选择" style="width: 120px">
              <el-option label="院校" value="1"></el-option>
              <el-option label="专业" value="2"></el-option>
            </el-select>
            <el-button slot="append" icon="el-icon-search"></el-button>
          </el-input>
        </el-menu-item>
        <el-menu-item index="1">
          <span class="menu-item-text">推荐</span>
        </el-menu-item>
        <el-menu-item index="2">
          <span class="menu-item-text">搜索</span>
        </el-menu-item>
      </el-menu>
    </el-header>

  </div>

</template>

<script>
  //import leftNav from "../components/leftNav";
  import LeftNav from "@/components/leftNav";
  export default {
    name: "homeView",
    components: {LeftNav},
    data(){
      return{
        radio3:'北京',
        selectType:'',
        activeIndex:1
      }
    },
  }
</script>

<style scoped>
  .menu-item-text{
    font-size: 20px;
  }
  .header-nav{

  }
  .my-header-search{
    margin-right: 50px;
    width: 500px;
  }
  .my-header-buttons{
    display: inline-block;
    margin-left: 10%;
  }
  .my-header-button{
    display: inline-block;
    width: 60px;
    height: 60px;
  }

  .my-header{
    /*border: 1px solid #ededed;*/
    background-color: white;
    margin-left: 300px;
    display: inline-block;
    top:0;
    width:80%;
    padding: 1%;
    z-index: 2;
  }
  .my-main{
    margin-left: 300px;
    margin-top: 2%;
  }

</style>
