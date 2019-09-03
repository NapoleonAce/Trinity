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
        @click= "addDomainDialogVisible = true"
      >添加专业</el-button>
      <el-dialog
        title="添加专业"
        :visible.sync="addDomainDialogVisible">
        <el-form :model="addDomainForm">
          <el-form-item label="专业名" :label-width=120>
            <el-input v-model="addDomainForm.domainName" auto-complete="off" disable="true"></el-input>
          </el-form-item>
          <el-form-item label="专业类别" :label-width=120>
            <el-input v-model="addDomainForm.domainType" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="专业描述" :label-width=120>
            <el-input v-model="addDomainForm.content"
                      type="textarea"
                      :autosize="{ minRows: 2, maxRows: 4}"
                      auto-complete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="addDomainDialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="handleAddDomain"  >添 加</el-button>
        </div>
      </el-dialog>

    </el-header>

    <el-main class="my-main">
      <el-table
        :data="domainData"
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
          label="详情"
          width="180">
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="handleWatchDomainInfo(scope.$index, scope.row)">查看</el-button>
            <el-dialog title="编辑信息" :visible.sync="scope.row.dialogInfoVisible">
              <el-form :model="editForm">
                <el-form-item label="专业 ID" :label-width=120>
                  <el-input v-model="editForm.domainId" auto-complete="off" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="专业名" :label-width=120>
                  <el-input v-model="editForm.domainName" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="专业类别" :label-width=120>
                  <el-input v-model="editForm.domainType" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="专业描述" :label-width=120>
                  <el-input v-model="editForm.content"
                            type="textarea"
                            :autosize="{ minRows: 2, maxRows: 4}"
                            auto-complete="off"></el-input>
                </el-form-item>

              </el-form>
              <div slot="footer" class="dialog-footer">
                <el-button @click="scope.row.dialogInfoVisible = false">取 消</el-button>
                <el-button type="primary" @click="handleUpdateDomain(scope.$index,scope.row)"  >保存</el-button>
              </div>
            </el-dialog>
          </template>
        </el-table-column>

        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="danger"
              @click="handleDeleteDomain(scope.$index, scope.row)">删除</el-button>
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
  export default {
    name: "colDomView",
    components:{LeftNav},
    data(){
      return {
        addDomainDialogVisible:false,
        addDomainForm:{
          domainId:1,
          domainName:'',
          domainType:'',
          collegeId:'',
          content:''
        },
        editForm:{
          domainId:1,
          domainName:'',
          domainType:'',
          collegeId:'',
          content:''
        },
        domainData: [
          {
            dialogInfoVisible:false,
            domainId:1,
            domainName:'',
            domainType:'',
            collegeId:'',
            content:''
          },
          {
            dialogInfoVisible:false,
            domainId:1,
            domainName:'',
            domainType:'',
            collegeId:'',
            content:''
          },
          {
            dialogInfoVisible:false,
            domainId:1,
            domainName:'',
            domainType:'',
            collegeId:'',
            content:''
          },
        ]
      }
    },
    mounted(){
      this.initDomainData();
    },
    methods:{
      initDomainData(){
        //暂定为浙江大学城市学院
        this.getRequest("/col/dom?collegeId="+4)
          .then(resp =>{
            if (resp && resp.status === 200){
              var data = resp.data.obj;
              console.log(data);
              var _domainData = [];
              for (var i=0;i<data.length;i++){
                _domainData[i] = {
                  dialogInfoVisible:false,
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
      handleAddDomain(){
        var _param = this.addDomainForm;
        //暂定为id2
        _param.collegeId = 4;
        this.postRequest('/col/dom',_param)
          .then(resp => {
            if (resp && resp.status === 200){
              this.initDomainData();
              this.addDomainForm = {
                domainId:1,
                domainName:'',
                domainType:'',
                collegeId:'',
                content:''
              }
            }
          });

        this.addDomainDialogVisible = false;
      },
      handleWatchDomainInfo(index,row){
        row.dialogInfoVisible = true;
        this.editForm = row;
      },
      handleUpdateDomain(index,row){
        var _param = this.editForm;
        this.putRequest('/col/dom',_param)
          .then(resp =>{
            if (resp && resp.status === 200){
              this.initDomainData();
            }
          });
        row.dialogInfoVisible = false;
      },
      handleDeleteDomain(index,row){
        this.$confirm('此操作将永久删除['+row.domainName+'],是否继续?','提示',{
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(()=>{
          var _param = {
            domainId:row.domainId,
            domainName:row.domainName,
            domainType:row.domainType,
            collegeId:row.collegeId,
            content:row.content
          }
          this.deleteRequest('/col/dom',_param)
            .then(resp =>{
              if (resp && resp.status === 200){
                this.initDomainData();
              }
            })
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
