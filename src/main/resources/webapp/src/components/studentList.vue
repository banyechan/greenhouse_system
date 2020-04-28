<template>
    <div>
        <div>
            姓名：<Input v-model="student.name" placeholder="请输入姓名" clearable style="width: 100px" />
            年龄：<Input v-model="student.age" placeholder="请输入年龄" clearable style="width: 80px" />
            性别：<Select v-model="student.sex" style="width:80px">
                    <Option value="">全部</Option>
                    <Option value="man">男</Option>
                    <Option value="girl">女</Option>
                </Select>
            <Button type="primary" shape="circle" icon="ios-search" @click="serch()">搜索</Button>
            <Button type="success" @click="addModel = true" style="margin-left:100px">添加</Button>
        </div>
        
        <Table stripe :columns="columns1" :data="studentList"> </Table>

         <!-- 模态框用的是iview样式组件的模态框 -->
        <Modal title="新增信息" v-model="addModel"  width="500" @on-ok="submit()" @on-cancel="cancel" >
            <div>
                姓名：<Input v-model="student.name" placeholder="请输入姓名" style="width: 300px" />
            </div>
            <div>
                年龄：<Input v-model="student.age" placeholder="请输入年龄" style="width: 300px" />
            </div>
            <div>
                性别：<RadioGroup v-model="student.sex">
                        <Radio label="man">男</Radio>
                        <Radio label="girl">女</Radio>
                    </RadioGroup>
            </div>
        </Modal>
    </div>
</template>

<script>
    export default {
        data () {
            return {
                addModel: false,
                student:{
                    name : null,
                    age : null,
                    sex : null
                },
                columns1: [
                    {
                        title: '姓名',
                        key: 'name'
                    },
                    {
                        title: '年龄',
                        key: 'age'
                    },
                     {
                        title: '性别',
                        key: 'sex'
                    },
                    {
                        title: '地址',
                        key: 'address'
                    },
                    {
                        title: '操作',
                        key: 'action',
                        width: 150,
                        align: 'center',
                        render: (h, params) => {
                            return h('div', [
                                h('Button', {
                                    props: {
                                        type: 'primary',
                                        size: 'small'
                                    },
                                    style: {
                                        marginRight: '5px'
                                    },
                                    on: {
                                        click: () => {
                                            this.show(params.index)
                                        }
                                    }
                                }, 'View'),
                                h('Button', {
                                    props: {
                                        type: 'error',
                                        size: 'small'
                                    },
                                    on: {
                                        click: () => {
                                            this.remove(params.index)
                                        }
                                    }
                                }, 'Delete')
                            ]);
                        }
                    }
                ],
                studentList: [
                    // {
                    //     name: 'John Brown',
                    //     age: 18,
                    //     sex: 'man',
                    //     address: 'New York No. 1 Lake Park',
                    //     date: '2016-10-03'
                    // },
                    // {
                    //     name: 'Jon Snow',
                    //     age: 26,
                    //     sex: 'girl',
                    //     address: 'Ottawa No. 2 Lake Park',
                    //     date: '2016-10-04'
                    // }
                ]
            }
        },                                                
        mounted(){
            this.getList();
        },
        methods:{
            getList(){
                this.axios.post('http://localhost:2020/student/list',{
                    sex: null,
                    name: null
                }).then(res => {
                    let{status,data} = res;
                    if(status === 200){
                        this.studentList = data;
                        console.log("------ 返回状态值为200-------- ");
                    }else{
                         console.log("------ 返回状态值非200-------- ");
                    }
                    
                }).catch(function(res){
                    console.log(res);
                });
            },
            serch(){
                let params = {
                    name: this.student.name,
                    age: this.student.age,
                    sex: this.student.sex
                }
                 this.axios.post('http://localhost:2020/student/list', params).then(res => {
                    let{status,data} = res;
                    if(status === 200){
                        this.studentList = data;
                    }else{
                         console.log("------ 返回状态值非200-------- ");
                    }
                    
                }).catch(function(res){
                    console.log(res);
                });
            },
             show (index) {
                this.$Modal.info({
                    title: '详细信息',
                    content: `Name：${this.studentList[index].name}<br>Age：${this.studentList[index].age}<br>Address：${this.studentList[index].address}`
                })
            },
            remove (index) {
                //this.studentList.splice(index, 1);
                console.log("------ 删除对象的index=" + index);
                let id = this.studentList[index].id;
                 console.log("------ 删除对象的id=" + id);
                let url = "http://localhost:2020/student/del?id=" + id;
                this.axios.delete(url).then(res => {
                    let{status,data} = res;
                    if(status === 200){
                        console.log("------ 删除成功-------- ");
                        this.studentList.splice(index, 1);
                        this.$Message.info('删除成功');
                    }else{
                         console.log("------ 删除失败-------- ");
                    }
                    
                }).catch(function(res){
                    console.log(res);
                });
            },
            submit(){
                console.log("------ 点击提交按钮-------- ");
                let params = {
                    name: this.student.name,
                    age: this.student.age,
                    sex: this.student.sex
                }
                 this.axios.post('http://localhost:2020/student/add', params).then(res => {
                    let{status,data} = res;
                    if(status === 200){
                        this.getList();
                        this.$Message.info('添加成功');
                    }else{
                        this.$Message.info('添加失败');
                    }
                    
                }).catch(function(res){
                    console.log(res);
                });
            },
            cancel () {
                this.$Message.info('Clicked cancel');
            }
        }
    }
</script>