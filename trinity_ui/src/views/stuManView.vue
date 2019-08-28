<template>
  <el-container class="main-container" style="height: 100%;">

    <el-aside style="width: 300px">
      <LeftNav></LeftNav>
    </el-aside>

    <el-header class="my-header" >
      <el-input
        class="my-header-search"
        placeholder="请输入搜索内容"
        prefix-icon="el-icon-search">
        <el-select v-model="selectType" slot="prepend" placeholder="请选择" style="width: 120px">
          <el-option label="学生姓名" value="1"></el-option>
          <el-option label="用户码" value="2"></el-option>
        </el-select>
        <el-button slot="append" icon="el-icon-search"></el-button>
      </el-input>
      <el-button
        @click= "addDialogVisible = true"
        icon="el-icon-plus" circle></el-button>
      <el-dialog title="编辑信息" :visible.sync="addDialogVisible">
        <el-form :model="addForm">
          <el-form-item label="用户名" :label-width="120">
            <el-input v-model="addForm.manName" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="角色" :label-width="120">
            <el-select v-model="addForm.roleName" placeholder="选择角色">
              <el-option label="管理员" value="管理员"></el-option>
              <el-option label="学生" value="学生"></el-option>
              <el-option label="院校" value="院校"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="密码" :label-width="120">
            <el-input v-model="addForm.password" auto-complete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="addDialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="handleAdd()"  >保存</el-button>
        </div>
      </el-dialog>
    </el-header>



  </el-container>
</template>

<script>
  import LeftNav from "@/components/leftNav";
  export default {
    name: "stuManView",
    components: {LeftNav}
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
  .my-header-search{
      margin-right: 50px;
      width: 500px;
    }
  .main-container{
    height: 100%;
    padding-left: 0px;
    top:0;
    width: 100%;
    background-color: white;
  }
  .my-header{
    /*border: 1px solid #ededed;*/
    text-align: left;
    background-color: white;
    margin-left: 300px;
    display: inline-block;
    top:0;
    width:80%;
    padding: 1%;
    z-index: 2;
  }
</style>
