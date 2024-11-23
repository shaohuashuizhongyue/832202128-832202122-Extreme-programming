<template>

    <div>
        <div style="width:100%;text-align: center;margin: 0 auto;position: absolute;border-radius:5px;background:url('/ajh/static/login/images/banner_slide_03.jpg') no-repeat 4px 5px;height: 100%;">
            <div id="loginbox" style="border-radius:6px">
                <form action="" method="post" name="loginForm"
                      id="loginForm">
                    <div class="control-group normal_text">
                        <h3>联系人管理系统
                            <!--<img src="/static/login/logo.png" alt="Logo" />-->
                        </h3>
                    </div>
                    <div class="control-group">
                        <div class="controls">
                            <div class="main_input_box">
                                <div class="add-on bg_lg" style="width:16%;float: left;height:37px;">
                                    <i><img src="/static/login/user.png" /></i>
                                </div>
                                <div style="width:80%;float: left;"><input style="width:100%;margin:0px;padding:0px;height:37px;" type="text" v-model="users.uname" id="loginname" value="" placeholder="请输入用户名" /></div>
                            </div>
                        </div>
                    </div>
                    <div class="control-group">
                        <div class="controls">
                            <div class="main_input_box" >
                                <div class="add-on bg_ly" style="width:16%;float: left;height:37px;">
                                    <i><img src="static/login/suo.png" /></i>
                                </div>
                                <div style="width:80%;float: left;"><input style="width:100%;margin:0px;padding:0px;height:37px;"  v-model="users.pwd" type="password" name="password" id="password" placeholder="请输入密码" value="" /></div>
                            </div>
                        </div>
                    </div>

                    <div class="control-group">
                        <div class="controls">
                            <div class="main_input_box" >
                                <div class="add-on bg_ly" style="width:16%;float: left;height:37px;">
                                    <i><img src="static/login/suo.png" /></i>
                                </div>
                                <div style="width:80%;float: left;">
                                        <select name="utype" v-model="users.utype" id="utype" style="width:100%;height: 37px;">
                                            <option value="管理员">管理员</option>
                                            <option value="普通用户">普通用户</option>
                                            <!--<option value="管理员">管理员</option>-->
                                        </select>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div style="clear: both;width:100%;height:20px;text-align: right;">
                        <div style="width:130px;float: right;padding-right:10%;">
                            <div style="float: left;margin-top:3px;margin-right:2px;height:16px;text-align: right;">
                                <font color="white">记住密码</font>
                            </div>
                            <div style="float: left;height:16px;">
                                <input  v-model="remember" name="form-field-checkbox" id="saveid" type="checkbox"
                                       onclick="savePaw();" style="padding-top:0px;" />
                            </div>
                        </div>
                    </div>

                    <div class="form-actions" >
                        <div style="width:100%;height:36px;">

                            <!--<div style="float: left;">
                                <i><img src="/static/login/yan.png" /></i>
                            </div>-->
                            <!--<div style="float: left;width:30%;" class="codediv">
                                <input type="text" name="code" id="code" class="login_code"/>
                            </div>
                            <div style="float: left;">
                                <i><img src="/admin/autoCode" height="27px" alt="点击更换" title="点击更换"  /></i>
                            </div>-->

                            <!--<div class="pull-right" style="padding-right:3%;width:15%;float:left;">
                                <a @click="reg();"  class="btn btn-success">注册</a>
                            </div>-->
                            <div class="pull-right" style="width:15%;float:left;">
                                <a @click="login();"  class="flip-link btn btn-info" id="to-recover">登录</a>
                            </div>

                        </div>
                    </div>

                </form>


                <div class="controls">
                    <div class="main_input_box">

                        <!--<font color="white"><span id="nameerr">Copyright ©中</span></font>-->

                        <font color="white"><span id="nameerr">Copyright © <b style="color: #35ff89;"> 联系人管理系统</b></span></font>
                    </div>
                </div>
            </div>
        </div>
        <div id="templatemo_banner_slide" class="container_wapper">
            <div class="camera_wrap camera_emboss" id="camera_slide">
                <div data-src="/static/login/images/banner_slide_01.jpg"></div>
                <div data-src="/static/login/images/banner_slide_02.jpg"></div>
                <div data-src="/static/login/images/banner_slide_03.jpg"></div>
            </div>
        </div>
    </div>

</template>

<script>

    export default {
        name: "login",
        data:function(){
            return {
                users: {},
                remember: true, // 默认勾选记住我
                ROLE:LOGIN_ROLE,
                webUrl:'',
            }
        },
        mounted:function(){
            let _this = this;
            $("body").removeClass("no-skin");
            $("body").attr("class", "signin");

            // 从缓存中获取记住的用户名密码，如果获取不到，说明上一次没有勾选“记住我”
            let rememberUser = LocalStorage.get(LOCAL_KEY_REMEMBER_USER);
            if (rememberUser) {
                _this.users = rememberUser;
            }
            _this.webUrl=_this.$weburl;

        },
        methods:{

            /*注册*/
            reg(){
                let _this = this;
                _this.$router.push("/register");

            },
            login(){
                let _this = this;
                // 保存校验
                if (!Validator.require(_this.users.uname, "用户名")) {
                    return;
                }
                if (!Validator.require(_this.users.pwd, "密码")) {
                    return;
                }
                /*if (!Validator.require(_this.users.utype, "角色")) {
                    return;
                }*/
                _this.$ajax.post(_this.$javaurl+"/admin/users/login",_this.users).then((response)=>{
                    let resp = response.data;
                    if (resp.success){
                        Toast.success("登录成功！");
                        console.log("信息=="+resp.message);
                        let loginUser = resp.content;
                        Tool.setLoginUser(resp.content);
                        // 判断“记住我”
                        if (_this.remember) {
                            // 如果勾选记住我，则将用户名密码保存到本地缓存
                            LocalStorage.set(LOCAL_KEY_REMEMBER_USER, {
                                uname: loginUser.uname,
                                pwd: loginUser.pwd,
                                utype: loginUser.utype,
                            });
                        } else {
                            // 没有勾选“记住我”时，要把本地缓存清空，否则按照mounted的逻辑，下次打开时会自动显示用户名密码
                            LocalStorage.set(LOCAL_KEY_REMEMBER_USER, null);
                        }
                        _this.$router.push("/index");
                    } else {
                        console.log("信息=="+resp.message);
                        _this.users.pwd = "";
                        //Toast.error("登录失败！用户名或者密码错误！");
                        Toast.error(resp.message);
                    }
                })


            },

        }
        /*components: {
          HelloWorld
        }*/
    }

</script>

<style scoped>



</style>


