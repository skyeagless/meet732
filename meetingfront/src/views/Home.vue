<template>
    <div>
        <el-row>
            <el-col :span="12">
                <div class="left">
                    <h4>新增申请</h4>
                    <div class="meetingForm">
                        <el-form ref="form" :model="form" label-width="100px">
                            <el-form-item label="申请人">
                                <el-input v-model="form.name" style="width: 150px"></el-input>
                            </el-form-item>
                            <el-form-item label="申请理由" style="width: 600px">
                                <el-input v-model="form.reason"></el-input>
                            </el-form-item>
                            <el-form-item>
                                <el-date-picker
                                        v-model="form.date"
                                        type="date"
                                        placeholder="选择日期"
                                        value-format="yyyy-MM-dd"
                                >
                                </el-date-picker>
                            </el-form-item>
                            <el-form-item>
                                <el-time-select
                                        placeholder="起始时间"
                                        v-model="form.startTime"
                                        :picker-options="{start:'09:00',step:'00:30',end:'16:30'}"
                                        value-format="HH:mm:ss"
                                >
                                </el-time-select>
                                <span> - </span>
                                <el-time-select
                                        placeholder="结束时间"
                                        v-model="form.endTime"
                                        :picker-options="{start:'9:30',step:'00:30',end:'17:00',minTime:form.startTime}"
                                        value-format="HH:mm:ss"
                                >
                                </el-time-select>
                            </el-form-item>
                            <el-form-item>
                                <el-button type="primary" @click="onSubmit">立即预定</el-button>
                                <el-button @click="clearAll">清空</el-button>
                            </el-form-item>
                        </el-form>
                    </div>
                </div>
            </el-col>
            <el-col :span="12">
                <div class="right">
                    <h4>当前已申请(保留最近的10个)</h4>
                    <el-table
                            :data="tableData"
                            style="width: 100%">
                        <el-table-column
                                prop="date"
                                label="申请日期"
                                width="180"
                        >
                        </el-table-column>
                        <el-table-column
                                prop="startTime"
                                label="起始时间"
                                width="180"
                        >
                        </el-table-column>
                        <el-table-column
                                prop="endTime"
                                label="结束时间"
                                width="180"
                        >
                        </el-table-column>
                        <el-table-column
                                prop="name"
                                label="申请人姓名"
                                width="90">
                        </el-table-column>
                        <el-table-column
                                prop="reason"
                                label="申请理由">
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
                </div>
            </el-col>
        </el-row>

    </div>

</template>

<script>



export default {
    name: 'Home',
    data(){
      return{
          form:{
              name:'',
              reason:'',
              date: '',
              startTime:'',
              endTime:''
          },
          tableData:[]
      }
    },
    mounted() {
        this.initMeeting();
    },
    methods:{
        onSubmit(){
            if(this.form.name === "" || this.form.reason === ""){
                this.$message({
                    message: '申请人/申请原因需要填写',
                    type: 'error'
                });
                this.clearAll();
                return;
            }
            this.postRequest("/meeting/create",this.form).then(res=>{
                if(res){
                    this.initMeeting();
                    this.$message({
                        message: '恭喜您,预定成功!',
                        type: 'success'
                    });
                    this.clearAll();
                }
            })
        },
        clearAll(){
            this.form = {}
        },
        initMeeting(){
            this.getRequest("/meeting/list/").then(res=>{
                if(res){
                    this.tableData = res;
                }
            })
        },
        handleDelete(index,data){
            this.$confirm('此操作将永久删除该预定，是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.deleteRequest("/meeting/del/"+data.id).then(resp=>{
                    if(resp){
                        this.$message({
                            type: 'success',
                            message: '删除成功'
                        });
                        this.initMeeting();
                    }
                });
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消删除'
                });
            });
        }
    }
}
</script>
