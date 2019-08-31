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
      <el-drawer
        title="添加学生信息"
        custom-class="demo-drawer"
        :visible.sync="addDialogVisible"
        :direction="rtl"
        :before-close="handleAddClose">
        <el-form
          class="add-student-form"
          :model="addForm">
          <el-form-item label="学生 ID" :label-width=120>
            <el-input v-model="addForm.studentId" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="学生姓名" :label-width=120>
            <el-input v-model="addForm.studentId" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="准考证号" :label-width=120>
            <el-input v-model="addForm.examId" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="性别" :label-width=120>
            <el-select v-model="addForm.gender" placeholder="选择性别">
              <el-option label="男" value="男"></el-option>
              <el-option label="女" value="女"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="电话" :label-width=120>
            <el-input v-model="addForm.school" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="高中" :label-width=120>
            <el-input v-model="addForm.school" auto-complete="off"></el-input>
          </el-form-item>
        </el-form>
<!--        <div class="demo-drawer__footer">-->
<!--          <el-button @click="addDialogVisible = false">取 消</el-button>-->
<!--          <el-button type="primary" @click=" $refs.drawer.closeDrawer()" :loading="addLoading">{{ addLoading ? '提交中 ...' : '确 定' }}</el-button>-->
<!--        </div>-->
      </el-drawer>

<!--      <el-dialog title="添加信息" :visible.sync="addDialogVisible">-->
<!--        <div slot="footer" class="dialog-footer">-->
<!--          <el-button @click="addDialogVisible = false">取 消</el-button>-->
<!--          <el-button type="primary" @click="handleAddStu()"  >保存</el-button>-->
<!--        </div>-->
<!--      </el-dialog>-->
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
              <el-form class="my-grade-form">
                <el-select v-model="gradeSelect">
                  <el-option label="选考" value="选考"></el-option>
                  <el-option label="学考" value="学考"></el-option>
                </el-select>
                <el-button @click="scope.row.selectGrade = gradeSelect">
                  选择
                </el-button>
                <div v-if="scope.row.selectGrade === '学考'" style="margin-top: 30px">
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
                </div>
                <div v-else style="margin-top: 30px">
                  <el-form-item
                    v-for = "item in scope.row.majorGrade"
                    :key="item.subjectName" :label="item.subjectName" :label-width="120">
                    <el-input v-model="item.score " auto-complete="off" >
                    </el-input>
                  </el-form-item>
                </div>

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
                <div v-if="isSelect">
                  <el-select v-model="scope.row.chosenType"
                             placeholder="选择特长类别">
                    <el-option
                      v-for="item in scope.row.speciality"
                      :key="item.speCode"
                      :label="item.speType"
                      :value="item.speType">
                    </el-option>
                  </el-select>
                  <el-button @click="chooseType(scope.row)">
                    选择
                  </el-button>
                  <el-button
                    @click="clearSpeForm(scope.row)"
                    icon="el-icon-plus" circle>
                  </el-button>
                </div>

                <el-form-item label="特长类型" :label-width=120>
                  <el-input v-model="specForm.speType" auto-complete="off" ></el-input>
                </el-form-item>
                <el-form-item label="特长等级" :label-width=120>
                  <el-input v-model="specForm.speLevel" auto-complete="off" ></el-input>
                </el-form-item>
                <el-form-item label="特长描述" :label-width=120>
                  <el-input v-model="specForm.content" auto-complete="off" ></el-input>
                </el-form-item>
                <el-form-item label="特长证明" :label-width=120>
                  <el-input v-model="specForm.evidence" auto-complete="off" ></el-input>
                </el-form-item>
              </el-form>

              <div slot="footer" class="dialog-footer">
                <el-button @click="handleUpdateSpe(scope.row)">确定</el-button>
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
              <el-form
                :model="form">
                <el-form-item label="学生 ID" :label-width=120>
                  <el-input v-model="form.studentId" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="学生姓名" :label-width=120>
                  <el-input v-model="form.studentName" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="性别" :label-width=120>
                  <el-select v-model="form.gender" placeholder="选择性别">
                    <el-option label="男" value="男"></el-option>
                    <el-option label="女" value="女"></el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="准考证号" :label-width=120>
                  <el-input v-model="form.examId" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="电话" :label-width=120>
                  <el-input v-model="form.phone" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="高中" :label-width=120>
                  <el-input v-model="form.school" auto-complete="off"></el-input>
                </el-form-item>
              </el-form>
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
        isSelect:true,
        addLoading:false,
        gradeSelect:'学考',
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
        form: {
          studentId:'',
          studentName: '',
          examId:'',
          gender: '',
          phone:'',
          school:''
        },
        specForm:{
          speCode:'',
          speType:'',
          speLevel:'',
          content:'',
          evidence:''
        },
        addForm: {
          studentId:'',
          studentName: '',
          examId:'',
          gender: '',
          phone:'',
          school:''
        },
        filterList:[
          { text: '男', value: '男' },
          { text: '女', value: '女' },
        ],
        tableData: [
          {
            selectGrade:'学考',
            chosenType:'',
            dialogGradeVisible:false,
            dialogFormVisible:false,
            dialogInfoVisible:false,
            dialogSpeVisible:false,
            studentId: '1',
            studentName: '王小虎',
            examId: '学生',
            gender:'男',
            phone:'110552',
            school:'中二之风中学',
            province:'天际',
            generalGrade:[
              {
                subjectName:'数学',
                grade:'A'
              },
              {
                subjectName:'语文',
                grade:'B'
              },
              {
                subjectName:'英语',
                grade:'B'
              },
              {
                subjectName:'技术',
                grade:'B'
              },
              {
                subjectName:'历史',
                grade:'B'
              },
              {
                subjectName:'地理',
                grade:'B'
              },
              {
                subjectName:'政治',
                grade:'B'
              },

            ],
            majorGrade:[
              {
                subjectName:'数学',
                score:120
              },
              {
                subjectName:'英语',
                score:120
              },
              {
                subjectName:'语文',
                score:120
              },
              {
                subjectName:'物理',
                score:91
              },
              {
                subjectName:'化学',
                score:97
              },
              {
                subjectName:'生物',
                score:97
              },

            ],
            speciality:[
              {
                speCode:1,
                speType:'健美操',
                speLevel:'牛逼级别',
                content:'特别厉害的健美操，比奶茶妹妹厉害一万倍',
                evidence:'今晚表演给你看'
              },
              {
                speCode:2,
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
      initStuData(){

      },
      handleAddClose(done){
        this.$confirm('是否需要保存？')
          .then(_ => {
            this.addLoading = true;
            setTimeout(()=>{
              this.addLoading = false;
              done();
            },2000);
          })
          .catch(_ => {});
      },
      handleUpdateInfo(row){
        row.dialogInfoVisible = false;
      },
      handleUpdateGrade(row){
        row.dialogGradeVisible = false;
      },
      handleUpdateSpe(row){
        if(this.isSelect){
          //update
        }else{
          //insert
        }
        row.dialogSpeVisible = false;
      },
      handleDelete(index,row){

      },
      handleWatchInfo(index,row){
        this.form = row;
        row.dialogInfoVisible = true;
      },
      handleWatchSpe(index,row){
        this.specForm = row.speciality[0];
        row.chosenType = row.speciality[0].speType;
        row.dialogSpeVisible = true;
      },
      handleWatchGrade(index,row){
        row.dialogGradeVisible = true;
      },
      filterTag(value, row) {
        return row.roleName === value;
      },
      clearSpeForm(row){
        var _specForm = {
          speCode:'',
          speType:'',
          speLevel:'',
          content:'',
          evidence:''
        };
        row.chosenType = "";
        this.specForm = _specForm;
        this.isSelect = false;
      },
      chooseType(row){
        //改变specForm
        console.log(row.chosenType);
        console.log(row);
        for (var i=0;i<row.speciality.length;i++){
          console.log(row.speciality[i]);
          if (row.speciality[i].speType === row.chosenType){
            this.specForm = row.speciality[i];
            break;
          }
        }
      },
    }
  }

</script>

<style scoped>
  .add-student-form{
    padding: 20px 20px 20px 20px;
  }
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
  .my-grade-form{
    display: inline-block;

  }
</style>
