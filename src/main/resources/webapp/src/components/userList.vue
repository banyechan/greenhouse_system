<template>
  <div>
    <h1>欢迎来到第一个页面</h1>
    <p>六王毕，四海一；蜀山兀，阿房出。覆压三百余里，隔离天日。骊山北构而西折，直走咸阳。二川溶溶，流入宫墙。</p>
    <p>五步一楼，十步一阁；廊腰缦回，檐牙高啄；各抱地势，钩心斗角。盘盘焉，囷囷焉，蜂房水涡，矗不知其几千万落！</p>
    <p>长桥卧波，未云何龙？复道行空，不霁何虹？高低冥迷，不知西东。歌台暖响，春光融融；舞殿冷袖，风雨凄凄。</p>

    <div class="mainContent">
      <label>用户列表</label>
      <table>
        <thead>
          <tr>
            <td>编号</td>
            <td>姓名</td>
            <td>年龄</td>
            <td>操作</td>
          </tr>
        </thead>
        <tbody v-for="model in userList" :key="model.id">
          <tr>
            <td>{{model.id}}</td>
            <td>{{model.name}}</td>
            <td>{{model.age}}</td>
            <td v-on:click="getInfo(model)">查看详情</td>
          </tr>
        </tbody>
      </table>
    </div>

  </div>
</template>

<script  type="text/javascript">
export default {
  name: 'userList',
  data () {
    return {
      userList: [ ]
    }
  },
  created () {
    this.getList()
  },
  methods: {
    getList: function () {
        this.axios.post('http://localhost:2020/student/list',{
            sex: 'man'
        }).then(function(res){
            let{status,data} = res;
            if(status === 200){
                this.userList = data
                console.log("------ 返回状态值为200-------- ");
            }else{
                    console.log("------ 返回状态值非200-------- ");
            }
            
        }).catch(function(res){
            console.log(res);
        });

    },
    getInfo: function (e) {
      console.info('start vueUserInfo' + e.userId)
      this.$router.push({path: '/info', query: {userId: e.userId}})
      console.info('end vueUserInfo' + e.userId)
    }
  }
}
</script>

<style>
  .mainContent {
      background-color: burlywood;
      font-size: 30px;
  }
</style>
