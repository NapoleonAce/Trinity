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
          <el-button type="primary" @click="handleAddStu()"  >保存</el-button>
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

        <el-table-column label="成绩">
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="handleWatchGrade(scope.$index, scope.row)">查看</el-button>
            <el-dialog title="成绩" :visible.sync="scope.row.dialogGradeVisible">
              <el-form>
                  <el-form-item
                    v-for="item in scope.row.generalGrade"
                    :key="item.subjectName"
                    :label="item.subjectName"
                    :label-width="120">
                    <el-select v-model="item.grade" placeholder="请选择">
                      <el-option
                        v-for="level in generalGradeLevel"
                        :key="level"
                        :label="level.label"
                        :value="level.value">
                      </el-option>
                    </el-select>
                  </el-form-item>
                <el-form-item
                  v-for = "item in scope.row.majorGrade"
                  :key="item.subjectName" :label="item.subjectName" :label-width="120">
                  <el-input v-model="item.grade " auto-complete="off" >
                  </el-input>
                </el-form-item>
              </el-form>
              <div slot="footer" class="dialog-footer">
                <el-button @click="handleUpdateGrade(scope.row)">确定</el-button>
              </div>
            </el-dialog>
          </template>
        </el-table-column>

        <el-table-column label="特长">

          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="handleWatchSpe(scope.$index, scope.row)">查看</el-button>
            <el-dialog title="特长" :visible.sync="scope.row.dialogSpeVisible">
              <el-form v-model="specForm">
                <el-select v-model="value" placeholder="选择特长类别">
                  <el-option
                    v-for="item in scope.row.speciality"
                    :key="scope.row.studentId + '-' + item-speCode"
                    :label="item.speType"
                    :value="item.speType">
                  </el-option>
                </el-select>
                  <el-form-item label="特长类型" :label-width="120">
                    <el-input v-model="specForm.speType" auto-complete="off" ></el-input>
                  </el-form-item>
                  <el-form-item label="特长等级" :label-width="120">
                    <el-input v-model="specForm.speLevel" auto-complete="off" ></el-input>
                  </el-form-item>
                  <el-form-item label="特长描述" :label-width="120">
                    <el-input v-model="specForm.content" auto-complete="off" ></el-input>
                  </el-form-item>
                  <el-form-item label="特长证明" :label-width="120">
                    <el-input v-model="specForm.evidence" auto-complete="off" ></el-input>
                  </el-form-item>
              </el-form>
              <div slot="footer" class="dialog-footer">
                <el-button @click="scope.row.dialogSpeVisible = false"> 取消</el-button>
                <el-button @click="handleUpdateSpe(scope.row)"> 确定</el-button>
              </div>
            </el-dialog>
          </template>
        </el-table-column>

        <el-table-column label="详细信息">
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="handleWatchInfo(scope.$index, scope.row)">查看</el-button>
            <el-dialog title="编辑信息" :visible.sync="scope.row.dialogInfoVisible">
              详细信息
              <div slot="footer" class="dialog-footer">
                <el-button @click="handleUpdateInfo(scope.row)"> 确定</el-button>
                <el-button @click="scope.row.dialogInfoVisible = false"> 取消</el-button>
              </div>
            </el-dialog>
          </template>
        </el-table-column>

        <el-table-column label="操作">
          <template slot-scope="scope">
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
  import LeftNav from "@/components/leftNav";
  export default {
    name: "stuManView",
    components: {LeftNav},
    data(){
      return{
        generalGradeLevel:[
          {
            value:'A',
            label:'A'
          },
          {
            value:'B',
            label:'B'
          },
          {
            value:'C',
            label:'C'
          },
          {
            value:'E',
            label:'E'
          },
        ],
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
        specForm:{
          speCode:'',
          speType:'',
          speLevel:'',
          content:'',
          evidence:''
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
            dialogGradeVisible:false,
            dialogFormVisible:false,
            dialogInfoVisible:false,
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
                speCode:'11',
                speType:'健美操',
                speLevel:'牛逼级别',
                content:'特别厉害的健美操，比奶茶妹妹厉害一万倍',
                evidence:'今晚表演给你看'
              },
              {
                speCode:'22',
                speType:'篮球',
                speLevel:'CXK级别',
                content:'( •̀ ω •́ )练习时长两年半',
                evidence:'B站收到了我的律师函'
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
      handleDropDown(code,row){
        //找出指定的特长，然后将其传入form中，再回传
        console.log("code:"+code)
        console.log("row"+row)
        for (var i=0;i<row.speciality.length;i++){
          if (row.speciality[i].speCode === code){
            this.specForm = row.speciality[i];
            break;
          }
        }
        this.dialogSpeVisible = true;
      },
      initStuData(){

      },
      handleUpdateInfo(row){
        row.dialogInfoVisible = false;
      },
      handleUpdateGrade(row){
        row.dialogGradeVisible = false;
      },
      handleUpdateSpe(row){
        row.dialogSpeVisible = false;
      },
      handleAddStu(){

      },
      handleDelete(index,row){

      },
      handleWatchInfo(index,row){
        row.dialogInfoVisible = true;
      },
      handleWatchSpe(index,row){
        this.specForm = row.speciality[0];
        row.dialogSpeVisible = true;
      },
      handleWatchGrade(index,row){
        row.dialogGradeVisible = true;
      },
      filterTag(value, row) {
        return row.roleName === value;
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
