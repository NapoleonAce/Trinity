<template>
  <el-container class="main-container" style="height: 100%;">
    <el-aside style="width: 300px">
      <LeftNav></LeftNav>
    </el-aside>
    <el-header class="my-header" >
      <el-input
        v-model="searchKey"
        class="my-header-search"
        placeholder="请输入院校关键字"
        prefix-icon="el-icon-search">
        <el-button
          @click="searchColByKey()"
          slot="append"
          icon="el-icon-search"></el-button>
      </el-input>
    </el-header>
    <el-main class="my-main">
      <el-Card
        @click=""
        v-for="item in recData"
        :key="item.domainId"
        class="col-card">
        <div slot="header" class="clearfix">
          <span style="font-size: 20px">{{item.domainName}}</span>
          <el-button
            type="text"
            style="float: right; padding: 3px 0;font-size: 15px;">
            {{item.college.collegeName}}
          </el-button>
<!--          <el-button-->
<!--            style="float: right; padding: 3px 0;"-->
<!--            type="text">-->
<!--            学校详情-->
<!--          </el-button>-->
        </div>
<!--        <div class="card-search-enroll">-->
<!--          <el-button-->
<!--            @click="getEnrollPage(item.collegeId)"-->
<!--            size="medium"-->
<!--            type="text">招生计划查询</el-button>-->
<!--        </div>-->
        <div >
          <ul>
            <li>院校地区:&emsp;{{item.college.province}} {{item.college.city}}</li>
            <li>报名时间:
<!--              <el-tag style="margin: 0px 10px 0px 10px"-->
<!--                      type="primary">-->
                &emsp;{{item.college.applyInfo.applyBegin}}
<!--              </el-tag>-->
              -
<!--              <el-tag style="margin: 0px 10px 0px 10px"-->
<!--                      type="danger">-->
                {{item.college.applyInfo.applyFinish}}
<!--              </el-tag>-->
            </li>
            <li>所属类别:&emsp;{{item.domainType}}</li>
            <li>计划数量:&emsp;{{item.number}}</li>
            <li>选考要求:&emsp;{{item.subjectReq}}</li>
            <li>推荐理由:</li>
            <li>
                &emsp;{{item.reason}}
            </li>
          </ul>

        </div>



      </el-Card>

    </el-main>


  </el-container>
</template>

<script>
  import LeftNav from "@/components/leftNav";
  // import baseComponent from "@/components/baseComponent"
  import Utils from "@/utils/Utils"
  export default {
    name: "recoView",
    components:{LeftNav},
    data(){
      return {
        searchKey:'',
        recData: [
          {
            dialogInfoVisible: false,
            studentId:'11',
            domainId:'',
            reason:'啊咧，我也想想不通是为什么呢',
            domainName:'母猪产后护理',
            domainType:'农业',
            content:'专业详细介绍',
            number:10,
            price:5000.0,
            subjectReq:'物理历史地理',
            collegeId:0,
            college:{
              collegeName:'浙江大学',
              province:'浙江',
              city:'杭州',
              content:'学校描述',
              applyInfo:{
                applyInfo:0,
                applyWay: '第八条  符合报名条件的学生登录浙江大学城市学院招生信息网，点击“浙江大学城市学院‘三位一体’综合评价招生”进入报名系统，根据网上提示的报考进度依次办理报名相关手续。考生限报1个报考类别。\n' +
                  '\n' +
                  '第九条  报名无需邮寄纸质申请材料。考生须在报名系统中提交以下材料：申请表（考生所在中学盖章）、本人身份证（正反面）、获奖证书及相关证明材料（特长生）。材料不全、未按要求上传或图片模糊不清的，均视为放弃报名。',
                applyCondition: '已经参加2019年浙江省普通高校招生考试报名，学业水平测试（以下简称高中学考）各科目成绩合格，综合素质评价达到B等及以上（新高考改革前的往届生P等及以上），且符合以下条件之一者，均可申请报考。\n' +
                  '\n' +
                  '（一）高中学考折算成绩达到60分及以上。折算方法：A等计10分（其中语文、数学、外语三门主要科目计15分），B等计8分，C等计4分，D等不计分。超过100分的按照100分计。\n' +
                  '\n' +
                  '（二）高中学考折算成绩达到40分及以上，且符合以下条件之一者（均限高中阶段获得）：\n' +
                  '\n' +
                  '1.艺术特长类：参加北京大学全国中学生艺术周、清华大学全国中学生文化艺术冬令营音乐、舞蹈、戏剧类艺术测试并获二级（含）以上证书；或参加浙江省教育厅组织的声乐、器乐、舞蹈类艺术特长测试并获得A级证书(西洋管乐器可放宽至B级证书)。\n' +
                  '\n' +
                  '2.体育特长类：获篮球、排球、足球、乒乓球、羽毛球、网球、武术、跆拳道、田径、健美操项目国家二级（含）以上运动员证书；或参加由地市级（含）以上教育主管部门或体育主管部门主办的体育比赛并获上述项目前三名（包括团体或个人）',
                applyBegin: "2019-02-22 09:00:00",
                applyFinish: "2019-03-04 16:00:00"
              }
            }
          },
        ],

      }
    },
    mounted(){
      this.initData();
    },
    methods:{
      initData(){
        var studentId = '3305';
        this.getRequest('/rec/allString?studentId='+studentId)
          .then(resp =>{
            if (resp && resp.status === 200){
              console.log(resp);
              var data = resp.data;
              this.recData = data;
              // var data = resp.data.obj;
              // var _colData = [];
              // for (var i=0;i<data.length;i++){
              //   _colData[i] = {
              //     dialogInfoVisible: false,
              //     collegeId: data[i].collegeId,
              //     collegeName: data[i].collegeName,
              //     content:data[i].content,
              //     province:data[i].province,
              //     city: data[i].city,
              //     applyInfo:data[i].applyInfo
              //   }
              // }
              // this.colData = _colData;
            }
          })

      },
      handleWatchDomainInfo(index,row){

      },
      handleUpdateDomain(index,row){

      },
      watchEnroll(){

      },
      getEnrollPage(colId){

      },
      searchColByKey(){
        var key = this.searchKey;
        this.getRequest('/col/search?key='+key)
          .then(resp =>{
            if (resp && resp.status === 200){
              var data = resp.data.obj;
              var _colData = [];
              for (var i=0;i<data.length;i++){
                _colData[i] = {
                  dialogInfoVisible: false,
                  collegeId: data[i].collegeId,
                  collegeName: data[i].collegeName,
                  content:data[i].content,
                  province:data[i].province,
                  city: data[i].city,
                  applyInfo:data[i].applyInfo
                }
              }
              this.colData = _colData;
            }
          })

      }



    }
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
  .card-search-enroll{
    display:inline-block;
    margin-bottom: 20px;
    font-size: 16px;
    width: 100%;
  }
  li{
    list-style: none;
    padding-bottom: 20px;
  }
  /*.card-search-enroll:hover{*/
  /*  background-color: #AAAAAA;*/
  /*}*/
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
  /*.my-button{*/
  /*  margin-right: 50px;*/
  /*}*/
  .my-main{
    display: inline-block;
    margin-left: 300px;
    margin-top: 2%;
  }
  .col-card{
    width:60%;
    margin-bottom: 20px;

  }

</style>
