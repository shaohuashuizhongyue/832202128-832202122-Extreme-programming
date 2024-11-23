<template>

    <!--左侧导航开始-->

    <!--左侧导航结束-->
    <div class="left">
        <ul class="nav" id="side-menu">
            <li class="nav-header">
                <div class="dropdown profile-element">
                    <span v-show="loginUser.img"><img alt="image" class="img-circle" v-bind:src="imgUrl+loginUser.img" width="64px" height="64px"/></span>
                    <span v-show="!loginUser.img"><img alt="image" class="img-circle" v-bind:src="imgUrl+'zanwu.jpg'" width="64px" height="64px"/></span>
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="clear">
                               <span class="block m-t-xs"><strong class="font-bold"> {{loginUser.uname}}({{loginUser.name}})</strong></span>
                                <span class="text-muted text-xs block"> {{loginUser.utype}}<b class="caret"></b></span>
                                </span>
                    </a>
                    <ul class="dropdown-menu animated fadeInRight m-t-xs">
                        <li><a class="J_menuItem" @click="showUserInfo();" href="#">个人资料</a>
                        </li>
                        <li class="divider"></li>
                        <li><a @click="logOut();">安全退出</a>
                        </li>
                    </ul>
                </div>
                <div class="logo-element">A+
                </div>
            </li>
        </ul>

        <el-menu default-active="/" router class="el-menu-vertical-demo"
                 background-color="#199D82"
                 text-color="#fff"
                 active-text-color="#ffd04b" v-show="loginUser.utype=='管理员'">
            <el-menu-item index="/home">
                <i class="el-icon-menu"></i>
                <span slot="title">系统首页</span>
            </el-menu-item>
            <el-submenu index="/">
                <template slot="title">
                    <i class="el-icon-user"></i>
                    <span slot="title">用户管理</span>
                </template>
                <el-menu-item-group>
                    <el-menu-item index="/users">管理员管理</el-menu-item>
                    <el-menu-item index="/users/zxs">普通用户管理</el-menu-item>
                    <el-menu-item index="/users/info">个人信息管理</el-menu-item>
                </el-menu-item-group>
            </el-submenu>
            <!--<el-menu-item index="/news">
                <i class="el-icon-news"></i>
                <span slot="title">公告管理</span>
            </el-menu-item>-->
            <el-menu-item index="/classroom">
                <i class="el-icon-news"></i>
                <span slot="title">教室管理</span>
            </el-menu-item>
            <el-submenu index="4">
                <template slot="title">
                    <i class="el-icon-goods"></i>
                    <span slot="title">设备管理</span>
                </template>
                <el-menu-item-group>
                    <el-menu-item index="/ftype">设备分类管理</el-menu-item>
                    <el-menu-item index="/device">设备管理</el-menu-item>
                    <el-menu-item index="/device/apply">设备申请管理</el-menu-item>
                    <el-menu-item index="/device/repair">设备维修管理</el-menu-item>
                    <el-menu-item index="/device/finance">设备财务管理</el-menu-item>
                </el-menu-item-group>
            </el-submenu>
        </el-menu>

        <el-menu default-active="/" router class="el-menu-vertical-demo"
                 background-color="#199D82"
                 text-color="#fff"
                 active-text-color="#ffd04b" v-show="loginUser.utype=='普通用户'||loginUser.utype=='管理员'">
            <el-menu-item index="/home">
                <i class="el-icon-menu"></i>
                <span slot="title">系统首页</span>
            </el-menu-item>
            <el-submenu index="/">
                <template slot="title">
                    <i class="el-icon-user"></i>
                    <span slot="title">用户管理</span>
                </template>
                <el-menu-item-group>
                   <!-- <el-menu-item index="/users" v-show="loginUser.utype=='普通用户'">管理员管理</el-menu-item>-->
                    <el-menu-item index="/users/info">个人信息管理</el-menu-item>
                </el-menu-item-group>
            </el-submenu>
            <el-menu-item index="/classroom">
                <i class="el-icon-news"></i>
                <span slot="title">教室管理</span>
            </el-menu-item>
            <el-submenu index="4">
                <template slot="title">
                    <i class="el-icon-goods"></i>
                    <span slot="title">设备管理</span>
                </template>
                <el-menu-item-group>
                    <el-menu-item index="/ftype">设备分类管理</el-menu-item>
                    <el-menu-item index="/device">设备管理</el-menu-item>
                    <el-menu-item index="/device/apply">设备申请管理</el-menu-item>
                    <el-menu-item index="/device/repair">设备维修管理</el-menu-item>
                    <el-menu-item index="/device/finance">设备财务管理</el-menu-item>
                </el-menu-item-group>
            </el-submenu>
        </el-menu>
    </div>
</template>


<style>
    .left .el-menu-vertical-demo:not(.el-menu--collapse) {
        width: 200px;
        min-height: 600px;
        height: 100%;
        margin-top: -16px;
    }
    .left{
        width: 200px;
        min-height: 400px;
        height: 100%;
    }

</style>

<script>
    export default {
        name: "left",
        data:function () {
            return{
                loginUser: {},
                imgUrl:'',

            }

        },
        mounted:function () {
            let _this=this;
            _this.loginUser= Tool.getLoginUser();
            if (!_this.loginUser) {
                _this.$router.push("/login");
            }else {
            }
            _this.imgUrl=_this.$imgurl;
        },
        methods:{
            /*处理修改个人信息*/
            showUserInfo(){
                let _this=this;
                _this.$router.push("/users/info");
                /*_this.loginUser= Tool.getLoginUser();
                if (_this.loginUser!=null) {
                    console.log("res=22="+_this.loginUser.id);
                      _this.$ajax.post(_this.$javaurl+"/admin/users/getById"+_this.loginUser).then((response)=>{
                          let resp = response.data;
                          console.log("res=="+resp);
                          SessionStorage.set("SYSUSER",resp);
                          _this.$router.push("/users/info");
                      });
                    //SessionStorage.set("SYSUSER",_this.loginUser);

                }else {
                    this.$router.push("/login");
                }*/

            },
            /*注销*/
            logOut(){
                let _this = this;
                _this.$ajax.get(_this.$javaurl+"/admin/users/logOut").then((response)=>{
                    let resp = response.data;
                    if (resp.success){
                        Toast.success("注销成功！");
                        Tool.setLoginUser(null);
                        this.$router.push("/login");
                    } else {
                        console.log("信息=="+resp.message);
                    }
                })

            },
        }
    }
</script>
