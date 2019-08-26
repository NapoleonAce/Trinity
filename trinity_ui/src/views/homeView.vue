<template>
  <el-container>

    <el-aside width="300px">
      <left-nav></left-nav>
    </el-aside>

    <el-container class="main-container" style="height: 100%;">
      <div class="my-header" style="text-align: left">
        <el-input
          class="my-header-search"
          placeholder="请输入搜索内容"
          prefix-icon="el-icon-search"
        >
        </el-input>
      </div>
    </el-container>

  </el-container>
</template>

<script>
    //import leftNav from "../components/leftNav";
    import LeftNav from "@/components/leftNav";
    export default {
        name: "homeView",
        components: {LeftNav}
    }
</script>

<style scoped>
  .my-header-buttons{
    display: inline-block;
    margin-left: 10%;
  }
  .my-header-button{
    display: inline-block;
    width: 60px;
    height: 60px;
  }
  .main-container{
    top:0;
    margin-left: 300px;
    width: 100%;
    background-color: white;
  }
  .my-header{
    border-left: 1px solid #ededed;
    background-color: #fafafa;
    position: fixed;
    top:0;
    width: 50%;
    padding: 1%;
    z-index: 2;
  }
  .my-main{
    margin-top: 8%;
  }

</style>
