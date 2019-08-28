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
          <el-option label="用户名" value="1"></el-option>
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
          <el-button type="primary" @click="handleAdd(addForm)"  >保存</el-button>
        </div>
      </el-dialog>
    </el-header>

    <el-main class="my-main">
      <el-table
        :data="managerTableData"
        style="width: 100%">
        <el-table-column
          label="用户码"
          width="180">
          <template slot-scope="scope">
            <i class="el-icon-tickets"></i>
            <span style="margin-left: 10px">{{ scope.row.managerCode }}</span>
          </template>
        </el-table-column>
        <el-table-column
          label="用户姓名"
          width="180">
          <template slot-scope="scope">
              <div slot="reference" class="name-wrapper">
                {{ scope.row.managerName }}
              </div>
          </template>
        </el-table-column>
        <el-table-column
          prop="roleName"
          label="用户角色"
          width="180"
          :filters = filterList
          :filter-method="filterTag"
          filter-placement="bottom-end">
          <template slot-scope="scope">
            <div slot="reference" class="name-wrapper">
              <el-tag>{{ scope.row.roleName }}</el-tag>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
            <el-dialog title="编辑信息" :visible.sync="scope.row.dialogFormVisible">
              <el-form :model="form">
                <el-form-item label="用户名" :label-width="120">
                  <el-input v-model="form.name" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="角色" :label-width="120">
                  <el-select v-model="form.roleName" placeholder="选择角色">
                    <el-option label="管理员" value="管理员"></el-option>
                    <el-option label="学生" value="学生"></el-option>
                    <el-option label="院校" value="院校"></el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="旧密码" :label-width="120">
                  <el-input v-model="form.oldPassword" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="新密码" :label-width="120">
                  <el-input v-model="form.newPassword" auto-complete="off"></el-input>
                </el-form-item>
              </el-form>
              <div slot="footer" class="dialog-footer">
                <el-button @click="scope.row.dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="handleSave(scope.row)"  >保存</el-button>
              </div>
            </el-dialog>
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)">删除</el-button>
          </template>

        </el-table-column>
      </el-table>
    </el-main>
  </el-container>

</template>

<script>
  //import leftNav from "../components/leftNav";
  import LeftNav from "@/components/leftNav";
  import baseComponent from "@/components/baseComponent"
  export default {
    name: "managerView",
    components: {LeftNav, baseComponent},
    data(){
      return{
        addDialogVisible:false,
        form: {
          manName: '',
          oldPassword:'',
          newPassword:'',
          roleName: '',
          roleId:''
        },
        addForm: {
          manName: '',
          oldPassword:'',
          newPassword:'',
          roleName: '',
          roleId:''
        },
        filterList:[
          { text: '管理员', value: '管理员' },
          { text: '学生', value: '学生' },
          { text: '院校', value: '院校'}
          ],
        managerTableData: [{
          managerCode: '1',
          managerName: '王小虎',
          roleName: '学生',
          dialogFormVisible:false
        },{
          managerCode: '2',
          managerName: '王小虎',
          roleName: '管理员',
          dialogFormVisible:false
        },{
          managerCode: '3',
          managerName: '王小虎',
          roleName: '院校',
          dialogFormVisible:false
        },{
          managerCode: '4',
          managerName: '王小虎',
          roleName: '管理员',
          dialogFormVisible:false
        },{
          managerCode: '5',
          managerName: '王小虎',
          roleName: '管理员',
          dialogFormVisible:false
        }]
      }
    },
    methods: {
      transFormRoleNameToId(manager){

      },
      handleAdd(newManger){
        //上传数据，并且返回新的
        this.addDialogVisible  = false
      },
      handleSave(row){
        row.dialogFormVisible = false
      },
      filterTag(value, row) {
        return row.roleName === value;
      },
      handleEdit(index, row) {
        row.dialogFormVisible = true;
        console.log(index, row);
      },
      handleDelete(index, row) {
        console.log(index, row);
      }
    }
  }
</script>

<style scoped>
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
  .my-main{
    margin-left: 300px;
    margin-top: 2%;
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
