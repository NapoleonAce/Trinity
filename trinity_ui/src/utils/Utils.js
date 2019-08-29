//将学科的id和名字对应起来

var Utils = {
    subjects: [
      '语文',
      '数学',
      '英语',
      '物理',
      '化学',
      '生物',
      '技术',
      '政治',
      '历史',
      '地理'
    ],
    roles:[
      '管理员',
      '院校',
      '学生'
    ],
  transformRoleNameToId:function (roleName) {
    for(var i=0;i<this.roles.length;i++){
      if (this.roles[i] === roleName){
        return i + 1;
      }
    }
  },
  transformIdToRoleName:function (roleId) {
    return this.roles[roleId - 1]
  },
  transformIdToSubName:function (id) {
    return this.subjects[id - 1]
  },
  transformSubNameToId:function (name) {
    for(var i=0;i<this.subjects.length;i++){
      if (this.subjects[i] === name){
        return i + 1;
      }
    }
  }
}
export default Utils

