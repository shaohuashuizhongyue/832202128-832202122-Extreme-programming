<template>
    <div>
        <div class="panel panel-default">
            <div class="layui-inline tool-btn">
            </div>
            <div class="panel-heading">

                <h3 class="panel-title">收藏列表</h3>
                <hr>
                <div class="panel-btns">
                </div>
                <!--<button v-on:click="add()" class="btn  btn-primary  btn-round">
                    <span class="glyphicon glyphicon-plus"></span>
                    新增
                </button>-->
                &nbsp;
                <button v-on:click="list(1)" class="btn btn-success btn-round">
                    <i class="ace-icon fa fa-refresh"></i>
                    分页刷新
                </button>
            </div>

            <div class="panel-body">
                <div class="form-group">
                    <div class="col-sm-4">
                        <select v-model="keep0.ctype" class="form-control" placeholder="请选择类型名称">
                            <option value="">请选择类型</option>
                            <option value="重要">重要</option>
                            <option value="常用">常用</option>
                        </select>
                       <!-- <input type="text" placeholder="请输入类型名称" v-model="keep0.ctype" class="form-control" />-->
                    </div>
                    <button v-on:click="search(1)" class="btn  btn-primary  btn-round">
                        <span class="glyphicon glyphicon-search"></span>
                        搜索
                    </button>
                </div>
                <hr>
                <!--分页-->
                <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="5"></pagination>
                <div class="table-responsive"><!--table-primary table-info table-warning table-dark table-success-->
                    <table class="table table-success" id="table1">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>用户</th>
                            <th>联系人</th>
                            <th>类型</th>
                           <!-- <th>添加时间</th>-->
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr v-for="(keep1,index) in keeps"  :key="index" class="odd gradeX">
                            <td>{{index+1}}</td>
                            <td><span v-for="v in ualist.filter(t=>{return t.id==keep1.uid})">{{v.name}}</span></td>
                            <td><span v-for="v in galist.filter(t=>{return t.id==keep1.cid})">
                                {{v.name}}</span>
                            </td>
                            <td>
                                <span class="btn btn-sm btn-danger" v-if="keep1.ctype=='重要'">
                                    {{keep1.ctype}}
                                </span>
                                <span class="btn btn-sm btn-info" v-if="keep1.ctype=='常用'">
                                    {{keep1.ctype}}
                                </span>

                            </td>
                            <!--<td>{{keep1.pubtime}}</td>-->
                            <td>
                                <div class="btn-group"><!--hidden-sm hidden-xs-->
                                    <button class="btn btn-sm btn-info" @click="look(keep1);">
                                        <i class="ace-icon fa fa-pencil bigger-120"></i>查看
                                    </button>
                                    <button class="btn btn-sm btn-warning" @click="zy(keep1);"
                                            v-show="loginUser.utype=='管理员'||keep1.uid==loginUser.id" v-if="keep1.ctype=='常用'">
                                        <i class="ace-icon fa fa-pencil bigger-120"></i>重要
                                    </button>
                                    <button class="btn btn-sm btn-warning" @click="cy(keep1);"
                                            v-show="loginUser.utype=='管理员'||keep1.uid==loginUser.id" v-if="keep1.ctype=='重要'">
                                        <i class="ace-icon fa fa-pencil bigger-120"></i>常用
                                    </button>

                                    <button class="btn btn-sm btn-danger" @click="del(keep1.id)"
                                            v-show="loginUser.utype=='管理员'||keep1.uid==loginUser.id">
                                        <i class="ace-icon fa fa-trash-o bigger-120"></i>删除
                                    </button>
                                </div>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div><!-- table-responsive -->
            </div><!-- panel-body -->
        </div>

        <div id="form-modal-look" class="modal fade" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document" style="width: 85%;">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">查看</h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">姓名</label>
                                <div class="col-sm-10" style="margin-top: 10px" v-html="liaison.name">
                                    <input v-model="liaison.name" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">性别</label>
                                <div class="col-sm-10" style="margin-top: 10px" v-html="liaison.sex">
                                    <input v-model="liaison.sex" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">年龄</label>
                                <div class="col-sm-10" style="margin-top: 10px" v-html="liaison.age">
                                    <input v-model="liaison.age" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">电话号码</label>
                                <div class="col-sm-10" style="margin-top: 10px" v-html="liaison.tel">
                                    <input v-model="liaison.tel" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">电子邮件地址</label>
                                <div class="col-sm-10" style="margin-top: 10px" v-html="liaison.email">
                                    <input v-model="liaison.email" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">社交媒体账号</label>
                                <div class="col-sm-10" style="margin-top: 10px" v-html="liaison.maccount">
                                    <input v-model="liaison.maccount" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">物理地址</label>
                                <div class="col-sm-10" style="margin-top: 10px" v-html="liaison.address">
                                    <input v-model="liaison.address" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label "> 备注： </label>
                                <div class="col-sm-10" style="margin-top: 10px" v-html="liaison.note">
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->

    </div>
</template>

<script>


    import File from "@/components/file";
    import Pagination from "@/components/pagination";

    export default {
        components: {Pagination,File},
        name: "list",
        data: function() {
            return {
                keep: {},
                keep0: {},
                keep1: {},
                keeps: [],
                loginUser: {},
                imgUrl:'',
                ualist:[],
                galist:[],
                liaison: {},
                liaison1: {},


            }
        },
        mounted: function() {
            let _this = this;
            _this.imgUrl=_this.$imgurl;
            _this.$refs.pagination.size = 5;
            _this.loginUser= Tool.getLoginUser();
            if (_this.loginUser.id!=null) {
            }else {
                _this.$router.push("/login");
            }
            _this.keep0.ctype="";
            _this.commonAll();
            _this.list(1);

        },
        methods: {
            /**
             * 点击【新增】
             */
            add() {
                let _this = this;
                _this.$router.push("/keep/add");
            },
            /**
             * 点击【编辑】
             */
            edit(keep) {
                let _this = this;
                SessionStorage.set("KEEP",keep);
                _this.$router.push("/keep/update");
            },

            /**
             * 点击【查看】
             */
            look(v) {
                let _this = this;
                let liaison1="";
                _this.$ajax.post(_this.$javaurl + "/admin/liaison/getById/"+v.cid).then((response) => {
                    // Loading.hide();
                    liaison1 = response.data;
                    console.log("liaison1=",liaison1);
                    _this.liaison=$.extend({}, liaison1);
                    $("#form-modal-look").modal("show");
                });

            },

            /**
             * 列表查询
             */
            list(page) {
                let _this = this;
                let uid = '';
                if (_this.loginUser.utype != '管理员') {
                    uid = _this.loginUser.id;
                }
                _this.$ajax.post(_this.$javaurl+"/admin/keep/list"
                    +"?page="+page
                    +"&size="+_this.$refs.pagination.size
                    + "&uid=" + uid
                ).then((response)=>{
                    let resp = response.data;
                    _this.keeps=resp.list;
                    _this.$refs.pagination.render(page,resp.total);
                    console.log("条数：",resp.total);

                })
            },
            /*信息列表*/
            search(page){
                let _this=this;
                let uid = '';
                if (_this.loginUser.utype != '管理员') {
                    uid = _this.loginUser.id;
                }
                _this.$refs.pagination.size = 100;
                _this.$ajax.post(_this.$javaurl+"/admin/keep/search"
                    +"?page="+page
                    +"&size="+_this.$refs.pagination.size
                    +"&ctype="+_this.keep0.ctype
                    + "&uid=" + uid
                ).then((response)=>{
                    console.log("keep0");
                    _this.keeps=response.data.list;
                    _this.$refs.pagination.render(page,response.data.total);
                })
            },

            //重要
            zy(v){
                let _this = this;
                v.ctype="重要";
                _this.$ajax.post(_this.$javaurl + "/admin/keep/save", v).then((response) => {
                    // Loading.hide();
                    let resp = response.data;
                    console.log("data==" + resp);
                    if (resp == "success") {
                        Toast.success("操作成功！");
                        //alert("保存成功！");
                        _this.list(1);
                    } else {
                        Toast.error("操作失败！");
                    }
                })
            },

            //常用
            cy(v){
                let _this = this;
                v.ctype="常用";
                _this.$ajax.post(_this.$javaurl + "/admin/keep/save", v).then((response) => {
                    // Loading.hide();
                    let resp = response.data;
                    console.log("data==" + resp);
                    if (resp == "success") {
                        Toast.success("操作成功！");
                        //alert("保存成功！");
                        _this.list(1);
                    } else {
                        Toast.error("操作失败！");
                    }
                })
            },
            /**
             * 点击【删除】
             */
            del(id) {
                let _this = this;
                Confirm.show("删除收藏后不可恢复，确认删除？", function () {
                    _this.$ajax.delete(_this.$javaurl+"/admin/keep/delete/" + id).then((response)=>{
                        let resp = response.data;
                        if (resp=="success") {
                            _this.list(1);
                            Toast.success("删除成功！");
                        }
                    })
                });
            },
            commonAll() {
                let _this = this;
                _this.$ajax.post(_this.$javaurl + '/admin/common/all').then((response)=>{
                    let resp = response.data;
                    console.log(resp);
                    _this.ualist = resp.ualist;
                    _this.galist = resp.galist;
                    //_this.communitys = resp.communityList;
                })
            },
        },
        computed:{

        }
    }
</script>
