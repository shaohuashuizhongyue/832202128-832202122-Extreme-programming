<template>
    <div>
        <div class="panel panel-default">
            <div class="layui-inline tool-btn">
            </div>
            <div class="panel-heading">

                <h3 class="panel-title">联系人列表</h3>
                <hr>
                <div class="panel-btns">
                </div>
                <button v-on:click="add()" class="btn  btn-primary  btn-round">
                    <span class="glyphicon glyphicon-plus"></span>
                    新增
                </button>
                &nbsp;
                <button v-on:click="imp()" class="btn  btn-primary  btn-round">
                    <span class="glyphicon glyphicon-plus"></span>
                    导入
                </button>
                &nbsp;
                <button v-on:click="expo()" class="btn  btn-primary  btn-round">
                    <span class="glyphicon glyphicon-plus"></span>
                    导出
                </button>
                &nbsp;
                <button v-on:click="list(1)" class="btn btn-success btn-round">
                    <i class="ace-icon fa fa-refresh"></i>
                    分页刷新
                </button>
            </div>

            <div class="panel-body">
                <div class="form-group">
                    <div class="col-sm-4">
                        <input type="text" placeholder="请输入名称" v-model="liaison0.name" class="form-control"/>
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
                            <th>姓名</th>
                            <th>性别</th>
                            <th>年龄</th>
                            <th>用户</th>
                            <th>电话号码</th>
                            <th>电子邮件地址</th>
                            <th>社交媒体账号</th>
                            <th>物理地址</th>
                            <th>添加时间</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr v-for="(liaison1,index) in liaisons" :key="index" class="odd gradeX">
                            <td>{{index+1}}</td>
                            <td>{{liaison1.name}}</td>
                            <td>{{liaison1.sex}}</td>
                            <td>{{liaison1.age}}</td>
                            <td><span v-for="v in ualist.filter(t=>{return t.id==liaison1.uid})">{{v.name}}</span></td>
                            <td>{{liaison1.tel}}</td>
                            <td>{{liaison1.email}}</td>
                            <td>{{liaison1.maccount}}</td>
                            <td>{{liaison1.address}}</td>
                            <td>{{liaison1.pubtime}}</td>
                            <td>
                                <div class="btn-group"><!--hidden-sm hidden-xs-->
                                    <button class="btn btn-sm btn-info" @click="look(liaison1);">
                                        <i class="ace-icon fa fa-pencil bigger-120"></i>查看
                                    </button>
                                    <button class="btn btn-sm btn-warning" @click="edit(liaison1);"
                                            v-show="loginUser.utype=='管理员'||liaison1.uid==loginUser.id">
                                        <i class="ace-icon fa fa-pencil bigger-120"></i>编辑
                                    </button>

                                    <button class="btn btn-sm btn-warning" @click="sc(liaison1);"
                                            v-show="loginUser.utype=='管理员'||liaison1.uid==loginUser.id">
                                        <i class="ace-icon fa fa-pencil bigger-120"></i>收藏
                                    </button>

                                    <button class="btn btn-sm btn-danger" @click="del(liaison1.id)"
                                            v-show="loginUser.utype=='管理员'||liaison1.uid==loginUser.id">
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




        <div id="form-modal-imp" class="modal fade" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document" style="width: 85%;">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">导入</h4>
                    </div>
                    <div class="modal-body">

                        <form class="form-horizontal" enctype="multipart/form-data">
                            <div class="form-group">
                                <label class="col-sm-2 control-label " > 文件上传 </label>
                                <div class="col-sm-10">
                                    <input type="file" ref="wj" class="form-control" @change="handleFileChange">
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                <button @click="uploadFile" type="button" class="btn btn-primary">上传文件</button>
                            </div>
                        </form>


                    </div>
                    <div class="modal-footer">
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->

        <!--<div class="modal-body">
            <form class="form-horizontal" enctype="multipart/form-data">
                <div class="form-group">
                    <label class="col-sm-2 control-label " > 文件上传 </label>
                    <div class="col-sm-10">
                        <input type="file" ref="wj" class="form-control">
                    </div>
                </div>
            </form>
            <div class="modal-footer">
                <button v-on:click="impUser()" type="button" class="btn btn-primary">保存</button>
            </div>
        </div>-->


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
    // 引入 xlsx 库
    import * as XLSX from 'xlsx';

    export default {
        components: {Pagination, File},
        name: "list",
        data: function () {
            return {
                liaison: {},
                liaison0: {},
                liaison1: {},
                liaison2: {},
                liaisons: [],
                loginUser: {},
                imgUrl: '',
                ualist: [],
                keep:{},
                selectedFile: null,


            }
        },
        mounted: function () {
            let _this = this;
            _this.imgUrl = _this.$imgurl;
            _this.$refs.pagination.size = 5;
            _this.loginUser = Tool.getLoginUser();
            if (_this.loginUser.id != null) {
            } else {
                _this.$router.push("/login");
            }
            _this.commonAll();
            _this.list(1);
            _this.liaison0.name="";

        },
        methods: {
            /**
             * 点击【新增】
             */
            add() {
                let _this = this;
                _this.$router.push("/liaison/add");
            },
            imp() {
                let _this = this;
                $("#form-modal-imp").modal("show");
            },


            //导入
            handleFileChange(event) {
                this.selectedFile = event.target.files[0];
            },
            async uploadFile() {
                let _this = this;
                if (!this.selectedFile) {
                    alert('请选择一个文件!');
                    return;
                }
                const formData = new FormData();
                formData.append('file', this.selectedFile);
                try {
                    const response = await  _this.$ajax.post(_this.$javaurl + '/admin/liaison/upload', formData, {
                        headers: {
                            'Content-Type': 'multipart/form-data'
                        }
                    });
                    Toast.success("文件上传成功！");
                    $("#form-modal-imp").modal("hide");
                    _this.list(1);
                    /*if (response.message=="文件上传并解析成功！"){
                        Toast.success("文件上传成功！");
                        $("#form-modal-imp").modal("hide");
                        _this.list(1);
                    }else {
                        alert("数据不符合要求，文件上传失败！请调整后重新上传！");
                    }*/


                } catch (error) {
                    console.error('文件上传失败', error);
                }
            },

            expo() {
                // 创建工作表
                const ws = XLSX.utils.json_to_sheet(this.liaisons);
                // 创建工作簿
                const wb = XLSX.utils.book_new();
                XLSX.utils.book_append_sheet(wb, ws, 'Sheet1');
                // 导出 Excel 文件
                XLSX.writeFile(wb, '导出的数据.xlsx');
            },
            /**
             * 点击【编辑】
             */
            edit(liaison) {
                let _this = this;
                SessionStorage.set("LIAISON", liaison);
                _this.$router.push("/liaison/update");
            },
            /**
             * 点击【查看】
             */
            look(liaison) {
                let _this = this;
                _this.liaison = $.extend({}, liaison);
                $("#form-modal-look").modal("show");
            },

            //收藏
            sc(v){
                let _this = this;
                _this.keep.uid=_this.loginUser.id;
                _this.keep.cid=v.id;
                _this.keep.ctype="常用";
                _this.$ajax.post(_this.$javaurl + "/admin/keep/save", _this.keep).then((response) => {
                    // Loading.hide();
                    let resp = response.data;
                    console.log("data==" + resp);
                    if (resp == "success") {
                        Toast.success("保存成功！");
                        //alert("保存成功！");
                        _this.$router.push("/keep");
                    } else {
                        Toast.error("保存失败！");
                    }
                })
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
                _this.$ajax.post(_this.$javaurl + "/admin/liaison/list"
                    + "?page=" + page
                    + "&size=" + _this.$refs.pagination.size
                    + "&uid=" + uid
                ).then((response) => {
                    let resp = response.data;
                    _this.liaisons = resp.list;
                    _this.$refs.pagination.render(page, resp.total);
                    console.log("条数：", resp.total);

                })
            },
            /*信息列表*/
            search(page) {
                let _this = this;
                let uid = '';
                if (_this.loginUser.utype != '管理员') {
                    uid = _this.loginUser.id;
                }
                _this.$refs.pagination.size = 100;
                _this.$ajax.post(_this.$javaurl + "/admin/liaison/search"
                    + "?page=" + page
                    + "&size=" + _this.$refs.pagination.size
                    + "&name=" + _this.liaison0.name
                    + "&uid=" + uid
                ).then((response) => {
                    console.log("liaison0");
                    _this.liaisons = response.data.list;
                    _this.$refs.pagination.render(page, response.data.total);
                })
            },

            /**
             * 点击【删除】
             */
            del(id) {
                let _this = this;
                Confirm.show("删除联系人后不可恢复，确认删除？", function () {
                    _this.$ajax.delete(_this.$javaurl + "/admin/liaison/delete/" + id).then((response) => {
                        let resp = response.data;
                        if (resp == "success") {
                            _this.list(1);
                            Toast.success("删除成功！");
                        }
                    })
                });
            },
            commonAll() {
                let _this = this;
                _this.$ajax.post(_this.$javaurl + '/admin/common/all').then((response) => {
                    let resp = response.data;
                    console.log(resp);
                    _this.ualist = resp.ualist;

                    //_this.communitys = resp.communityList;
                })
            },
        },
        computed: {}
    }
</script>
