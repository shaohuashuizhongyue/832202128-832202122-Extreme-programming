<template>
    <div>
        <div class="panel panel-success">
            <div class="layui-inline tool-btn">
            </div>
            <div class="panel-heading">

                <h3 class="panel-title">用户列表</h3>
                <hr>
                <div class="panel-btns">
                </div>
                <button v-on:click="add()" class="btn  btn-primary  btn-round" >
                    <span class="glyphicon glyphicon-plus"></span>
                    新增
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
                        <input type="text" placeholder="请输入姓名" v-model="users.name" class="form-control"/>
                    </div>
                    <div class="col-sm-4">
                        <input type="text" placeholder="请输入用户名" v-model="users.uname" class="form-control"/>
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
                    <table class="table table-warning" id="table1">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>用户名</th>
                            <th>姓名</th>
                            <th>图片</th>
                            <th>性别</th>
                            <th>地址</th>
                            <th>密码</th>
                            <th>角色</th>
                            <th>电话</th>
                            <th>年龄</th>
                            <th>邮箱</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr v-for="(users,index) in userss" :key="index" class="odd gradeX">
                            <td>{{index+1}}</td>
                            <td>{{users.uname}}</td>
                            <td>{{users.name}}</td>
                            <td>
                                <img v-show="!users.img" class="media-object" :src="imgUrl+'zanwu.jpg'" width="50px;"
                                     height="50px;"/>
                                <img v-show="users.img" class="media-object" v-bind:src="imgUrl+users.img" width="50px;"
                                     height="50px;"/>
                            </td>
                            <td>{{users.sex}}</td>
                            <td>{{users.address}}</td>
                            <td>{{users.pwd}}</td>
                            <td>{{users.utype}}</td>
                            <td>{{users.tel}}</td>
                            <td>{{users.age}}</td>
                            <td>{{users.email}}</td>
                            <td>
                                <div class="btn-group"><!--hidden-sm hidden-xs-->
                                    <button class="btn btn-sm btn-info" @click="look(users);">
                                        <i class="ace-icon fa fa-pencil bigger-120"></i>查看
                                    </button>
                                    <button class="btn btn-sm btn-warning" @click="edit(users);"
                                            v-show="loginUser.utype=='管理员'">
                                        <i class="ace-icon fa fa-pencil bigger-120"></i>编辑
                                    </button>

                                    <button class="btn btn-sm btn-danger" @click="del(users.id)"
                                            v-show="loginUser.utype=='管理员'">
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
                                <label class="col-sm-2 control-label">用户名</label>
                                <div class="col-sm-10" v-html="users.uname">
                                    <input v-model="users.uname" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">姓名</label>
                                <div class="col-sm-10" v-html="users.name">
                                    <input v-model="users.name" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label "> 图片上传 </label>
                                <div class="col-sm-10">
                                    <div v-show="users.img" class="row">
                                        <div class="col-md-4">
                                            <img v-bind:src="imgUrl+users.img" class="img-responsive">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">性别</label>
                                <div class="col-sm-10" v-html="users.sex">
                                    <input v-model="users.sex" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">地址</label>
                                <div class="col-sm-10" v-html="users.address">
                                    <input v-model="users.address" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">密码</label>
                                <div class="col-sm-10" v-html="users.pwd">
                                    <input v-model="users.pwd" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">角色</label>
                                <div class="col-sm-10" v-html="users.utype">
                                    <input v-model="users.utype" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">电话</label>
                                <div class="col-sm-10" v-html="users.tel">
                                    <input v-model="users.tel" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">年龄</label>
                                <div class="col-sm-10" v-html="users.age">
                                    <input v-model="users.age" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">邮箱</label>
                                <div class="col-sm-10" v-html="users.email">
                                    <input v-model="users.email" class="form-control">
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
        components: {Pagination, File},
        name: "list",
        data: function () {
            return {
                users: {},
                userss: [],
                loginUser: {},
                imgUrl: '',

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
            _this.users.name='';
            _this.users.uname='';
            _this.commonAll();
            _this.list(1);


        },
        methods: {

            /**
             * 点击【新增】
             */
            add() {
                let _this = this;
                _this.$router.push("/users/add");
            },
            /**
             * 点击【编辑】
             */
            edit(users) {
                let _this = this;
                SessionStorage.set("USERS", users);
                _this.$router.push("/users/update");
            },
            /**
             * 点击【查看】
             */
            look(users) {
                let _this = this;
                _this.users = $.extend({}, users);
                $("#form-modal-look").modal("show");
            },

            /**
             * 列表查询
             */
            list(page) {
                let _this = this;
                _this.$ajax.post(_this.$javaurl + "/admin/users/list"
                    + "?page=" + page
                    + "&size=" + _this.$refs.pagination.size
                    +"&utype="+'普通用户'
                ).then((response) => {
                    let resp = response.data;
                    _this.userss = resp.list;
                    _this.$refs.pagination.render(page, resp.total);
                    console.log("条数：", resp.total);

                })
            },
            /*信息列表*/
            search(page) {
                console.log("newssearch：");
                let _this = this;
                _this.$refs.pagination.size = 100;
                _this.$ajax.post(_this.$javaurl + "/admin/users/search"
                    + "?page=" + page
                    + "&size=" + _this.$refs.pagination.size
                    + "&name=" + _this.users.name
                    + "&uname=" + _this.users.uname
                    +"&utype="+'普通用户'
                ).then((response) => {
                    console.log("users");
                    _this.userss = response.data.list;
                    _this.$refs.pagination.render(page, response.data.total);
                })
            },

            /**
             * 点击【删除】
             */
            del(id) {
                let _this = this;
                Confirm.show("删除用户后不可恢复，确认删除？", function () {
                    _this.$ajax.delete(_this.$javaurl + "/admin/users/delete/" + id).then((response) => {
                        let resp = response.data;
                        if (resp == "success") {
                            _this.list(1);
                            // alert("删除成功！")
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
                    //_this.communitys = resp.communityList;
                })
            },
        },
        computed: {
        }
    }
</script>
