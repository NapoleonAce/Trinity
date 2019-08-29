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
          <el-form-item label="角色" :label-width="120">
            <el-select v-model="addForm.roleName" placeholder="选择角色">
              <el-option label="管理员" value="管理员"></el-option>
              <el-option label="学生" value="学生"></el-option>
              <el-option label="院校" value="院校"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="用户码" :label-width="120">
            <el-input v-model="addForm.manCode" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="用户名" :label-width="120">
            <el-input v-model="addForm.manName" auto-complete="off"></el-input>
          </el-form-item>

          <el-form-item label="密码" :label-width="120">
            <el-input v-model="addForm.newPassword" auto-complete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="addDialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="handleAdd()"  >保存</el-button>
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
                <el-form-item label="角色" :label-width="120">
                  <el-select v-model="form.roleName" placeholder="选择角色">
                    <el-option label="管理员" value="管理员"></el-option>
                    <el-option label="学生" value="学生"></el-option>
                    <el-option label="院校" value="院校"></el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="用户代码" :label-width="120">
                  <el-input v-model="scope.row.managerCode" auto-complete="off" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="用户名" :label-width="120">
                  <el-input v-model="scope.row.managerName" auto-complete="off" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="新用户名" :label-width="120">
                  <el-input v-model="form.newManName" auto-complete="off"></el-input>
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
  import Utils from "@/utils/Utils"
  export default {
    name: "managerView",
    components: {LeftNav, baseComponent},
    data(){
      return{
        addDialogVisible:false,
        form: {
          manCode:'',
          oldManName: '',
          newManName:'',
          oldPassword:'',
          newPassword:'',
          roleName: '',
          roleId:''
        },
        addForm: {
          manCode:'',
          manName: '',
          newPassword:'',
          roleName: '',
          roleId:''
        },
        filterList:[
          { text: '管理员', value: '管理员' },
          { text: '学生', value: '学生' },
          { text: '院校', value: '院校'}
          ],
        managerTableData: [
          {
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
        }]
      }
    },
    mounted:function () {
     this.initManagers();
    },
    methods: {
      initManagers(){
        this.getRequest('/manager/select').then(resp =>{
          if (resp && resp.status === 200){
            console.log(resp)
            var data = resp.data.obj;
            var _managerTableData = [];
            for (var i=0;i < data.length;i++){
              console.log(data[i].name);
              _managerTableData[i]= {
                managerCode:data[i].code,
                managerName:data[i].name,
                roleName:Utils.transformIdToRoleName(data[i].roleId),
                dialogFormVisible:false
              };
            }
            this.managerTableData = _managerTableData;
          }
        })
      },
      handleAdd(){
        //上传数据，并且返回新的
        var newManager = this.addForm;
        newManager.roleId = Utils.transformRoleNameToId(newManager.roleName);
        console.log(newManager.roleId);
        this.postRequest('/manager/add',{
          manCode:newManager.manCode,
          manName:newManager.manName,
          password:newManager.newPassword,
          roleId:newManager.roleId
        }).then(resp =>{
          if (resp){
            if (resp.status === 200){
              //数据更新,利用回传的数据即可
              var data = resp.data;
              this.managerTableData.add({
                managerCode: data.code,
                managerName: data.name,
                roleName: newManager.roleName,
                dialogFormVisible:false
              })
            }
          }
        })
        this.addDialogVisible  = false
      },
      handleSave(row){
        //上传数据，并且返回新的数据
        var _form = this.form;
        _form.oldManName = row.managerName;
        _form.manCode = row.managerCode;
        _form.roleId = Utils.transformRoleNameToId(_form.roleName);
        console.log(_form);
        this.putRequest('/manager/update',_form)
          .then(resp =>{
            if (resp && resp.status === 200){
              //回传新数据
              var data = resp.data;
              row.managerName = data[0].name;
              row.roleName = Utils.transformIdToRoleName(data[1])
            }
          })
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
        console.log(row.managerName);
        var name = row.managerName;
        this.deleteRequest('/manager/delete',{
          manName:name
        }).then(resp =>{
          if (resp && resp.status === 200){
            this.managerTableData.splice(index,1)
          }
        })
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
