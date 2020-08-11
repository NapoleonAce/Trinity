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
      <el-dialog
        title="添加学生信息"
        custom-class="demo-drawer"
        :visible.sync="addDialogVisible">
        <el-form
          class="add-student-form"
          :model="addForm">
          <el-form-item label="学生 ID" :label-width=120>
            <el-input v-model="addForm.studentId" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="学生姓名" :label-width=120>
            <el-input v-model="addForm.studentName" auto-complete="off"></el-input>
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
            <el-input v-model="addForm.phone" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="高中" :label-width=120>
            <el-input v-model="addForm.school" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="所属省份" :label-width=120>
            <el-input v-model="addForm.province" auto-complete="off"></el-input>
          </el-form-item>
        </el-form>

        <div class="demo-drawer__footer">
          <el-button @click="addDialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="handleAddClose" >确定</el-button>
        </div>
      </el-dialog>

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
        <el-table-column label="学考成绩">
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="watchGeneralGrade(scope.row)">查看</el-button>
            <el-dialog title="成绩" :visible.sync="scope.row.dialogGeneralGradeVisible">
              <el-form >
                <div style="margin-top: 30px">
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
              </el-form>
              <div slot="footer" class="dialog-footer">
                <el-button @click="scope.row.dialogGeneralGradeVisible = false">取消</el-button>
                <el-button @click="updateGeneralGrade(scope.row)">确定</el-button>
              </div>
            </el-dialog>
          </template>

        </el-table-column>
        <el-table-column label="选考成绩">
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="watchMajorGrade(scope.row)">查看</el-button>
            <el-dialog title="成绩" :visible.sync="scope.row.dialogMajorGradeVisible">
              <el-form class="my-grade-form">
                <div style="margin-top: 30px">
                  <el-form-item
                    v-for = "item in scope.row.majorGrade"
                    :key="item.subjectName" :label="item.subjectName" :label-width="120">
                    <el-input v-model="item.score " auto-complete="off" >
                    </el-input>
                  </el-form-item>
                </div>
              </el-form>
              <div slot="footer" class="dialog-footer">
                <el-button @click="scope.row.dialogMajorGradeVisible = false">取消</el-button>
                <el-button @click="updateMajorGrade(scope.row)">确定</el-button>
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
                <div v-if="scope.row.isSelect">
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
                    @click="clearSpeForm(scope.row)">
                    添加
                  </el-button>
                  <el-button @click="handleSpeDelete(scope.$index, scope.row)" type="danger">
                    删除此条
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
                <el-button @click="scope.row.dialogSpeVisible = false">取消</el-button>
                <el-button @click="handleUpdateSpe(scope.row)">更新</el-button>
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
                <el-form-item label="所属省份" :label-width=120>
                  <el-input v-model="form.province" auto-complete="off"></el-input>
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
              @click="handleStuDelete(scope.$index, scope.row)">删除</el-button>
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
          school:'',
          province:''
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
          school:'',
          province:''
        },
        filterList:[
          { text: '男', value: '男' },
          { text: '女', value: '女' },
        ],
        tableData: [
          {
            isSelect:true,
            selectGrade:'学考',
            chosenType:'',
            dialogGradeVisible:false,
            dialogGeneralGradeVisible:false,
            dialogMajorGradeVisible:false,
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
        this.getRequest('/stuMan/select/stu').then(resp=>{
          if (resp && resp.status === 200){
            console.log(resp);
            var data = resp.data.obj;
            console.log(data);
            var _tableData = [];
            for(var i=0;i<data.length;i++){
              console.log(data[i].studentName);
              _tableData[i] = {
                selectGrade:'学考',
                chosenType:'',
                dialogGradeVisible:false,
                dialogFormVisible:false,
                dialogGeneralGradeVisible:false,
                dialogMajorGradeVisible:false,
                dialogInfoVisible:false,
                dialogSpeVisible:false,
                studentId: data[i].studentId,
                studentName: data[i].studentName,
                examId: data[i].examId,
                gender: data[i].gender,
                phone: data[i].phone,
                school: data[i].school,
                province: data[i].province,
                generalGrade: [],
                majorGrade: [],
                speciality: []
              }
              console.log(_tableData[i].dialogGeneralGradeVisible)
            }
            this.tableData = _tableData;
          }
        })
      },
      handleAddClose(){
        //添加学生的时候就添加成绩，选考成绩选定之后就会自动生成所有成绩
        var reqData = this.addForm;
        this.postRequest('/stuMan/add/stu',reqData)
          .then( resp =>{
            if(resp && resp.status === 200){
              this.tableData.add(reqData);
            }
          });
        this.addDialogVisible = false;
      },
      handleUpdateInfo(row){
        var _param = this.form;
        _param.oldStudentId = row.studentId;
        this.putRequest("/stuMan/update/stu",_param)
          .then(resp =>{
            if (resp && resp.status === 200){
              row.studentId = _param.studentId;
              row.studentName = _param.studentName;
              row.examId = _param.examId;
              row.phone = _param.phone;
              row.province = _param.province;
              row.school = _param.school;
              row.gender = _param.gender;
            }
          });
        row.dialogInfoVisible = false;
      },
      closeGenGrade(index,row){
        console.log(row.dialogGeneralGradeVisible);
        row.dialogGeneralGradeVisible = false;
        console.log(row.dialogGeneralGradeVisible);
      },
      watchGeneralGrade(row){
        //get
        this.getRequest('/stuMan/select/gen?studentId='+row.studentId)
          .then(resp=>{
            console.log(resp);
            if (resp){
              if(resp.data.status === 200){
                var data = resp.data.obj;
                console.log(resp.data);
                console.log(data);
                row.generalGrade = data;
                console.log(row.generalGrade);

                row.dialogGeneralGradeVisible = true;
              }
            }
          });
      },
      watchMajorGrade(row){
        this.getRequest('/stuMan/select/major?studentId='+row.studentId)
          .then(resp=>{
            if (resp){
              if(resp.data.status === 200){
                var data = resp.data.obj;
                row.majorGrade = data;
                row.dialogMajorGradeVisible = true;
              }
            }
          });
      },
      updateGeneralGrade(row){
        var _param = {
              generalGradeList:row.generalGrade
        };
        console.log(typeof (_param));
        console.log(JSON.stringify(_param));

        this.postRequestTest("/stuMan/update/gen",JSON.stringify(_param))
          .then(resp =>{
            if (resp && resp.status === 200){
              row.dialogGeneralGradeVisible = false;
            }
          })
      },
      updateMajorGrade(row){

        var _param = {
          majorGradeList: row.majorGrade
        };
        console.log(JSON.stringify(_param))
        this.postRequestTest("/stuMan/update/major",JSON.stringify(_param))
          .then(resp =>{
            if (resp && resp.status === 200){
              row.dialogMajorGradeVisible = false;
            }
          })
      },
      handleUpdateGrade(row){
        var _param ={
          gradeUnion:{
            generalGradeList:row.generalGrade,
            majorGradeList:row.majorGrade
          }
        };
        console.log(_param);
        this.putRequestTest("/stuMan/update/grade",_param)
          .then(resp =>{
            if (resp && resp.status===200){
              console.log(resp);
            }
          });

        row.dialogGradeVisible = false;
      },
      handleUpdateSpe(row){
        if(row.isSelect){
          //update
          var _param = this.specForm;
          console.log(_param);
          this.putRequest('/stuMan/update/spe',_param)
            .then(resp => {
              if (resp && resp.status === 200){
                console.log(resp);
              }
            })
        }else{
          //insert
          var _speForm = this.specForm;
          _speForm.studentId = row.studentId;
          this.postRequest('/stuMan/add/spe',_speForm)
            .then(resp =>{
              if (resp && resp.status === 200){
                var data = resp.data;
                _speForm.speCode = data.speCode;
                row.speciality.add(_speForm);
                row.isSelect = true;
              }
            })

        }

        row.dialogSpeVisible = false;

      },
      handleStuDelete(index,row){
        var studentId = row.studentId;
        this.$confirm('此操作将永久删除['+studentId+'],是否继续?','提示',{
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(()=>{
          this.deleteRequest('/stuMan/delete/stu',{
            studentId:studentId
          }).then(reqs =>{
            if (reqs && reqs.status === 200){
              this.tableData.splice(index,1);
            }
          })
        })
      },
      handleSpeDelete(index,row){
        var chosenSpe = this.specForm;
        this.deleteRequest('stuMan/delete/spe',chosenSpe)
          .then(resp =>{
            if (resp && resp.status === 200){
              this.handleWatchSpe(index,row);
              row.dialogSpeVisible = false;
            }
          });

      },
      handleWatchInfo(index,row){
        this.form = row;
        row.dialogInfoVisible = true;
      },
      handleWatchSpe(index,row){
        this.getRequest('/stuMan/select/spe?studentId='+row.studentId)
          .then(resp =>{
             if (resp && resp.status === 200){
               var data = resp.data.obj;
               console.log(data);
               row.speciality = data;
               this.specForm = row.speciality[0];
               row.chosenType = row.speciality[0].speType;
               row.isSelect = true;
             }else{
               console.log(1);
               row.speciality[0] = this.specForm;
               this.clearSpeForm(row);
             }
          })
        row.dialogSpeVisible = true;
      },
      handleWatchGrade(index,row){
        this.getRequest('/stuMan/select/grade?studentId='+row.studentId)
          .then(resp=>{
            if(resp && resp.status === 200){
              var data = resp.data.obj;
              row.generalGrade = data.generalGradeList;
              row.majorGrade = data.majorGradeList;
            }
          });
        row.dialogGradeVisible = true;
      },
      filterTag(value, row) {
        return row.gender === value;
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
        row.isSelect = false;
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
