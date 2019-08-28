<template>

    <el-container class="main-container" style="height: 100%;">

      <el-aside style="width: 300px">
        <LeftNav></LeftNav>
      </el-aside>




      <el-main class="my-main">
      </el-main>
    </el-container>

</template>

<script>
    //import leftNav from "../components/leftNav";
    import LeftNav from "@/components/leftNav";
    import baseComponent from "@/components/baseComponent"
    export default {
        name: "homeView",
        components: {LeftNav, baseComponent},
        data(){
          return{
          }
        },
    }
</script>

<style scoped>
  .main-container{
    height: 100%;
    padding-left: 0px;
    top:0;
    width: 100%;
    background-color: white;
  }
  .my-main{
    margin-left: 300px;
    margin-top: 2%;
  }

</style>
