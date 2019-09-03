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
        v-for="item in colData"
        :key="item.collegeId"
        class="col-card">
      <div slot="header" class="clearfix">
        <span>{{item.collegeName}}</span>
        <span style="float: right; padding: 3px 0;font-size: 12px"> {{item.province}} {{item.city}}</span>


      </div>
        <div style="margin-bottom: 20px">
          <el-button
            @click="getEnrollPage(item.collegeId)"
            type="text">
            招生计划
          </el-button>
        </div>
        <el-collapse @change="handleChange">
          <el-collapse-item title="报名时间" name="1">
            <el-tag style="margin: 0px 10px 0px 10px"
                    type="primary">{{item.applyInfo.applyBegin}}</el-tag>
            -
            <el-tag style="margin: 0px 10px 0px 10px" type="danger">{{item.applyInfo.applyFinish}}</el-tag>
          </el-collapse-item>
          <el-collapse-item title="报名条件" name="2">
            <div> {{item.applyInfo.applyCondition}}</div>
          </el-collapse-item>
          <el-collapse-item title="报名方式" name="3">
            <div> {{item.applyInfo.applyWay}}</div>
          </el-collapse-item>
          <el-collapse-item title="学校简介" name="4">
            <div>{{item.content}} </div>
          </el-collapse-item>
        </el-collapse>


    </el-Card>

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
        searchKey:'',
        colData: [
          {
            dialogInfoVisible: false,
            collegeId: 1,
            collegeName: '浙江大学城市学院',
            content: '浙江大学城市学院具体介绍',
            province: '浙江',
            city: '杭州',
            applyInfo: {
              collegeId: 1,
              applyInfoId: 2,
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
          },
          {
            dialogInfoVisible: false,
            collegeId: 2,
            collegeName: '浙江工业大学',
            content: '浙江工业大学介绍',
            province: '浙江',
            city: '杭州',
            applyInfo: {
              collegeId: 2,
              applyInfoId: 3,
              applyWay: '1.网上报名：符合报名条件的学生请登录浙江工业大学本科招生网（http：//zs.zjut.edu.cn）网上报名专栏，进入“‘三位一体’综合评价招生报名系统”，根据网上提示的报名流程办理报名相关手续。\n' +
                '\n' +
                '2.符合自行申请（报名条件（一）类），同时符合具有突出特殊才能和卓越表现（报名条件（二）类）的考生，可以兼报。\n' +
                '\n' +
                '3. 书面材料在线提交：以下材料要求扫描（拍照）后上传报名系统，上传文件要清晰可见，单个文件大小不超过3M。\n' +
                '\n' +
                '①《浙江工业大学“三位一体”综合评价招生申请表》要求在线填写、提交并打印。纸质申请表填写的内容必须经中学教务处审核认定，并加盖中学公章，考生本人签名后将盖章签名的申请表扫描（拍照）上传。\n' +
                '\n' +
                '②高中阶段获得的符合报名条件中所列举的获奖证书或证明材料，并扫描（拍照）上传。\n' +
                '\n' +
                '③个人陈述，须由本人亲笔手写，内容包括高中阶段成长经历及表现（获省级（含）以上学科竞赛奖励情况）、参与科学研究、科技创新、文学创作、文艺活动情况，参与社会实践、社团活动、志愿服务情况，报考理由、对报考专业认识及对个人规划与设想等，字数控制在1000字左右，并扫描（拍照）上传。\n' +
                '\n' +
                '④符合具有突出特殊才能和卓越表现（报名条件（二）类）的考生需提供《浙江工业大学“三位一体”综合评价招生突出特殊才能考生中学审核表》，有中学推荐或者专家推荐的考生需同时附《浙江工业大学“三位一体”综合评价招生突出特殊才能考生推荐表》（表格可至我校“‘三位一体’综合评价招生报名系统”下载），并扫描（拍照）上传。\n' +
                '\n' +
                '4.材料装订：将书面申请材料按上述顺序装订成册。A4纸标准大小，装订时将申请表作为材料首页，不需另制封面（具体要求以“三位一体综合评价招生报名系统”的通知为准）。\n' +
                '\n' +
                '5.材料邮寄：浙江省杭州市下城区潮王路18号浙江工业大学招生办公室，联系电话：0571-88320032，邮编：310032。请考生在邮寄申请材料时使用中国邮政EMS特快专递。申请材料届时不予退还，请考生自行留好备份。',
              applyCondition: '一）具有浙江省2019年普通高等学校招生统一考试报名资格，综合素质评价均为B等（含）以上，新高考改革前的往届生综合素质评价均为P等（含）以上，符合以下条件之一的考生均可申请报考：\n' +
                '\n' +
                '1.学业水平考试6门（含）以上科目为A等，其余为C等（含）以上。\n' +
                '\n' +
                '2.学业水平考试5门（含）以上科目为A等，其余为C等（含）以上，且符合以下专项条件之一者：\n' +
                '\n' +
                '    （1）学科竞赛类：高中阶段在国际科学与工程大奖赛或国际环境科研项目奥林匹克竞赛中获奖；或在全国中学生学科奥林匹克竞赛（包括全国高中数学联赛、全国中学生物理竞赛、中国化学奥林匹克竞赛、全国青少年信息学奥林匹克联赛、全国中学生生物学联赛）中获得省级赛区竞赛三等奖（含）以上；或在浙江省高中数学竞赛、浙江省高中学生化学竞赛、浙江省高中生物学竞赛、浙江省中学生物理竞赛中获得A组省二等奖（含）以上。\n' +
                '\n' +
                '（2）科技创新类：高中阶段以第一作者在全国青少年科技创新大赛（含全国青少年生物和环境科学实践活动）或全国中小学电脑制作活动中获得省级三等奖（含）以上；或在全国“明天小小科学家”活动中获得三等奖（含）以上。\n' +
                '\n' +
                '（3）语言文学特长类：高中阶段以第一作者正式出版文学专著或在全国性作文比赛（包括“叶圣陶杯”全国中学生新作文大赛决赛、全国新概念作文大赛、全国中小学生创新作文大赛、“语文报杯”全国中学生作文大赛、中国中学生作文大赛）中获得三等奖（含）以上；或在全国创新英语大赛中获得三等奖（含）以上；或在全国中学生英语能力竞赛中获得二等奖（含）以上。\n' +
                '\n' +
                '（4）艺术特长类：高中阶段在全国中小学艺术展演及浙江省教育厅、浙江省文化厅主办的中学生艺术赛事中获得三等奖（含）以上（限个人项目）；或中学阶段（含初中）获得浙江省学生艺术特长水平测试A级证书。\n' +
                '\n' +
                '（5）体育特长类（限田径、游泳、乒乓球、羽毛球、网球、定向、健美操、武术，限个人项目）：高中阶段在浙江省中学生运动会、浙江省中学生田径运动会上获得前八名；或在国家教育部、国家体育总局举办的中学生体育赛事中进入决赛。\n' +
                '\n' +
                '（二）具有浙江省2019年普通高等学校招生统一考试报名资格，综合素质评价均为B等（含）以上，新高考改革前的往届生综合素质评价均为P等（含）以上，在学科研究、科技创新、艺术体育和社会实践等方面具有突出特殊才能和卓越表现的学生，可以申请报考（学生自荐、所在中学推荐或专家推荐均可）。自荐或专家推荐的考生须经所在中学审核，考生名单由各中学按规定在校内进行公示。',
              applyBegin: '2019-02-27 10:00:00',
              applyFinish: '2019-03-14 16:00:00'
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
        this.getRequest('/col/home')
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

      },
      watchEnroll(){

      },
      getEnrollPage(colId){
        this.$store.commit('setWatchColId',colId);
        this.$router.push('/colView');
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
