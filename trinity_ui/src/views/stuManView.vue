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
          <el-option label="学校名" value="2"></el-option>
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

    <el-main class="my-main">
      <el-table
        :data="tableData"
        style="width: 100%">
        <el-table-column
          label="学生ID"
          width="180">
          <template slot-scope="scope">
            <i class="el-icon-tickets"></i>
            <span style="margin-left: 10px">{{ scope.row.studentId }}</span>
          </template>
        </el-table-column>
        <el-table-column
          label="学生姓名"
          width="180">
          <template slot-scope="scope">
            <div slot="reference" class="name-wrapper">
              {{ scope.row.studentName }}
            </div>
          </template>
        </el-table-column>
        <el-table-column
          prop="roleName"
          label="准考证号"
          width="180"
         >
          <template slot-scope="scope">
            <div slot="reference" class="name-wrapper">
              {{ scope.row.examId }}
            </div>
          </template>
        </el-table-column>
        <el-table-column
          label="性别"
          width="180"
          :filters = filterList
          :filter-method="filterTag"
          filter-placement="bottom-end">
          <template slot-scope="scope">
            <div slot="reference" class="name-wrapper">
              <el-tag>
                {{ scope.row.gender }}
              </el-tag>
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
        <el-table-column label="成绩">
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="handleWatchGeneralGrade(scope.$index, scope.row)">学考</el-button>
            <el-dialog title={{scope.row.studentName}}+"学考成绩" :visible.sync="scope.row.dialogGeneralGradeVisible">
              <div slot="footer" class="dialog-footer">
                <el-button @click="scope.row.dialogGeneralGradeVisible = false">确定</el-button>
              </div>
            </el-dialog>
            <el-button
              size="mini"
              @click="handleWatchMajorGrade(scope.$index, scope.row)">选考</el-button>
            <el-dialog title={{scope.row.studentName}}+"学考成绩" :visible.sync="scope.row.dialogMajorGradeVisible">
              <div slot="footer" class="dialog-footer">
                <el-button @click="scope.row.dialogMajorGradeVisible = false">确定</el-button>
              </div>
            </el-dialog>
          </template>
        </el-table-column>
        <el-table-column label="特长">
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="handleWatchSpe(scope.$index, scope.row)">查看</el-button>
            <el-dialog title="编辑信息" :visible.sync="scope.row.dialogSpeVisible">
              <div slot="footer" class="dialog-footer">
                <el-button @click="scope.row.dialogSpeVisible = false"> 确定</el-button>
              </div>
            </el-dialog>
          </template>
        </el-table-column>
      </el-table>
    </el-main>


  </el-container>
</template>

<script>
  import LeftNav from "@/components/leftNav";
  export default {
    name: "stuManView",
    components: {LeftNav},
    data(){
      return{
        addDialogVisible:false,
        dialogSpeVisible:false,
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
          { text: '男', value: '男' },
          { text: '女', value: '女' },
        ],
        tableData: [
          {
            dialogMajorGradeVisible:false,
            dialogGeneralGradeVisible:false,
            dialogFormVisible:false,
            studentId: '1',
            studentName: '王小虎',
            examId: '学生',
            gender:'男',
            phone:'110552',
            school:'中二之风中学',
            province:'天际',
            generalGrade:[
              {
                subjectName:'',
                grade:''
              }
            ],
            majorGrade:[
              {
                subjectName:'',
                grade:''
              }
            ],
            speciality:[
              {
                speType:'',
                speLevel:'',
                content:'',
                evidence:''
              }
            ]
          }
          ]
      }
    },
    mounted(){
      this.initStuData();
    },
    methods:{
      initStuData(){

      },
      handleWatchSpe(index,row){

      },
      handleWatchGeneralGrade(index,row){

      },
      handleWatchMajorGrade(index,row){

      },
    }
  }

</script>

<style scoped>
  .my-main{
    margin-left: 300px;
    margin-top: 2%;
  }
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
