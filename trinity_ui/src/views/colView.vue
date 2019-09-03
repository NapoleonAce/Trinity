<template>
<!--单个院校进入的界面
    首先知道自己的是个什么院校
    然后知道自己的招生计划
    将搜索栏旁边的东西改成几几年的select,可以进行添加
    再添加一个按钮可以查看报名计划-->
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
        @click= "addEnrollDialogVisible = true"
        >添加计划</el-button>
      <el-dialog title="添加计划" :visible.sync="addEnrollDialogVisible">
        <el-form :model="addEnrollForm">
          <el-form-item label="年份" :label-width="120">
            <el-input v-model="addEnrollForm.year" auto-complete="off" :disabled="true"></el-input>
          </el-form-item>
          <el-form-item label="专业名" :label-width="120">
            <el-select v-model="addEnrollForm.domainId" placeholder="选择专业">
              <el-option
                v-for="item in domainData"
                :key="item.domainId"
                :label="item.domainName"
                :value="item.domainId">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="选考科目" :label-width="120">
            <el-input v-model="addEnrollForm.subjectReq" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="计划人数" :label-width="120">
            <el-input v-model="addEnrollForm.number" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="学费" :label-width="120">
            <el-input v-model="addEnrollForm.price" auto-complete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="addEnrollDialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="handleAddEnroll()"  >添 加</el-button>
        </div>
      </el-dialog>

      <el-button @click="handleWatchApplyInfo">报名信息</el-button>
      <el-dialog title="报名信息" :visible.sync="applyDialogVisible">
        <el-button @click="handleWatchApplyInfo" :disabled="true">添加</el-button>
        <el-form :model="applyInfoForm">
          <el-form-item label="报名方式" :label-width="120">
            <el-input
              v-model="applyInfoForm.applyWay"
              type="textarea"
              :autosize="{ minRows: 4, maxRows: 100}"
              auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="报名条件" :label-width="120">
            <el-input
              type="textarea"
              :autosize="{ minRows: 4, maxRows: 100}"
              v-model="applyInfoForm.applyCondition"
              auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="报名开始时间" :label-width="120">
            <el-date-picker
              v-model="applyInfoForm.applyBegin"
              type="datetime"
              placeholder="选择日期时间">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="报名结束时间" :label-width="120">
            <el-date-picker
              v-model="applyInfoForm.applyFinish"
              type="datetime"
              placeholder="选择日期时间">
            </el-date-picker>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="applyDialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="handleApply"  >保 存</el-button>
        </div>
      </el-dialog>

      <el-button>专业管理</el-button>

    </el-header>

    <el-main class="my-main">
      <el-table
        :data="enrollData"
        style="width: 100%">
        <el-table-column
          label="专业名"
          width="180">
          <template slot-scope="scope">
            <i class="el-icon-tickets"></i>
            <span style="margin-left: 10px">{{ scope.row.domainName }}</span>
          </template>
        </el-table-column>
        <el-table-column
          label="类别"
          width="180">
          <template slot-scope="scope">
            <div slot="reference" class="name-wrapper">
              {{ scope.row.domainType }}
            </div>
          </template>
        </el-table-column>
        <el-table-column
          label="选课条件"
          width="180">
          <template slot-scope="scope">
            <div slot="reference" class="name-wrapper">
              {{ scope.row.subjectReq }}
            </div>
          </template>
        </el-table-column>

        <el-table-column
          label="计划招生"
          width="180">
          <template slot-scope="scope">
            <div slot="reference" class="name-wrapper">
              {{ scope.row.number }}
            </div>
          </template>
        </el-table-column>
        <el-table-column
          label="费用"
          width="180">
          <template slot-scope="scope">
            <div slot="reference" class="name-wrapper">
              {{ scope.row.price }}
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
                <el-form-item label="年份" :label-width="120">
                  <el-input v-model="form.year" auto-complete="off" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="专业名" :label-width="120">
                  <el-input v-model="form.domainName" auto-complete="off" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="专业类别" :label-width="120">
                  <el-input v-model="form.domainType" auto-complete="off" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="选课要求" :label-width="120">
                  <el-input v-model="form.subjectReq" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="计划数" :label-width="120">
                  <el-input v-model="form.number" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="学费" :label-width="120">
                  <el-input v-model="form.price" auto-complete="off"></el-input>
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
  import LeftNav from "@/components/leftNav";
  // import baseComponent from "@/components/baseComponent"
  import Utils from "@/utils/Utils"
  import StuSelect from "@/views/stuSelect";
    export default {
      name: "colView",
      components:{StuSelect, LeftNav},
      data(){
        return {
          isAdd:false,
          addEnrollDialogVisible:false,
          applyDialogVisible:false,
          addDomainDialogVisible:false,
          editEnrollDialogVisible:false,
          applyInfoForm:{
            collegeId:4,
            applyInfoId:2,
            applyWay:'',
            applyCondition:'',
            applyBegin:'',
            applyFinish:''
          },
          addEnrollForm: {
            domainName:'',
            year:2019,
            subjectReq:"",
            number:0,
            price:null
          },
          addForm: {
            manCode:'',
            manName: '',
            newPassword:'',
            roleName: '',
            roleId:''
          },
          form:{
            domainId:0,
            domainName:'',
            subjectReq:'',
            domainType:'',
            year:0,
            number:0,
            price:0
          },
          filterList:[
            { text: '管理员', value: '管理员' },
            { text: '学生', value: '学生' },
            { text: '院校', value: '院校'}
          ],
          enrollData: [
            {
              dialogFormVisible:false,
              domainId:8,
              domainName:'',
              domainType:'',
              subjectReq:"",
              year:2019,
              number:0,
              price:0
            },
            ],
          domainData:[]
        }
      },
      mounted(){
        this.initEnrollData();
        this.loadDomainInfo();
      },
      methods:{
        loadDomainInfo(){
          this.getRequest("/col/dom?collegeId="+4)
            .then(resp =>{
              if (resp && resp.status === 200){
                var data = resp.data.obj;
                console.log(data);
                var _domainData = [];
                for (var i=0;i<data.length;i++){
                  _domainData[i] = {
                    domainId:data[i].domainId,
                    domainName:data[i].domainName,
                    domainType:data[i].domainType,
                    collegeId:data[i].collegeId,
                    content:data[i].content
                  }
                }
                this.domainData = _domainData;
              }
            })
        },
        initEnrollData(){
          //暂定为2
          this.getRequest("/col/enroll?collegeId="+4)
            .then(resp =>{
              if (resp && resp.status === 200){
                var data = resp.data.obj;
                var _enrollData = [];
                for (var i=0;i<data.length;i++){
                  _enrollData[i] = {
                    dialogFormVisible:false,
                    domainId:data[i].domainId,
                    domainName:data[i].domainName,
                    domainType:data[i].domainType,
                    subjectReq:data[i].subjectReq,
                    year:data[i].year,
                    number:data[i].number,
                    price:data[i].price
                  }
                }
                this.enrollData = _enrollData;
              }
            })
        },
        changeEditPlanYear(row){
          //将计划中的form进行修改
        },
        handleAddApply(){
          var _param = this.applyInfoForm;
          this.postRequest("/col/apply",_param)
            .then(resp =>{
              if (resp && resp.status === 200){
                this.isAdd = false;
                this.applyDialogVisible = false;
              }
            });

        },
        handleUpdateApply(){
          var _param = this.applyInfoForm;
          // console.log(_param);
          // console.log(typeof (this.applyInfoForm.applyBegin));
          //时间转换的问题2019-09-23T16:00:00.000+0000
        //  2019-09-17T16:00:00.000+0000
          //Wed Sep 18 2019 00:00:00 GMT+0800
          this.putRequest("/col/apply",_param)
            .then(resp =>{
              if (resp && resp.status === 200){
                this.applyDialogVisible = false;
              }
            });

        },
        handleApply(){
          if (this.isAdd){
            console.log("isAdd: "+this.isAdd);
            this.handleAddApply();
          } else {
            this.handleUpdateApply();
          }
        },
        handleWatchApplyInfo(){
          this.applyDialogVisible = true;
          //this.clearApplyForm();
          console.log("applyinfo begin")
          this.getRequest("/col/apply?collegeId="+4)
            .then((resp) =>{
              console.log("applyinfo resp")
              console.log(resp)
              if (resp){
                console.log(resp)
                if (resp.status === 200) {
                  var data = resp.data.obj;
                  //要将时间进行转换
                  console.log("i am here 200")
                  var begin = Utils.formatDate(data.applyBegin);
                  var finish = Utils.formatDate(data.applyFinish);
                  this.applyInfoForm = data;
                  this.applyInfoForm.applyBegin = begin;
                  this.applyInfoForm.applyFinish = finish;
                }else {
                  console.log("i am here")
                  this.clearApplyForm();
                }
              }
            })
          console.log("applyinfo ennd")
        },
        clearApplyForm(){
          var _applyInfoForm ={
            collegeId:4,
            applyWay:'',
            applyCondition:'',
            applyBegin:'',
            applyFinish:''
          };
          this.applyInfoForm = _applyInfoForm;
          this.isAdd = true;
        },
        handleWatchAddPlan(){

        },
        handleAddEnroll(){
          var _param = this.addEnrollForm;
          this.postRequest("/col/enroll",_param)
            .then(resp =>{
              if (resp && resp.status === 200){
                this.initEnrollData();
              }
            })
          this.addEnrollDialogVisible = false;
        },
        handleEdit(index,row){
          this.form = row;
          row.dialogFormVisible = true;
        },
        handleSave(row){
          var _param = this.form;
          this.putRequest('/col/enroll',_param)
            .then(resp =>{
              if (resp && resp.status === 200){
                this.initEnrollData();
              }
            });
          row.dialogFormVisible = false;
        },
        handleDelete(index,row){
          var _param = row;
          this.$confirm('此操作将删除['+row.domainName+']的'+row.year+'招生计划,是否继续?','提示',{
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(()=>{
            this.deleteRequest('/col/enroll',_param)
              .then(resp =>{
                if (resp && resp.status === 200){
                  this.initEnrollData();
                }
              });
          })

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
