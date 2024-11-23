<template>
    <div>
        <div class="contentpanel">
            <div class="row">
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <form class="form-horizontal"><!--role="form"-->
                        <div class="panel panel-success">
                            <div class="panel-heading">
                                <h4 class="panel-title">修改用户</h4>
                                <p></p>
                            </div>
                            <div class="panel-body">
                                <div class="space-4"><input hidden v-model="users.id"/></div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"> 用户名 </label>
                                    <div class="col-sm-9">
                                        <input type="text" placeholder="请输入用户名" class="form-control col-xs-10 col-sm-5"
                                               v-model="users.uname" disabled/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"> 姓名 </label>
                                    <div class="col-sm-9">
                                        <input type="text" placeholder="请输入姓名" class="form-control col-xs-10 col-sm-5"
                                               v-model="users.name"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"> 图片上传 </label>
                                    <div class="col-sm-9">
                                        <file v-bind:input-id="'image-upload'"
                                              v-bind:text="'图片上传'"
                                              v-bind:suffixs="['jpg', 'jpeg', 'png','gif','webp']"
                                              v-bind:after-upload="afterUpload"></file>
                                        <div v-show="users.img" class="row">
                                            <div class="col-md-4">
                                                <img v-bind:src="imgUrl+users.img" class="img-responsive">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"> 性别 </label>
                                    <div class="col-sm-9">
                                        <select v-model="users.sex" class="form-control">
                                            <option value="男">男</option>
                                            <option value="女">女</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"> 地址 </label>
                                    <div class="col-sm-9">
                                        <input type="text" placeholder="请输入地址" class="form-control col-xs-10 col-sm-5"
                                               v-model="users.address"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"> 密码 </label>
                                    <div class="col-sm-9">
                                        <input type="password" placeholder="请输入密码"
                                               class="form-control col-xs-10 col-sm-5" v-model="users.pwd"/>
                                    </div>
                                </div>
                                <!--<div class="form-group" >
                                    <label class="col-sm-3 control-label no-padding-right" > 角色 </label>
                                    <div class="col-sm-9">
                                        <input type="text" placeholder="请输入角色" class="form-control col-xs-10 col-sm-5" v-model="users.utype" />
                                    </div>
                                </div>-->
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"> 电话 </label>
                                    <div class="col-sm-9">
                                        <input type="text" placeholder="请输入电话" class="form-control col-xs-10 col-sm-5"
                                               v-model="users.tel"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"> 年龄 </label>
                                    <div class="col-sm-9">
                                        <input type="text" placeholder="请输入年龄" class="form-control col-xs-10 col-sm-5"
                                               v-model="users.age"/>
                                    </div>
                                </div>
                               <!-- <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"> 目前状态 </label>
                                    <div class="col-sm-9">
                                        <select v-model="users.status" class="form-control">
                                            <option value="待审核">待审核</option>
                                            <option value="正常">正常</option>
                                        </select>
                                    </div>
                                </div>-->
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"> 邮箱 </label>
                                    <div class="col-sm-9">
                                        <input type="text" placeholder="请输入邮箱" class="form-control col-xs-10 col-sm-5"
                                               v-model="users.email"/>
                                    </div>
                                </div>

                                <div class="space-4"></div>

                                <div class="clearfix form-actions1">
                                    <div class="col-md-offset-3 col-md-9">
                                        <button class="btn btn-info" type="button" @click="save();">
                                            <i class="ace-icon fa fa-check bigger-110"></i>
                                            提交
                                        </button>
                                        &nbsp; &nbsp; &nbsp;
                                        <button class="btn" type="reset">
                                            <i class="ace-icon fa fa-undo bigger-110"></i>
                                            重置
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div><!-- /.col -->
            </div>
            <div>
            </div>
        </div>
    </div>


</template>

<script>
    import File from "@/components/file";
    export default {
        components: {File},
        name: "update.vue",
        data: function () {
            return {
                users: {},
                userss: [],
                imgUrl: '',
            }
        },
        mounted: function () {
            let _this = this;
            _this.imgUrl = _this.$imgurl;
            _this.loginUser = Tool.getLoginUser();
            if (_this.loginUser.id != null) {
                _this.users = SessionStorage.get("USERS");
            } else {
                _this.$router.push("/login");
            }
            _this.commonAll();
        },
        methods: {

            checkName() {
                let _this = this;
                _this.$ajax.post(_this.$javaurl + "/admin/users/checkName", _this.users).then((response) => {
                    // Loading.hide();
                    let rs = response.data;
                    console.log("data==" + rs);
                    if (rs == "ng") {
                        console.log("名称已存在！请替换！");
                        Toast.warning("名称已存在！请替换！");
                        _this.users.uname = "";
                        return false;
                        //alert("名称已存在！请替换！");
                    } else {
                        console.log("名称可以用！");
                        // Toast.warning(resp.message)
                    }
                })
            },

            /**
             * 点击【保存】
             */
            save() {
                let _this = this;
                // 保存校验
                if (!Validator.require(_this.users.uname, "用户名")) {
                    return;
                }
                if (!Validator.require(_this.users.name, "姓名")) {
                    return;
                }
                if (!Validator.require(_this.users.sex, "性别")) {
                    return;
                }
                if (!Validator.require(_this.users.address, "地址")) {
                    return;
                }
                if (!Validator.require(_this.users.pwd, "密码") || !Validator.length(_this.users.pwd, "密码", 6, 12)) {
                    return;
                }
                /*if ( !Validator.require(_this.users.utype, "角色")) {
                    return;
                }*/
                if (!Validator.require(_this.users.tel, "电话号码")||!Validator.checkTel(_this.users.tel, "电话号码")) {
                    return;
                }
                if (!Validator.require(_this.users.age, "年龄")) {
                    return;
                }
               /* if (!Validator.require(_this.users.status, "目前状态")) {
                    return;
                }*/
                if (!Validator.require(_this.users.email, "邮箱")) {
                    return;
                }
                //Loading.show();
                _this.$ajax.post(_this.$javaurl + "/admin/users/save", _this.users).then((response) => {
                    // Loading.hide();
                    let resp = response.data;
                    console.log("data==" + resp);
                    if (resp == "success") {
                        Toast.success("保存成功！");
                        _this.$router.push("/users");
                    } else {
                        Toast.error("保存失败！");
                    }
                })
            },
            afterUpload(resp) {
                let _this = this;
                console.log("resp==" + resp)
                let img = resp.content;
                console.log("img==" + img)
                _this.users.img = img;
            },
            commonAll() {
                let _this = this;
                _this.$ajax.post(_this.$javaurl + '/admin/common/all').then((response) => {
                    let resp = response.data;
                    console.log(resp);
                    _this.tlist = resp.tlist;
                    _this.qlist = resp.qlist;
                    //_this.communitys = resp.communityList;
                })
            },
        },
        computed: {
        }
    }
</script>

